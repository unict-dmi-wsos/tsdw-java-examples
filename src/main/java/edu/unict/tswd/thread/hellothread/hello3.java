package edu.unict.tswd.thread.hellothread;
// Inspired by www.dmi.unict.it/tramonta/lessons/sd/jthread.tar
import static java.lang.Thread.sleep;

public class hello3 {
    public static void main(String[] args) throws InterruptedException {
        // Main is a deamon
        Thread tMain = Thread.currentThread();
        System.out.println("Main Thread "+tMain.toString());
        myThread3 t = new myThread3();
        // Start thread
        t.start();
        // Create another thread
        myRunnable3 myRunnable = new myRunnable3();
        Thread myRunnableThread = new Thread(myRunnable);
        // Start
        myRunnableThread.start();

        // In parallel here
        for (int i = 0; i < 5000; i++) {
            System.out.print("<");
            sleep(1);
            //sleep(new Random().nextInt(5)); // Poor Ascii ART
        }
        System.out.println("This is the end");
    }
}
