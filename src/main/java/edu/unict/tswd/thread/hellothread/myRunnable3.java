package edu.unict.tswd.thread.hellothread;

import static java.lang.Thread.sleep;

public class myRunnable3 implements Runnable {
    @Override
    public void run() {
        // Starting
        System.out.println("In run di myRunnable");
        Thread t = Thread.currentThread();
        System.out.println("myRunnable running pid "+t.toString());
        System.out.println(t.getName());
        for (int i = 0; i < 5000; i++) {
                System.out.println("\t_");
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
