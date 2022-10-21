package edu.unict.tswd.thread.pc;
// Inspired by www.dmi.unict.it/tramonta/lessons/sd/jthread.tar
public class container {
    private int contents;
    private boolean available = false;

    public synchronized int get() {
        while (available == false) {
            try {
                // attende che un dato sia disponibile
                wait();
            } catch (InterruptedException e) { }
        }
        available = false;
        // notifica che un dato e' stato consumato ed
        // un produttore puo' inserirne un altro
        notifyAll();
        return contents;
    }

    public synchronized void put(int value) {
        while (available == true) {
            try {
                // attende che un dato sia consumato
                wait();
            } catch (InterruptedException e) { }
        }
        contents = value;
        available = true;
        // notifica che un dato e' stato inserito
        notifyAll();
    }
}
