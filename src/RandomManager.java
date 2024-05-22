import java.util.Random;

public class RandomManager {
    Random rand = new Random();
    int randint(int min, int max) {
        return rand.nextInt(max - min + 1) + min;
    }
}
