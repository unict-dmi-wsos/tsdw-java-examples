package edu.unict.tswd.socket.tcp.echoservermt;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{
    private static int counter = 0;
    private int id = ++counter;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ServerThread(Socket s) throws IOException {
        socket = s;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
        out = new PrintWriter(new BufferedWriter(osw), true);
        start();
        System.out.println("ServerThread "+id+": started");
        System.out.println("Socket: " + s);
    }
    public void run() {
        try {
            while (true) {
                String str = in.readLine();
                if (str.equals("END")) break;
                System.out.println("ServerThread "+id+": echoing -> " + str);
                out.println(str);
            }
            System.out.println("ServerThread "+id+": closing...");
        } catch (IOException e) {}
        try {
            socket.close();
        } catch(IOException e) {}
    }
}
