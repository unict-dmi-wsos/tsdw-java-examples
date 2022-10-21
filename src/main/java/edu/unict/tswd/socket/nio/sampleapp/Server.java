package edu.unict.tswd.socket.nio.sampleapp;
// Credits
// https://www.developer.com/design/understanding-asynchronous-socket-channels-in-java/
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Server {
    public static void main(String[] args){
        try (AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open()) {
            server.bind(new InetSocketAddress("127.0.0.1", 1234));

            System.out.println("Not waiting");
            while(true) {
                System.out.println("Loop");
                Future<AsynchronousSocketChannel> acceptCon = server.accept();
                AsynchronousSocketChannel client = acceptCon.get(10, TimeUnit.SECONDS);
                System.out.println("Connection"+client);
                if ((client != null) && (client.isOpen())) {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    Future<Integer> readval = client.read(buffer);
                    System.out.println("Received from client: " + new String(buffer.array()).trim());
                    readval.get();
                    buffer.flip();
                    String str = "I'm fine. Thank you!";
                    Future<Integer> writeVal = client.write(ByteBuffer.wrap(str.getBytes()));
                    System.out.println("Writing back to client: " + str);
                    writeVal.get();
                    buffer.clear();
                }
                client.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}