set server_dict="C:\online-games\online-games-server"
set tomcat_dict="C:\online-games\apache-tomcat-9.0.4\webapps"
set war_file="C:\online-games\online-games-server\target\online-games.war"
set tomcat_file="C:\online-games\apache-tomcat-9.0.4\webapps\online-games.war"

cd /d %server_dict%

call mvn clean install -DskipTests
echo "INSTALL DONE."

del %tomcat_file%
echo "PREV FILE DELETED."

copy %war_file% %tomcat_dict%
echo "NEW FILE COPIED."

echo "DEPLOY FINISHED."
