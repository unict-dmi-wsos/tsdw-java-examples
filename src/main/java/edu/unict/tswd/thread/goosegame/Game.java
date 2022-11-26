package edu.unict.tswd.thread.goosegame;

// Class that manages the game
public class Game {
    int round=0;

    public int getRound(){
        return(round);
    }

    public synchronized void setRound(int k){
        round = k;
    }

    public synchronized void gameWait() throws InterruptedException {
        wait();
    }

    public synchronized void gameNotify() throws InterruptedException {
        notifyAll();
    }

    public void sleep() throws InterruptedException {
    Thread.sleep(500);
    }
}