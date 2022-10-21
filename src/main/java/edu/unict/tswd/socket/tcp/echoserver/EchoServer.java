package edu.unict.tswd.socket.tcp.echoserver;
// Inspired by www.dmi.unict.it/tramonta/lessons/sd/socketJ.zip

import java.io.*;
import java.net.*;

public class EchoServer {
    public static final int PORT = 1050; // Server port
    public static final String SECRET = "mischief-managed"; // Server secret

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("EchoServer: started ");
        System.out.println("Server Socket: " + serverSocket);
        Socket clientSocket=null;
        BufferedReader in=null;
        PrintWriter out=null;
        try {
            // Waits until connection is available
            clientSocket = serverSocket.accept();
            System.out.println("Connection accepted: "+ clientSocket);

            // Input Stream
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Output Stream
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            // Loop, "mischief-managed" will close it
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.equals(SECRET))
                    break;
                System.out.println("Echoing: " + inputLine);
                out.println(inputLine); // Send it back
            }
        }
        catch (IOException e) {
            System.err.println("Accept failed");
            System.exit(1);
        }
        // Closing all flows
        System.out.println("EchoServer: closing...");

        try {
            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Errorclosing...");
            e.printStackTrace();
        }

    }
}

