@echo off

call sam-initialize-variable.bat

aws cloudformation package --template-file %SAM_TEMPLATE_YML% --s3-bucket %ARTIFACT_BUCKET% --output-template-file %PACKAGE_SAM_TEMPLATE_YML%
aws cloudformation deploy --stack-name %STACK% --template-file %PACKAGE_SAM_TEMPLATE_YML% --capabilities CAPABILITY_IAM
aws s3 cp %AKBB_PAYMENT_ORDER_TEMPLATE% s3://%SOURCE_BUCKET%