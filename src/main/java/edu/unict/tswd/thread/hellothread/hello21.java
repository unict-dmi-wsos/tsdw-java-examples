package edu.unict.tswd.thread.hellothread;
import java.util.Random;

import static java.lang.Thread.sleep;

public class hello21  {
    public static void main(String[] args) throws InterruptedException {
        // Main is a deamon
        Thread tMain = Thread.currentThread();
        System.out.println("Main Thread "+tMain.toString());
        // Java Lambda Expression Example: Thread Example
        // See https://www.codejava.net/java-core/the-java-language/java-8-lambda-runnable-example
        
        Runnable fn = () -> {
            for (int i=0; i<10; i++) {
                System.out.println("Hello from a thread!");
            }
          };

        // Start a daemon thread to run a task
        Thread thread = Thread.ofPlatform().name("Hello").start(fn);
    
        try {
            // aspetta la fine dei thread
            thread.join();
        } catch (InterruptedException e) { }

        // dopo che il thread t e' concluso, questo thread finisce
        System.out.println("T status:"+thread.getState());
        System.out.println("This is the end");
    }
}
