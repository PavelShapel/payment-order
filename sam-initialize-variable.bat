@echo off

set DOMAIN=com-pavelshapel
set PROJECT=pavikon-payment-order

set ARTIFACT_BUCKET=%DOMAIN%-artifact
set SOURCE_BUCKET=%DOMAIN%-%PROJECT%-source
set TARGET_BUCKET=%DOMAIN%-%PROJECT%-target

set STACK=%PROJECT%-stack

set SAM_TEMPLATE_YML=sam-template.yml
set PACKAGE_SAM_TEMPLATE_YML=packaged-sam-template.yml

set AKBB_PAYMENT_ORDER_TEMPLATE=.\aws-lambda-service-file-placeholder\src\test\resources\akbb-payment-order-template.txt

set CORPORATION_TABLE=corporation
set CORPORATION_TABLE_BACKUP=corporation-backup
