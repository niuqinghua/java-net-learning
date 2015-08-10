keytool -genkey -alias mysocket -keyalg RSA -keystore mysocket.jks

java -Djavax.net.ssl.keyStore=mysocket.jks -Djavax.net.ssl.keyStorePassword=mysocket net.learning.sample.socket.sample5.MyServer

java -Djavax.net.ssl.trustStore=mysocket.jks  -Djavax.net.ssl.trustStorePassword=mysocket net.learning.sample.socket.sample5.MyClient