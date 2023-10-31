package edu.unict.tswd.thread.asyncronous_communication;

import sun.misc.Signal;
import sun.misc.SignalHandler;

public class Main {
    public static void main(String[] args) {
        Server s = new Server();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Closing all connection after CTRL-C");
                s.close_connection();
            }
        });
    }    
}
