$CATALINA_HOME/bin/shutdown.sh
rm $CATALINA_HOME/webapps/test_aws.war
rm -rf $CATALINA_HOME/webapps/test_aws
cp ./target/test_aws.war $CATALINA_HOME/webapps/
$CATALINA_HOME/bin/startup.sh
tail -f $CATALINA_HOME/logs/catalina.out
