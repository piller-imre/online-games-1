set server_dict="C:\Users\Lev\git\online-games\online-games"
set tomcat_dict="C:\Users\Lev\git\online-games\apache-tomcat-9.0.4\webapps"
set war_file="C:\Users\Lev\git\online-games\online-games\target\online-games.war"
set tomcat_file="C:\Users\Lev\git\online-games\apache-tomcat-9.0.4\webapps\online-games.war"

cd /d %server_dict%

call mvn clean install -DskipTests
echo "INSTALL DONE."

del %tomcat_file%
echo "PREV FILE DELETED."

copy %war_file% %tomcat_dict%
echo "NEW FILE COPIED."

echo "DEPLOY FINISHED."
