package edu.unict.tswd.thread.hellothread;

public class hello21builder {
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

        Thread.Builder builder = Thread.ofPlatform().name("worker-", 0);

        for (int j=0;j<10;j++) {
            Thread t = builder.start(fn);   // name "worker-0"
            System.out.println("Name of thread"+t.getName());
        }
        System.out.println("This is the end");
    }
}
