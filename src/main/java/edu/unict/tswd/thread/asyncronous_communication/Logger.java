package edu.unict.tswd.thread.asyncronous_communication;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static Logger instance;
    private List<Socket> list_sock = new ArrayList<Socket>();

    private Logger() {}

    public static Logger getIstance() {
        if (instance == null) 
            instance = new Logger();

        return instance;
    }

    public static void update(Socket list_sock) {
        instance.list_sock.add(list_sock);
    }
    
    public static List getLog() {
        return instance.list_sock;
    }

}
