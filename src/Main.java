public class Main {

    public static void main(String[] args) {

        System.console().writer().println("Welcome to rock paper scissors!\n");
        Game game=new Game();
        while (true){
            System.console().writer().println("1 - Play a round\n");
            System.console().writer().println("2 - Exit\n");
            String input = System.console().readLine();
            if (input.equals("1")){
                System.console().writer().println("\n\n\n\n\n\n");
                game.playGame();
                System.console().writer().println("\n\n\n\n\n\n");
                System.console().writer().println("After "+ game.gamesPlayed + " games, the score is: \n");
                System.console().writer().println("Player - " +  game.playerGamesWon);
                System.console().writer().println("Robot - "  +  game.aiGamesWon);
            }
            else {
                if (input.equals("2")) {
                    System.console().writer().println("Thank you for playing !\n");
                    break;
                }
                else{
                    System.console().writer().println("Invalid command try again!\n");

                }
            }


        }
    }
}
