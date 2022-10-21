package edu.unict.tswd.thread.hellothread;
// Inspired by www.dmi.unict.it/tramonta/lessons/sd/jthread.tar
import static java.lang.Thread.sleep;
import java.util.Random;

public class hello {
    public static void main(String[] args) throws InterruptedException {
        Thread tMain = Thread.currentThread();
        System.out.println("Main Thread "+tMain.toString());
        myThread t = new myThread();
        // Start thread
        t.start();
        // In parallel here
        for (int i = 0; i < 5000; i++) {
            System.out.print("<");
            sleep(1);
            //sleep(new Random().nextInt(5)); // Poor Ascii ART
            //if ( t.isAlive() ) System.out.print("alive");
        }

        System.out.println("");
        System.out.println("END Hello");
        try {
            // aspetta la fine del thread t
            t.join();
        } catch (InterruptedException e) { }

        // dopo che il thread t e' concluso, questo thread finisce
        if (! t.isAlive() ) System.out.println("T is dead W T");
        System.out.println(t.mioGetState());
        System.out.println("This is the end");
    }
}
