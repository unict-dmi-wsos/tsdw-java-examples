package edu.unict.tswd.thread.hellothread;
// Inspired by www.dmi.unict.it/tramonta/lessons/sd/jthread.tar
import static java.lang.Thread.sleep;

public class hello2 {
    public static void main(String[] args) throws InterruptedException {
        // Main is a deamon
        Thread tMain = Thread.currentThread();
        System.out.println("Main Thread "+tMain.toString());
        myThread2 t = new myThread2();
        // Start thread
        t.start();
        // Create another thread
        myRunnable myRunnable = new myRunnable();
        Thread myRunnableThread = new Thread(myRunnable);
        // Start
        myRunnableThread.start();

        // In parallel here
        for (int i = 0; i < 10; i++) {
            System.out.println("<");
        }

        System.out.println("");
        System.out.println("This is the end");
    }
}
