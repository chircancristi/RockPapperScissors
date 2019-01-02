public class Game {
    int gamesPlayed=0,playerGamesWon=0,aiGamesWon=0;
    Ai ai=new Ai();

    void playGame(){
        int aiPick=ai.pickMove();
        int playerPick;

         while(true) {
            System.console().writer().println("rock paper scissors\n");
            String input = System.console().readLine();

            playerPick=getPlayersMove(input);
            if (playerPick!=-1) break;
        }
        pickWinner(aiPick,playerPick);
    }
    int getPlayersMove(String input){
        int playerPick;
        if (input.equals("rock")==true) {
            playerPick = 1;
        }
        else {
            if (input.equals("paper")==true) {
                playerPick = 3;
            }
            else{
                if (input.equals("scissors")==true) {
                    playerPick = 3;
                }
                else {
                    System.console().writer().println("There is no such thing, please try again\n");
                    playerPick= -1;
                }
            }
        }
        return playerPick;
    }
    void pickWinner(int aiPick , int playerPick){
        if (aiPick==playerPick){
            System.console().writer().println("It's a tie, let's do this again!\n");
            playGame();
        }
        else {
            if (playerPick==1 && aiPick==2){
                System.console().writer().println("Player - Rock\n" + "Robot - Paper\n");
                System.console().writer().println("You lost! \n");
                this.aiGamesWon++;
            }
            if (playerPick==1 && aiPick==3){
                System.console().writer().println("Player - Rock\n" + "Robot - Scissors\n");
                System.console().writer().println("You win! \n");
                this.playerGamesWon++;
            }
            if (playerPick==2 && aiPick==1){
                System.console().writer().println("Player - Paper\n" + "Robot - Rock\n");
                System.console().writer().println("You win! \n");
                this.playerGamesWon++;
            }
            if (playerPick==2 && aiPick==3){
                System.console().writer().println("Player - Paper\n" + "Robot - Scissors\n");
                System.console().writer().println("You lost! \n");
                this.aiGamesWon++;
            }
            if (playerPick==3 && aiPick==1){
                System.console().writer().println("Player - Scissors\n" + "Robot - Rock\n");
                System.console().writer().println("You lost! \n");
                this.aiGamesWon++;
            }
            if (playerPick==3 && aiPick==2){
                System.console().writer().println("Player - Scissors\n" + "Robot - Paper\n");
                System.console().writer().println("You win! \n");
                this.playerGamesWon++;
            }
            this.gamesPlayed++;
            System.console().writer().println("Enter any key to continue\n");
            String input = System.console().readLine();
        }
    }
}
