# akkakryotest
sample application demoing how to use kryo within akka cluster

## usage
'pingpong' (port 2551) is seed node

mvn exec:java -Dexec.mainClass="akkakryotest.Main" -Dakka.remote.netty.tcp.port=2551 where port should be incremented for each node
