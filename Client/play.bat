
@set ANDROID_JAR=/Users/RhoSunghyun/Documents/dev/adt-bundle-mac-x86_64-20130729/sdk/platforms/android-18/android.jar
@set OUTPUT_DIR=/Users/RhoSunghyun/git/clientsdk4/Client/
@set PACKAGE_FILE_NAME=NEW_PACKAGE.apk


@REM 리소스 패키지와 dex 파일을 패키징하여 설치 가능한 APK 파일 생성
@call /Users/RhoSunghyun/Documents/dev/WORKING_DIRECTORY/sdk/apkbuilder/etc/apkbuilder.bat %OUTPUT_DIR%/%PACKAGE_FILE_NAME% -z %OUTPUT_DIR%/resources.ap_ -f %OUTPUT_DIR%\result.dex