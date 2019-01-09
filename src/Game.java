import GameComponents.Move;
import GameComponents.Rules;

import java.util.Scanner;

public class Game {

    int gamesPlayed;
    int playerGamesWon;
    int aiGamesWon;

    Ai ai;

    Rules rules;

    Game() {
        this.gamesPlayed = 0;
        this.playerGamesWon = 0;
        this.aiGamesWon = 0;

        this.ai = new Ai();

        this.rules = new Rules();
    }

    void playGame() {
        int aiPick = ai.pickMove();
        Move aiMove = this.rules.getMoveWithNumericValue(aiPick);

        Scanner scan = new Scanner(System.in);
        String input;
        Move playerMove;

        while (true) {
            System.out.println("rock paper scissors\n");

            input = scan.nextLine();

            playerMove = this.rules.getMoveWithName(input);
            if (playerMove != null){
                break;
            } else {
                System.out.println("UNACCEPTABLE! Pick again!");
            }

        }

        pickWinner(aiMove, playerMove);
        this.gamesPlayed++;
    }

    void pickWinner(Move aiMove, Move playerMove) {
        System.out.println("Player - " + playerMove.name + "\nRobot - " + aiMove.name + "\n");

        switch (this.rules.joust(playerMove, aiMove)){
            case 1: {
                System.out.println("You win!\n\n");
                this.playerGamesWon++;
                this.ai.didIWin = -1;
                break;
            }

            case -1: {
                System.out.println("You lost!\n\n");
                this.aiGamesWon++;
                this.ai.didIWin = 1;
                break;
            }

            case 0: {
                System.out.println("It's a tie, let's do this again!\n");
                this.ai.didIWin = 0;
                break;
            }
        }
        if (this.ai.aiLastSixMoves.size()<7){
            this.ai.aiLastSixMoves.add(playerMove);
        }
        else{
            this.ai.aiLastSixMoves.removeFirst();
            this.ai.aiLastSixMoves.add(playerMove);

        }
        this.ai.aiMoves.add(aiMove);
        this.ai.playerMoves.add(playerMove);

    }
}
