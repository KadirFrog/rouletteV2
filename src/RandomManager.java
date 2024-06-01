import java.util.Random;

public class RandomManager {
    public static Random rand = new Random();
    static int randint(int min, int max) {
        return rand.nextInt(max - min + 1) + min;
    }
}
