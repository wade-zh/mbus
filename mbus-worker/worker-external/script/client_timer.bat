@echo off
echo wscript.sleep 1800000 > timer.vbs
:begin
start CNNServer.exe 
timer.vbs
taskkill /f /im CNNServer.exe
goto begin