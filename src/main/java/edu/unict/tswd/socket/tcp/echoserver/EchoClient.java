package edu.unict.tswd.socket.tcp.echoserver;

// Inspired by www.dmi.unict.it/tramonta/lessons/sd/socketJ.zip

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        InetAddress addr;

        if (args.length == 0)
            addr = InetAddress.getByName(null);
        else addr = InetAddress.getByName(args[0]);

        Socket socket = null;
        BufferedReader in = null, stdIn = null;
        PrintWriter out = null;

        try {
            // Create socket
            socket = new Socket(addr, EchoServer.PORT);
            System.out.println("EchoClient: started");
            System.out.println("Client Socket: " + socket);

            // Input Stream
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Output Stream
            out = new PrintWriter(socket.getOutputStream(), true);

            // Keyboard Input Stream
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;

            // ciclo di lettura da tastiera, invio al server e stampa risposta
            while (true) {
                userInput = stdIn.readLine();
                out.println(userInput);
            if (userInput.equals(EchoServer.SECRET)) {
                System.out.println("Killing Server with SECRET: " + in.readLine());
                break;
            }
                System.out.println("Server Response: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + addr);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + addr);
            System.exit(1);
        }
        System.out.println("EchoClient: closing...");
        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }
}
