package edu.unict.tswd.thread.asyncronous_communication;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server che mantiene un log dei client che si sono connessi
 * 
 * Comandi:
 * 
 * 1. LOG -> Il client può chiedere di conoscere tutti gli utenti connessi fino a quel momento
 * 2. QUIT -> chiudere la propria connessione con il server
 */
public class Server {
    private Worker log;
    private ServerSocket serv_sock;
    private Socket client_sock;
    private int port = 8000;

    public Server() {
        start_server();
        run();
    }

    /**
     * Metodo che inizializza la socket del server
     */
    public void start_server() {
        try {
            serv_sock = new ServerSocket(port);
            serv_sock.setReuseAddress(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo in cui il server accetta nuove connessioni e lascia gestire le richieste a un worker
     */
    public void run() {
        while (true) {
            try {
                if (serv_sock != null) {
                    client_sock = serv_sock.accept();
                    log = new Worker(client_sock, this);
                    log.start();
                } else {
                    System.out.println("serv_sock is null");
                    System.exit(-1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void close_connection() {
        try {
            serv_sock.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
