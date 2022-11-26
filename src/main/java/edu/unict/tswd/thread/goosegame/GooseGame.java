package edu.unict.tswd.thread.goosegame;

public class GooseGame {
    public static void main(String[] args) throws InterruptedException {
        Game game =new Game();
        Player t0=new Player(0,game);
        Player t1=new Player(1,game);
        t0.start();
        t1.start();
        t0.join();
        t1.join();
        System.out.println("****** Game Over ******");
    }
}
