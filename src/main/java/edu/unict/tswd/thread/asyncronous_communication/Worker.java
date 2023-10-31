package edu.unict.tswd.thread.asyncronous_communication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import edu.unict.tswd.thread.pc.producer;


/**
 * Thread che gestisce le richieste inviate al server
 */
public class Worker extends Thread{
    private BufferedReader in;
    private PrintWriter out;
    private Socket sock;
    private Server s;

    public Worker(Socket sock, Server s) {
        this.sock = sock;
        this.s = s;
    }

    /**
     * Metodo per chiudere tutti i buffer 
     */
    public void closebuffer() {
        try {
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo che stampa sul buffer di output il log
     */
    private void printLog() {
        try {
            List<Socket> log = Logger.getIstance().getLog();
            
            int num_user = 0;
            for (Socket el: log) {
                out.println("Utente #" + num_user + ": " + el);
                num_user++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Corpo del thread
     */
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()), true);

            String req;
            Logger log = Logger.getIstance();

            log.update(sock);
            while((req = in.readLine()) != null) {
                if (req.equals("QUIT")) {
                    log.delete_user(sock);
                    closebuffer();
                    sock.close();
                    break;
                } else if (req.equals("LOG")) {
                    printLog();
                }else {
                    out.println("Comando sconosciuto");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
