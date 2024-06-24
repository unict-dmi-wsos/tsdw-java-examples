package edu.unict.tswd.thread.dicegame;

public class Game {
    public static void main(String[] args) throws InterruptedException {
        Banco banco = new Banco(10, 100);
        Player p1 = new Player("Paperino", 0, banco);
        Player p2 = new Player("Gastone", 100, banco);
        Player p3 = new Player("Paperone", 10, banco);
        JackpotRefiller refiller = new JackpotRefiller(banco, 100);
     
        p1.start(); 
        p2.start();
       
        refiller.start(); // Be careful with start of refiller, since checks number of players
        // Thread.sleep(5000); Consider to make other player start later 
        p3.start();
        
        p1.join();
        p2.join();
        p3.join();
        refiller.join();
        System.out.println("*** Game Over ***");
        p1.printStatus();
        p2.printStatus();
        p3.printStatus();
    }
}