@echo off

set SOURCE_BUCKET=com-pavelshapel-pavikon-payment-order-source
set TARGET_BUCKET=com-pavelshapel-pavikon-payment-order-target
aws s3 rm s3://%SOURCE_BUCKET% --recursive
aws s3 rm s3://%TARGET_BUCKET% --recursive

set STACK=pavikon-payment-order-stack
aws cloudformation delete-stack --stack-name %STACK%

