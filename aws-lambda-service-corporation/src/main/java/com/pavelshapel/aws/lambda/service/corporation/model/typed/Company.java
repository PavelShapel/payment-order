package com.pavelshapel.aws.lambda.service.corporation.model.typed;

import com.pavelshapel.aws.lambda.service.corporation.model.Type;
import com.pavelshapel.core.spring.boot.starter.api.model.Named;
import com.pavelshapel.core.spring.boot.starter.api.model.Typed;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static com.pavelshapel.aws.lambda.service.corporation.model.Type.COMPANY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
//@DynamoDBDocument
public class Company implements Typed<Type>, Named {
//    @DynamoDBTypeConvertedEnum
//    @DynamoDBIgnore
    Type type = COMPANY;
//    @DynamoDBAttribute
    String name;
//    @DynamoDBAttribute
    String fullName;
}
