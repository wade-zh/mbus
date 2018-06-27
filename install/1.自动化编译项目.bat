if exist .\packages (
rd /s /q .\packages
mkdir .\packages
rd /s /q c:\mbus
mkdir c:\mbus
) else (
mkdir .\packages
mkdir c:\mbus
)

xcopy /y /d ..\mbus-worker\worker-external\script .\packages\script\


xcopy /y ..\mbus-worker\worker-external\mbus c:\mbus\

mkdir .\packages\cnn-server-2.1.0
xcopy /y ..\mbus-worker\worker-external\external-socket\bin\2.1.0 .\packages\cnn-server-2.1.0\

xcopy /y .\application.properties ..\mbus-site\site-web\src\main\resources\application.properties
xcopy /y .\jedis.xml ..\mbus-worker\worker-app\src\main\java\resources\jedis.xml
xcopy /y .\rabbit.properties ..\mbus-worker\worker-app\src\main\java\resources\rabbit.properties


cd ../mbus-site
call mvn clean package -Dmaven.test.skip=true
xcopy /y /f .\site-web\target\website-1.2.2.jar ..\install\packages\web\

cd ../mbus-worker
call mvn clean package -Dmaven.test.skip=true
xcopy /y /d .\worker-app\target\worker-1.2.2.jar ..\install\packages\worker\
xcopy /y /f c:\mbus\tcp.dll ..\install\packages\worker\

cd ../install
xcopy /y /f .\packages\script\client_timer.bat .\packages\cnn-server-2.1.0
xcopy /y /f .\packages\script\worker_timer.bat .\packages\worker
pause
pause