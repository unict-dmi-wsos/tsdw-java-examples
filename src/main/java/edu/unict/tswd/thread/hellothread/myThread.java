package edu.unict.tswd.thread.hellothread;
// Inspired by www.dmi.unict.it/tramonta/lessons/sd/jthread.tar

import java.util.Random;

public class myThread extends Thread {
    myThread() {
        System.out.println("myThread constructor");
    }
    public void run() {
        // Starting
        System.out.println("In run di myThread");
        Thread t = Thread.currentThread();
        System.out.println("myThread running pid "+t.toString());
        System.out.println(t.getName());
        for (int i = 0; i < 5000; i++) {
            try {
                System.out.print(">");
                sleep(1);
                //sleep(new Random().nextInt(5)); // Poor Ascii ART

                // CR at 40
                if ((i%40)== 0) {
                    System.out.println("");
                }
            } catch (InterruptedException e) { }
            // scrive un *
           // System.out.println("End #");
        }
    }

    public int mioGetState() {
        Thread t = Thread.currentThread();
        System.out.println("Il thread che esegue mioGetState della classe myThread e' "+t.toString());
        return 1;
    }
}
