package edu.unict.tswd.thread.dicegame;


public class Player extends Thread {
    
    int wallet;
    int budget;
    String name;
    Banco banco;
    int matchPlayed;

    Player(String name, int wallet, Banco banco) {
        this.wallet = wallet;
        this.budget = wallet;
        this.name = name;
        this.banco = banco;
        this.matchPlayed=0;
    }

    @Override
    public void run() {
        banco.increaseNumPlayers();
        while (true) {
            System.out.println("["+name+"] would like to play, checking games availability...");
            if (banco.getMatchesCounter()==0) {
                System.out.println("["+name+"] no more games available! Bye!");
                break; // If there are no more matches, stop playing
            }
            banco.decreaseMatchCounters(); // Decrease match counter            
            this.matchPlayed++; // Increase coin
            System.out.println("["+name+"] playing my "+matchPlayed+" match, there are ... "+banco.getMatchesCounter()+" matches left");

            int dice=rollDice(); // Roll Dice
            printStatus(); // Print Status
            int potentialOutcome=outcome(dice); // Calculate Outcome
            System.out.println("["+name+"] Potential outcome: "+potentialOutcome);
            wallet+=(potentialOutcome<0) ? pay(potentialOutcome) : banco.getMoney(name,potentialOutcome); // Pay or Get
            printStatus(); // Print Status
            if (wallet==0) {
                System.out.println("["+name+"] has no money left!");
                break; // If wallet is empty, stop playing
            }
            if (banco.getJackpot()>=3) { // If there are at least 3 coins in the jackpot
                banco.resumeOtherGames(); // Resume other games
            }
         
        }
        banco.decreaseNumPlayers();
    }
    
    int pay(int outcome) {
       int newWallet = wallet + outcome;
         if (newWallet <= 0) {
              System.out.println("["+name+"] has only " + wallet + " and have to pay " + outcome + "!");
              this.banco.addToJackpot(wallet);
              return -wallet; // I can pay only what I have
         } else {
              this.banco.addToJackpot(-outcome);
         }
         return outcome;
    }

    int rollDice() {
        int dice = (int) (Math.random() * 6 + 1);
        System.out.println("["+name+"] \uD83C\uDFB2:" + dice);
        return (dice);
    }

    int outcome(int dice){
        int outcome = 0;
        switch (dice) {
                case 1:
                    outcome = -2; // Pay 2
                    break;
                case 2:
                    outcome = -1; // Pay 1
                    break;
                case 4:
                    outcome = 1; // Get 1
                    break;
                case 5:
                    outcome = 2; // Get 2
                    break;
                case 6:
                    outcome = 3; // Get 3
                    break;
        }
        return outcome;
    }

    void printStatus() {
        System.out.println("["+name+"] Matches "+ matchPlayed+" Wallet \uD83D\uDC5B " + wallet + "  Initial: "+budget+" Jackpot \uD83C\uDFB0 " + banco.getJackpot() + " Num players playing:"+banco.getNumPlayers());
    }
}
