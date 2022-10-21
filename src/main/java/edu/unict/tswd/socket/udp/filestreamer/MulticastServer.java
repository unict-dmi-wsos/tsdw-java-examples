package edu.unict.tswd.socket.udp.filestreamer;
// Inspired by www.dmi.unict.it/tramonta/lessons/sd/socketJ.zip
// MulticastServer.java

import java.io.*;
import java.net.*;

public class MulticastServer {
	
    public static final int PORT = 4446; 
    // porta al di fuori del range 1-1024 !
    // dichiarata come statica perchè caratterizza il server

    public static final String FILE = "resources/saggezza.txt";
    static BufferedReader in = null;
    static boolean moreLines = true; 		
		
    public static void main(String[] args) { 
        long WAIT = 1000;
        int count=0;
        MulticastSocket socket = null;
        String currentPath = null;
        try {
            currentPath = new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Current dir:" + currentPath);

        String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System:" + currentDir);

        System.out.println("MulticastServer: avviato");
      
        // creazione della socket datagram
        try {
            socket = new MulticastSocket(PORT);
            System.out.println("Socket: " + socket);
    	} 
        catch (IOException e) {
            System.out.println("Problemi nella creazione della socket: ");
            e.printStackTrace();
            System.exit(1);
        }
        // associazione di uno stream di input al file da cui estrarre le linee
        try {
            in = new BufferedReader(new FileReader(FILE));
            System.out.println("File "+FILE+" aperto");
        } 
        catch (FileNotFoundException e) {
            System.out.println("Problemi nell'apertura del file: ");
            e.printStackTrace();
            System.exit(2);
        }
        // creazione del gruppo associato all'indirizzo multicast
        InetAddress group=null;
        try{
            group = InetAddress.getByName("231.0.0.1");
            socket.joinGroup(group);
            System.out.println("Creazione del gruppo "+ group);
        }
        catch (IOException e) {
            System.out.println("Problemi nella creazione del gruppo: ");
            e.printStackTrace();
            System.exit(3);
        }

        while (moreLines) {
            count++;
            byte[] buf = new byte[256];
            // estrazione della linea               
            String linea = LineUtility.getNextLine(in);
            if (linea.equals("Nessuna linea disponibile"))
                moreLines = false;
            System.out.println("Estratta linea # "+count+" :   "+linea);

            // costruzione del datagramma contenente la linea 
            try {
                DatagramPacket packet = DatagramUtility.buildPacket(group, PORT, linea);
                // invio della linea al gruppo
                socket.send(packet);
                System.out.println("Linea inviata");
            }
            catch (Exception e) {
                System.out.println("Problemi nell'invio del datagramma: ");
                e.printStackTrace();
                continue;
            }

            // attesa tra un invio e l'altro...
            try {
                Thread.sleep((long)(Math.random() * WAIT));
            } catch (InterruptedException e) { }
        } // while
        System.out.println("File terminato");
        System.out.println("MulticastServer: termino...");
        socket.close();
    }
}