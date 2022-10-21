package edu.unict.tswd.thread.pc;
// Inspired by www.dmi.unict.it/tramonta/lessons/sd/jthread.tar
public class consumer extends Thread{
    private container cont;
    private final int EOF=-1;

    public consumer (container c) {
        cont = c;
    }

    public void run() {
        System.out.println("C: inizio");
        int num=0;
        while (num != EOF) {
            num = cont.get();
            System.out.println("C: prelevo " + num);
        }
        System.out.println("C: termino\n");
    }
}
