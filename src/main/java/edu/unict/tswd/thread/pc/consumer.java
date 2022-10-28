package edu.unict.tswd.thread.pc;
// Inspired by www.dmi.unict.it/tramonta/lessons/sd/jthread.tar
public class consumer extends Thread{
    private container cont;
    private final int EOF=-1;
    private String name;
    public consumer (container c, String name) {
        cont = c;
        this.name = name;
    }

    public void run() {
        System.out.println(name+" C: inizio");
        int num=0;
        while (num != EOF) {
            num = cont.get();
            System.out.println(name+" C: prelevo " + num);
        }
        System.out.println(name+" C: termino\n");
    }
}
