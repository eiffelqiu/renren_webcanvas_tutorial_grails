Prerequisites
=======
Maven 
-------
install Maven, check http://maven.apache.org/

Mac OSX user, if you have installed homebrew

	$ brew install maven

Run
=======
step 1
-------

open "com.yourdomain.webcanvas.config.AppConfig"
change APP_ID,API_KEY,APP_SECRET,APP_NAME according to your app setting

step 2
-------

search and replace "www.yourdomain.com" to your renren app website host

set renren Callback URL to "http://www.yourdomain.com:8080/renren_webcanvas_tutorial/" , change domain name accordingly

step 3
-------

	$ mvn grails:run-app
