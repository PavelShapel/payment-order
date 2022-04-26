@echo off

set BUCKET_NAME=com.pavelshapel.artifact
set STACK_NAME=S3ObjectsMacro
set MACRO_SAM_TEMPLATE_YML=macroSamTemplate.yml
set PACKAGE_SAM_TEMPLATE_YML=packagedSamTemplate.yml

aws cloudformation package --template-file %MACRO_SAM_TEMPLATE_YML% --s3-bucket %BUCKET_NAME% --output-template-file %PACKAGE_SAM_TEMPLATE_YML%

aws cloudformation deploy --stack-name %STACK_NAME% --template-file %PACKAGE_SAM_TEMPLATE_YML% --capabilities CAPABILITY_IAM