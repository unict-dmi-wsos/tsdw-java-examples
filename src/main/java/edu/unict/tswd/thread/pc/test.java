package edu.unict.tswd.thread.pc;
// Inspired by www.dmi.unict.it/tramonta/lessons/sd/jthread.tar
public class test {
    public static void main(String[] args) {
        container containerIstance = new container();
        producer p1 = new producer(containerIstance);
        consumer c1 = new consumer(containerIstance, "c1");
        // consumer c2 = new consumer(containerIstance, "c2");
        c1.start();
        // c2.start();
        p1.start();

    }
}
