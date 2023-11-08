package edu.unict.tswd.thread.ProvePassate.Sample;

import java.util.Random;

public class MyThread extends Thread{
    Random rand = new Random();
    private int val;
    private int tid;
    private Shared shared;

    public MyThread(int tid, Shared sh){
        shared = sh;
        this.tid = tid;
    }

    @Override
    public void run(){
        while(true){
            this.val = rand.nextInt(81) + 10;

            System.out.println("[T" + this.tid + "] : sample valeva " + shared.getSample() + ", il suo futuro valore e' " + val);
            if(val == shared.getSample())
                break;
                
            shared.setSample(val);

        }

        System.out.println("T" + tid + " termino...");
    }
}