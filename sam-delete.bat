@echo off

call sam-initialize-variable.bat

aws s3 rm s3://%SOURCE_BUCKET% --recursive
aws s3 rm s3://%TARGET_BUCKET% --recursive
aws s3 rm s3://%ARTIFACT_BUCKET% --recursive
aws logs delete-log-group --log-group-name /aws/lambda/%NBRB_FUNCTION%
aws logs delete-log-group --log-group-name /aws/lambda/%FILE_PLACEHOLDER_FUNCTION%
rem aws dynamodb create-backup --table-name %CORPORATION_TABLE% --backup-name %CORPORATION_TABLE_BACKUP%
aws cloudformation delete-stack --stack-name %STACK%

