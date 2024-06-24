package edu.unict.tswd.thread.soccergame;

public class Team extends Thread {
    int position = 0;
    int index; // Index of the Thread
    Game game; // Game
    int goal=0;
    
    public int getGoal() {
        return goal;
    }

    private String teamName; // Name of the team

    public String getTeamName() {
        return teamName;
    }


    Team(int index, String teamName,Game game) {
        this.index = index;
        this.teamName=teamName;
        this.game = game;
    }

    
    @Override
    public void run() {
        int roundNumber;
        int time;
        int dice;
        int partial=0; // Variable to keep track of the number of consecutive "Keep Ball"

        while ((time=game.getTime()) <= 90) { // Main loop
            roundNumber=game.getRound();
            if (roundNumber == index) {
                System.out.println("Team "+teamName+" plays");
                System.out.println("🕑 ["+time+ "]");
                game.addTime(); // New Minute, incremented only by the thread of team that plays
                dice=rollDice(); // Roll the dice
                switch (dice) {
                    case 1: // Goal
                    System.out.println("⚽⚽⚽ GOAL ⚽⚽⚽");
                    partial=0;
                    goal++;
                    sleepAndNotify();
                    break;
                    case 2: // Loss Ball
                    System.out.println("↔ Loss Ball");
                    partial=0;
                    sleepAndNotify();
                    break;
                    case 3: // Keep Ball
                    System.out.println("Keep Ball");
                    partial += 1;
                    if (partial >=3 ) {
                        System.out.println("⏳ Keep Ball Timeout");
                        sleepAndNotify();
                    }
                    break;
                    
                    default:
                        break;
                }
               
            } else {
                 // Notify
                 try {
                    game.gameNotify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    //System.out.println("Team "+teamName+" waits");
                    game.gameWait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            
        }
        try {
            game.gameNotify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    int rollDice() {
        int dice = (int) (Math.random() * 3 + 1);
        System.out.println("\uD83C\uDFB2 extracted:" + dice);
        return (dice);
    }

    void sleepAndNotify(){
        game.setRound(1 - index);
        // Notify
        try {
            game.gameNotify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Go to sleep
        try {
            game.sleep();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
