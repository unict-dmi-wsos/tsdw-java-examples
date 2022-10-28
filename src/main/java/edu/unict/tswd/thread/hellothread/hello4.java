package edu.unict.tswd.thread.hellothread;
// Inspired by www.dmi.unict.it/tramonta/lessons/sd/jthread.tar
import java.util.Random;

import static java.lang.Thread.sleep;

public class hello4 {
    public static void main(String[] args) throws InterruptedException {
        // Main is a deamon
        Thread tMain = Thread.currentThread();
        System.out.println("Main Thread "+tMain.toString());
        myThread4 t = new myThread4();
        // Start thread
        t.start();
        // Create another thread
        myRunnable4 myRunnable = new myRunnable4();
        Thread myRunnableThread = new Thread(myRunnable);
        // Start
        myRunnableThread.start();

        // In parallel here
        for (int i = 0; i < 5000; i++) {
            System.out.println("<");
            sleep(new Random().nextInt(5)); // Poor Ascii ART
            //if ( t.isAlive() ) System.out.print("alive");
        }
        System.out.println("");

        try {
            // aspetta la fine dei thread
            t.join();
            myRunnableThread.join();
        } catch (InterruptedException e) { }

        // dopo che il thread t e' concluso, questo thread finisce
        System.out.println("T status:"+t.getState());
        System.out.println("myRunnableThread status:"+myRunnableThread.getState());
        System.out.println("This is the end");
    }
}
