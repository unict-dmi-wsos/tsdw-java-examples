package edu.unict.tswd.thread.asyncronous_communication;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
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

    public Server() {
        start_server();
        run();
    }

    public void start_server() {
        try {
            serv_sock = new ServerSocket(8000);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                client_sock = serv_sock.accept();
                log = new Worker(client_sock);
                log.start();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
}
