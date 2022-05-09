@echo off

call sam-initialize-variable.bat

aws s3 rm s3://%SOURCE_BUCKET% --recursive
aws s3 rm s3://%TARGET_BUCKET% --recursive
rem aws dynamodb create-backup --table-name %CORPORATION_TABLE% --backup-name %CORPORATION_TABLE_BACKUP%
aws cloudformation delete-stack --stack-name %STACK%

