package edu.unict.tswd.thread.dicegame;

public class JackpotRefiller extends Thread {
    private Banco banco;
    private int refillAmount;

    public JackpotRefiller(Banco banco, int refillAmount) {
        this.banco = banco;
        this.refillAmount = refillAmount;
    }

    @Override
    public void run() {
        while (true) {
            if (banco.getMatchesCounter()==0) {
                System.out.println("[BANCO] no more games available! Bye!");
                banco.resumeOtherGames();
                break; // If there are no more matches, stop playing
            }

            if (banco.getNumPlayers()==0) {
                System.out.println("[BANCO] no more players available! Bye!");
                banco.resumeOtherGames();
                break; 
            }

            // What happens if all players have no money ?
            if (banco.getJackpot() < 3) {
                banco.addToJackpot(refillAmount);
                System.out.println("[BANCO] \uD83D\uDCB0 Refilled " + refillAmount + " coins in the jackpot");
                banco.resumeOtherGames();
            }

            try {
                Thread.sleep(5000); // Wait 5 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}