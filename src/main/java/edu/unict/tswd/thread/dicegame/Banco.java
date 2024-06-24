package edu.unict.tswd.thread.dicegame;

public class Banco {
    private int jackpot; // Quantity of money available to be won
    private int matchesCounter; // Max number of mathes 
    private int numPlayers; // used to count how ma

    public Banco(int jackpot, int matchesCounter) {
        this.jackpot = jackpot;
        this.matchesCounter = matchesCounter;
        this.numPlayers=0;
    }

    public synchronized void increaseNumPlayers() {
        numPlayers++;
    }

    public synchronized void decreaseNumPlayers() {  
        numPlayers--;
        // Check if is 0 or less signal to wakeup ?
    ;
    }

    public int getNumPlayers() {
        return(numPlayers);
    }

    public synchronized int getJackpot() {
        return jackpot;
    }

    public synchronized void addToJackpot(int jackpot) {
        this.jackpot += jackpot;
    }
    
    public synchronized int getMoney(String name, int money) {
        while (this.jackpot - money <= 0) {
            try {
                System.out.println("["+name+"] Not enough money in the jackpot, waiting...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } // Here we are sure that there is enough money in the jackpot
        this.jackpot -= money; // Update jackpot;
        return money;
    }

    public synchronized void resumeOtherGames() {
        notifyAll();
    }

    public synchronized void decreaseMatchCounters() {
        matchesCounter--;
    }

    public int getMatchesCounter() {
        return matchesCounter;
    }
}
