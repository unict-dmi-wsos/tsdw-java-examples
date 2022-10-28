package edu.unict.tswd.thread.pc;
// Inspired by www.dmi.unict.it/tramonta/lessons/sd/jthread.tar
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class producer extends Thread{
    private container cont;
    private final int EOF=-1;
    private BufferedReader stdIn=new BufferedReader(new InputStreamReader(System.in));

    public producer(container c) {
        cont = c;
    }

    public void run() {
        System.out.println("P: inizio");
        int num;
        // chiedo 3 numeri all'utente, tento di inserirne 6 su cont
        for (int k=0; k<3; k++) {
            try {
                System.out.print("P: immettere un numero > ");
                num = Integer.parseInt(stdIn.readLine());
                cont.put(num);
                System.out.println("P: ho inserito "+num);
                // la seguente obbliga il produttore ad aspettare che cont sia libero
                cont.put(++num);
                System.out.println("P: ho inserito "+num);
            }
            catch(Exception e) { e.printStackTrace(); }
        }
        System.out.println("P: termino");
        cont.put(EOF);
    }
}
