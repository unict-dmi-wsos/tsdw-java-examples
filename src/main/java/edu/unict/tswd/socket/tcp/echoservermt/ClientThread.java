package edu.unict.tswd.socket.tcp.echoservermt;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private static int counter = 0;
    private int id = counter++;
    private static int threadcount = 0;

    public static int threadCount() {
        return threadcount;
    }

    public ClientThread(InetAddress addr) {
        threadcount++;

        try {
            socket = new Socket(addr, Server.PORT);
            System.out.println("EchoClient n "+id+": started");
            System.out.println("Client Socket: "+ socket);
        } catch(IOException e) {}
        // Se la creazione della socket fallisce non � necessario fare nulla

        try {
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            in = new BufferedReader(isr);
            OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
            out = new PrintWriter(new BufferedWriter(osw), true);
            start();
        } catch(IOException e1) {
            // in seguito ad ogni fallimento la socket deve essere chiusa, altrimenti
            // verr� chiusa dal metodo run() del thread
            try {
                socket.close();
            } catch(IOException e2) {}
        }
    }

    public void run() {
        try {
            for(int i =0;i <10; i++) {
                out.println("client "+id +" msg "+i);
                System.out.println("Msg sent: client "+id+" msg"+i);
                String str = in.readLine();
                System.out.println("Echo: "+str);
            }
            out.println("END");
        } catch(IOException e) {}
        try {
            System.out.println("Client "+id+" closing...");
            socket.close();
        } catch(IOException e) {}
        threadcount--;
    }
}
