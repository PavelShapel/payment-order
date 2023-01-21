package com.pavelshapel.aws.lambda.service.file.manager.handler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.pavelshapel.aws.spring.boot.starter.api.service.BucketHandler;
import com.pavelshapel.json.spring.boot.starter.converter.JsonConverter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.function.Function;

import static java.util.Collections.singletonMap;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class FileUploaderRequestHandler implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    BucketHandler bucketHandler;
    JsonConverter jsonConverter;

    @Override
    public APIGatewayProxyResponseEvent apply(APIGatewayProxyRequestEvent request) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(singletonMap(CONTENT_TYPE, APPLICATION_JSON_VALUE));
        byte[] requestBody = Base64.getDecoder().decode(request.getBody().getBytes(StandardCharsets.UTF_8));
        String responseBody;
        try (InputStream inputStream = new ByteArrayInputStream(requestBody)) {
            String bucket = request.getHeaders().get("x-bucket");//requestBody.getBucket();
            String key = request.getHeaders().get("x-key");//requestBody.getKey();
            ObjectMetadata metadata = createMultipartFormMetadata();
            responseBody = bucketHandler.uploadObject(bucket, key, inputStream, metadata);
            return response
                    .withStatusCode(OK.value())
                    .withBody(responseBody);
        } catch (Exception exception) {
            responseBody = jsonConverter.pojoToJson(singletonMap("exceptionMessage", exception.getMessage()));
            return response
                    .withStatusCode(INTERNAL_SERVER_ERROR.value())
                    .withBody(responseBody);
        }
    }

    private ObjectMetadata createMultipartFormMetadata() {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(MULTIPART_FORM_DATA_VALUE);
        return metadata;
    }

    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context)
    {
        //Create the logger LambdaLogger
        logger = context.getLogger();
        logger.log("Loading Java Lambda handler of Proxy");

        //Log the length of the incoming body
        logger.log(String.valueOf(event.getBody().getBytes().length));

        //Create the APIGatewayProxyResponseEvent response
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();

        //Set up contentType
        String String contentType = "";

        //Change these values to fit your region and bucket name
        String clientRegion = "YOUR-REGION";
        String bucketName = "YOUR-BUCKET-NAME";

        //Every file will be named image.jpg in this example.
        //You will want to do something different here in production
        String fileObjKeyName = "image.jpg";

        try
        {
            //Get the uploaded file and decode from base64
            byte[] bI = Base64.decodeBase64(event.getBody().getBytes());

            //Get the content-type header
            Map<String, String> hps = event.getHeaders();

            if (hps != null) {
                contentType = hps.get("content-type");
            }

            //Extract the boundary
            String[] boundaryArray = contentType.split("=");

            //Transform the boundary to a byte array
            byte[] boundary = boundaryArray[1].getBytes();

            //Log the extraction for verification purposes
            logger.log(new String(bI, "UTF-8") + "\n");

            //Create a ByteArrayInputStream
            ByteArrayInputStream content = new ByteArrayInputStream(bI);

            //Create a MultipartStream to process the form-data
            MultipartStream multipartStream = new MultipartStream(content, boundary, bI.length, null);

            //Create a ByteArrayOutputStream
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            //Find first boundary in the MultipartStream
            boolean nextPart = multipartStream.skipPreamble();

            //Loop through each segment
            while (nextPart)
            {
                String header = multipartStream.readHeaders();

                //Log header for debugging
                logger.log("Headers:");
                logger.log(header);

                //Write out the file to our ByteArrayOutputStream
                multipartStream.readBodyData(out);

                //Get the next part, if any
                nextPart = multipartStream.readBoundary();

            }

            //Log completion of MultipartStream processing
            logger.log("Data written to ByteStream");

            //Prepare an InputStream from the ByteArrayOutputStream
            InputStream fis = new ByteArrayInputStream(out.toByteArray());

            //Create our S3Client Object
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(clientRegion)
                    .build();

            //Configure the file metadata
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(out.toByteArray().length);
            metadata.setContentType("image/jpeg");
            metadata.setCacheControl("public, max-age=31536000");

            //Put file into S3
            s3Client.putObject(bucketName, fileObjKeyName, fis, metadata);

            //Log status
            logger.log("Put object in S3");

            //Construct a response
            response.setStatusCode(200);
            Map<String, String> responseBody = new HashMap<String, String>();
            responseBody.put("Status", "File stored in S3");
            String responseBodyString = new JSONObject(responseBody).toJSONString();
            response.setBody(responseBodyString);

        }

        catch(AmazonServiceException e)
        {

            // The call was transmitted successfully, but
            // Amazon S3 couldn't process it, so it returned
            // an error response.
            logger.log(e.getMessage());
        }

        catch(SdkClientException e)
        {
            // Amazon S3 couldn't be contacted for a response, or
            // the client couldn't parse the response from Amazon S3.
            logger.log(e.getMessage());
        }

        catch (IOException e)
        {

            // Handle MultipartStream class IOException
            logger.log(e.getMessage());
        }

        logger.log(response.toString());

        return response;
    }
}
