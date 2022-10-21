package edu.unict.tswd.socket.nio.sampleapp;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Client {
    public static void main(String[] args){
        try (AsynchronousSocketChannel client = AsynchronousSocketChannel.open()) {
            Future<Void> result = client.connect(new InetSocketAddress("127.0.0.1", 1234));
            result.get();
            String str= "Hello! How are you?";
            ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
            Future<Integer> writeval = client.write(buffer);
            System.out.println("Writing to server: "+str);
            writeval.get();
            buffer.flip();
            Future<Integer> readval = client.read(buffer);
            System.out.println("Received from server: " +new String(buffer.array()).trim());
            readval.get();
            buffer.clear();
        }
        catch (ExecutionException | IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            System.out.println("Disconnected from the server.");
        }
    }
}

