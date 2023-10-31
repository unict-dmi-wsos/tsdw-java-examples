package edu.unict.tswd.thread.asyncronous_communication;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static Logger instance;
    private List<Socket> list_sock = new ArrayList<Socket>();

    private Logger() {}

    /**
     * Ritorna l'unica istanza del logger
     * 
     * @return istanza del logger
     */
    public static Logger getIstance() {
        if (instance == null) 
            instance = new Logger();

        return instance;
    }

    /**
     * Aggiorna la lista degli utenti connessi
     * 
     * @param sock Socket passata per aggiornare la lista del log
     */
    public static void update(Socket sock) {
        instance.list_sock.add(sock);
    }

    /**
     * Elimina un utente che si è disconnesso dal server
     * 
     * @param socket Socket da eliminare dalla lista poichè ha chiuso la connessione
     */
    public static void delete_user(Socket socket) {
        instance.list_sock.remove(socket);
    }


    /**
     * Ritorna il log attuale
     * 
     * @return lista del log
     */
    public static List getLog() {
        return instance.list_sock;
    }

}
