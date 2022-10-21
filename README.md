# Examples Sockets

## SimpleScraper


## Socket Stream
Server

``sh
java -cp target/tswd-java-examples-1.0-SNAPSHOT.jar edu.unict.tswd.socket.tcp.echoserver.EchoServer
``

Client

``sh
java -cp target/tswd-java-examples-1.0-SNAPSHOT.jar edu.unict.tswd.socket.tcp.echoserver.EchoClient
``

## Socket UDP Multicast
Server
``sh
java -cp target/tswd-java-examples-1.0-SNAPSHOT.jar edu.unict.tswd.socket.udp.filestreamer.MulticastServer
``

Client 1
``sh
java -cp target/tswd-java-examples-1.0-SNAPSHOT.jar edu.unict.tswd.socket.udp.filestreamer.MulticastClient
``

Client 2
``sh
java -cp target/tswd-java-examples-1.0-SNAPSHOT.jar edu.unict.tswd.socket.udp.filestreamer.MulticastClient
``

## Socket UDP LineReader
Server
``sh
java -cp target/tswd-java-examples-1.0-SNAPSHOT.jar edu.unict.tswd.socket.udp.filestreamer.LineServer
``

Server

``sh
java -cp target/tswd-java-examples-1.0-SNAPSHOT.jar edu.unict.tswd.socket.udp.filestreamer.LineClient
``

##  NIO ?

java -cp target/tswd-java-examples-1.0-SNAPSHOT.jar edu.unict.tswd.socket.nio.sampleapp.Server

java -cp target/tswd-java-examples-1.0-SNAPSHOT.jar edu.unict.tswd.socket.nio.sampleapp.Client
java -cp target/tswd-java-examples-1.0-SNAPSHOT.jar edu.unict.tswd.socket.nio.sampleapp.Client

Docker 

docker build . -t unict:tswd-java-examples
docker run  -t unict:tswd-java-examples edu.unict.tswd.socket.udp.filestreamer.MulticastClient
