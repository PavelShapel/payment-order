@echo off

call docker-compose down

goto exit

:error
echo error
echo  
pause

:exit
echo ok
echo  
