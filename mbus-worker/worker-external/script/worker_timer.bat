@echo off
echo wscript.sleep 1800000 > timer.vbs
:begin
java -jar -Dfile.encoding=utf-8 worker-1.2.2.jar
timer.vbs
taskkill /f /im java.exe
goto begin