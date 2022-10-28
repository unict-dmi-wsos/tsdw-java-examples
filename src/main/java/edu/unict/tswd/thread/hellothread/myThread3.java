package edu.unict.tswd.thread.hellothread;
// Inspired by www.dmi.unict.it/tramonta/lessons/sd/jthread.tar

public class myThread3 extends Thread {
    myThread3() {
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
                System.out.println("\t\t>");
                sleep(1);
                //sleep(new Random().nextInt(5)); // Poor Ascii ART
            } catch (InterruptedException e) { }

        }
    }
}
