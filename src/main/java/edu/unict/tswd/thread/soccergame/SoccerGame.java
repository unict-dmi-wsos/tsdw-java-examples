package edu.unict.tswd.thread.soccergame;

public class SoccerGame {
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        Team t0=new Team(0,"Italia",game);
        Team t1=new Team(1,"Croazia",game);
        t0.start();
        t1.start();
        t0.join();
        t1.join(); 
        System.out.println("Final Score");
        System.out.println(t0.getTeamName()+" vs "+t1.getTeamName() + " "+t0.getGoal()+ "-" +t1.getGoal());
        System.out.println("****** Game Over ******");
    }
}
