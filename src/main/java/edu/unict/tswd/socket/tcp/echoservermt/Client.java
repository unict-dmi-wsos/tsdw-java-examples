package edu.unict.tswd.socket.tcp.echoservermt;

import java.io.IOException;
import java.net.InetAddress;

public class Client {
    static final int MAX_THREADS = 1;

    public static void main(String[] args) throws IOException,InterruptedException {
        InetAddress addr;
        if (args.length == 0) addr = InetAddress.getByName(null);
        else addr = InetAddress.getByName(args[0]);

        while(true) {
            if (ClientThread.threadCount() < MAX_THREADS)
                new ClientThread(addr);
            Thread.currentThread().sleep(1000);
        }
    }
}
