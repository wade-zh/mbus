@echo off
echo wscript.sleep 1800000 > timer.vbs
:begin
redis-server.exe redis.windows.conf
timer.vbs
taskkill /f /im redis.exe
goto begin