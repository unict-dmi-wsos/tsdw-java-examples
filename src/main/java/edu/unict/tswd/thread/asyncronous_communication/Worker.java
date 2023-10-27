package edu.unict.tswd.thread.asyncronous_communication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.STRING;

public class Worker extends Thread{
    private BufferedReader in;
    private PrintWriter out;
    private Socket sock;

    public Worker(Socket sock) {this.sock = sock;}

    public void closebuffer() {
        try {
            in.close();
            out.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()), true);

            String req;
            Logger.getIstance().update(sock);
            while((req = in.readLine()) != null) {
                if (req.equals("QUIT")) {
                    closebuffer();
                    sock.close();
                    break;
                } else if (req.equals("LOG")) {
                    out.println(Logger.getIstance().getLog());
                } else {
                    out.println("Comando sconosciuto");
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
