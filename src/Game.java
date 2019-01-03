import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {

    int gamesPlayed;
    int playerGamesWon;
    int aiGamesWon;

    Ai ai;

    Map<Integer, Integer> rules;

    Game() {
        this.gamesPlayed = 0;
        this.playerGamesWon = 0;
        this.aiGamesWon = 0;

        this.ai = new Ai();

        this.rules = new HashMap<>();

        this.rules.put(1, 3);
        this.rules.put(2, 1);
        this.rules.put(3, 2);
    }

    void playGame() {
        int aiPick = ai.pickMove();
        int playerPick;
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("rock paper scissors\n");
            String input = scan.nextLine();

            playerPick = getPlayersMove(input);
            if (playerPick != -1)
                break;
        }
        pickWinner(aiPick, playerPick);
        this.gamesPlayed++;
    }

    int getPlayersMove(String input) {
        switch (input) {
            case "rock": {
                return 1;
            }

            case "paper": {
                return 2;
            }

            case "scissors": {
                return 3;
            }

            default:
                return -1;
        }
    }

    void pickWinner(int aiPick, int playerPick) {
        System.out.println("Player - " + this.intToPick(playerPick) + "\nRobot - " + this.intToPick(aiPick) + "\n");

        if (aiPick == playerPick) {
            System.out.println("It's a tie, let's do this again!\n");
            return;
        }

        if (this.rules.get(playerPick) == aiPick) {
            System.out.println("You win!\n\n");
            this.playerGamesWon++;
        } else {
            System.out.println("You lost!\n\n");
            this.aiGamesWon++;
        }
    }


    private String intToPick(int move) {
        switch (move) {
            case 1: {
                return "rock";
            }

            case 2: {
                return "paper";
            }

            case 3: {
                return "scissors";
            }

            default:
                return null;
        }
    }
}
