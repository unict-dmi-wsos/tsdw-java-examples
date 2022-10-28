package edu.unict.tswd.thread.hellothread;

public class myRunnable2 implements Runnable {
    @Override
    public void run() {
        // Starting
        System.out.println("In run di myRunnable");
        Thread t = Thread.currentThread();
        System.out.println("myRunnable running pid "+t.toString());
        System.out.println(t.getName());
        for (int i = 0; i < 10; i++) {
                System.out.println("\t_");
        }
    }
}
