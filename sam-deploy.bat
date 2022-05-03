@echo off

set ARTIFACT_BUCKET=com-pavelshapel-artifact
set STACK=pavikon-payment-order-stack
set SAM_TEMPLATE_YML=sam-template.yml
set PACKAGE_SAM_TEMPLATE_YML=packaged-sam-template.yml
aws cloudformation package --template-file %SAM_TEMPLATE_YML% --s3-bucket %ARTIFACT_BUCKET% --output-template-file %PACKAGE_SAM_TEMPLATE_YML%
aws cloudformation deploy --stack-name %STACK% --template-file %PACKAGE_SAM_TEMPLATE_YML% --capabilities CAPABILITY_IAM

set SOURCE_BUCKET=com-pavelshapel-pavikon-payment-order-source
set AKBB_PAYMENT_ORDER_TEMPLATE=.\aws-lambda-service-file-placeholder\src\test\resources\akbb-payment-order-template.txt
aws s3 cp %AKBB_PAYMENT_ORDER_TEMPLATE% s3://%SOURCE_BUCKET%