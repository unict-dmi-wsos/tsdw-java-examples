package edu.unict.tswd.socket.tcp.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client{
    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("Usage: java Client <serveraddr>");
            System.exit(1);
        }

        InetAddress serverAddr = null;
        try{
            serverAddr = InetAddress.getByName(args[0]);
        } catch(UnknownHostException u){
            System.out.println("Not valid address");
            System.exit(1);
        }

        Socket socket = null;
        BufferedReader in = null, stdIn = null;
        PrintWriter out = null;


        try{
            socket = new Socket(serverAddr, BibliotecaServer.PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            stdIn = new BufferedReader(new InputStreamReader(System.in));

            String bookName = stdIn.readLine();
            out.println(bookName);
            System.out.print("Status: ");
            System.out.println(in.readLine());


        } catch(IOException e){
            System.out.println("Error: Server off");
            System.exit(1);
        }

        try{
            in.close();
            out.close();
            socket.close();
            stdIn.close();
        } catch(IOException e){
            System.out.println("close() error");
        }
    }
}