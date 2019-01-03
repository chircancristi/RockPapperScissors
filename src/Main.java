import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to rock paper scissors!\n");
        Game game = new Game();
        while (true) {
            System.out.println("1 - Play a round\n");
            System.out.println("2 - Exit\n");
            String input = scan.nextLine();
            if (input.equals("1")) {
                System.out.println("\n\n\n");
                game.playGame();

                System.out.println("After " + game.gamesPlayed + " games, the score is: \n");
                System.out.println("Player - " + game.playerGamesWon);
                System.out.println("Robot - " + game.aiGamesWon);
            } else if (input.equals("2")) {
                break;
            } else {
                System.out.println("Invalid command try again!\n");
            }
        }

    }
}

