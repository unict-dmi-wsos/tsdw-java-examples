package edu.unict.tswd.socket.tcp.echoservermt;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static final int PORT = 1050;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("EchoMultiServer: started");
        System.out.println("Server Socket: " + serverSocket);

        try {
            while(true) {
                // bloccante finch� non avviene una connessione:
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connection accepted: "+ clientSocket);

                try {
                    new ServerThread(clientSocket);
                } catch(IOException e) {
                    clientSocket.close();
                }
            }
        }
        catch (IOException e) {
            System.err.println("Accept failed");
            System.exit(1);
        }
        System.out.println("EchoMultiServer: closing...");
        serverSocket.close();
    }
}
