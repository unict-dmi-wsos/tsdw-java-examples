package edu.unict.tswd.thread.soccergame;

public class Game {
    int round=0; // First team to play
    int time=0; // Time in minute of the game

    public int getRound(){
        return(round);
    }

    public int getTime(){
        return(time);
    }

    public synchronized void addTime(){
        time+=1;
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
    Thread.sleep(10);
    }
}
