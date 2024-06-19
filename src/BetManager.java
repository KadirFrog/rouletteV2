import java.util.HashMap;
import java.util.Map;

public class BetManager {
    static final int green = MainClass.processing.color(0, 255, 0);
    public static final int red = MainClass.processing.color(255, 0, 0);
    public static final int black = MainClass.processing.color(0, 0, 0);
    public static final int[] rouletteColors = {
            green, red, black, red, red, black, red, red, black, red, red, black, red, green,
            black, red, black, black, red, black, black, red, black, black, red, black, green,
            red, black, red, black, black, red, red, black, red, black, black, red, green
    };
    public static int money = 1000;
    public static int bet = 0;
    public static Map<String, Integer> bets = new HashMap<>();
    public static boolean bet_ready = false;

    public static void calculate_winnings(int digit) {
       try {
           money += bets.get(""+digit) * 36;
       } catch (Exception ignored) {}
       for (String key : bets.keySet()) {
           if (key.equals("EVEN") && digit % 2 == 0) {
               money += bets.get(key) * 200;
           } else if (key.equals("ODD") && digit % 2 != 0) {
               money += bets.get(key) * 200;
           } else if (key.equals("RED") && red == rouletteColors[digit]) {
               money += bets.get(key) * 200;
           } else if (key.equals("BLACK") && black == rouletteColors[digit]) {
               money += bets.get(key) * 200;
           } else if (key.equals("1 to 18") && digit >= 1 && digit <= 18) {
               money += bets.get(key) * 200;
           } else if (key.equals("19 to 36") && digit >= 19 && digit <= 36) {
               money += bets.get(key) * 200;
           }
       }
    }
}
