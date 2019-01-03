import java.util.Random;

public class Ai {

    int pickMove(){
        Random random = new Random();
        return random.nextInt(3) + 1;
    }
}
