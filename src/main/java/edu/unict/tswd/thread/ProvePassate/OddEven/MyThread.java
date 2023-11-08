package edu.unict.tswd.thread.ProvePassate.OddEven;
import java.util.Random;

public class MyThread extends Thread{
    private Random rand = new Random();
    private String threadName;
    private Shared shared;

    public MyThread(String name, Shared sh){
        this.threadName = name;
        this.shared = sh;
    }

    @Override
    public void run(){
        int iters = 0;
        int maxIters = 1000;
        if(threadName.equals("tE")){ // tE
            while(iters < maxIters){
                iters++;
                try{
                    shared.sleepSh(200);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }

                int r = (rand.nextInt()*2) % Integer.MAX_VALUE; // pari
                int n = shared.getN();

                shared.setN(n+r);
                if((shared.getN() % 2 == 0) && iters>=9){
                    System.out.println("[" + this.threadName + "] n = " + shared.getN());
                    break;
                }
            }
        }
        else{ // tO
            while(iters < maxIters){
                iters++;
                try{
                    shared.sleepSh(200);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
                int r = ((rand.nextInt() * 2) + 1) % Integer.MAX_VALUE;
                int n = shared.getN();

                shared.setN(n+r);

                if(iters >9 && shared.getN() % 2 == 1){
                    System.out.println("[" + this.threadName + "] n = " + shared.getN());
                    break;
                }
            }
        }
        if(iters >= maxIters)
            System.out.println("[" + this.threadName + "] superate iterazioni massime");
        System.out.println("[" + this.threadName + "] " + "termino...");
    }
}