import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

import java.awt.*;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

public class BettingMenu extends PApplet {
    public static PApplet processing;
    public static int window_width, window_height;
    public static float unit_x, unit_y, unit;
    public int current_bet = 0;
    public int money;
    private final int mainTableRows = 3;
    private final int mainTableCols = 13;
    private final int table4Cols = 3;
    private final int table5Cols = 6;
    private boolean no_money = false;
    private Button[][] mainButtons;
    private Button[] table4Buttons;
    private Button[] table5Buttons;
    private int[][] buttonStates;
    Button submitButton;
    final String[] rouletteNumbers = {
            "0", "3", "6", "9", "12", "15", "18", "21", "24", "27", "30", "33", "36", "2 to 1",
            "2", "5", "8", "11", "14", "17", "20", "23", "26", "29", "32", "35", "2 to 1",
            "1", "4", "7", "10", "13", "16", "19", "22", "25", "28", "31", "34", "2 to 1"
    };
    final String[] table4Labels = {"1st 12", "2nd 12", "3rd 12"};
    final String[] table5Labels = {"1 to 18", "EVEN", "RED", "BLACK", "ODD", "19 to 36"};

    public void c_size(int width, int height) {
        window_width = width;
        window_height = height;
        unit_x = (float) width / 200;
        unit_y = (float) height / 200;
        unit = (unit_x * unit_y) / 2;
        size(width, height);
    }

    public void settings() {
        c_size(800, 800);
    }

    public void setup() {
        processing = this;
        windowTitle("Betting Menu | Roulette V2");
        background(0);
        money = BetManager.money;
        int buttonWidth = 50;
        int buttonHeight = 50;

        mainButtons = new Button[mainTableRows][mainTableCols];
        table4Buttons = new Button[table4Cols];
        table5Buttons = new Button[table5Cols];

        buttonStates = new int[mainTableRows + 2][];
        for (int i = 0; i < mainTableRows; i++) {
            buttonStates[i] = new int[mainTableCols];
        }
        buttonStates[mainTableRows] = new int[table4Cols];
        buttonStates[mainTableRows + 1] = new int[table5Cols];

        final int green = color(0, 255, 0);
        final int red = color(255, 0, 0);
        final int black = color(0, 0, 0);

        final int[] rouletteColors = {
                green, red, black, red, red, black, red, red, black, red, red, black, red, green,
                black, red, black, black, red, black, black, red, black, black, red, black, green,
                red, black, red, black, black, red, red, black, red, black, black, red, green
        };

        processing = this;
        windowTitle("Betting Menu | Roulette V2");
        background(0);

        int current = 0;
        for (int i = 0; i < mainTableRows; i++) {
            for (int j = 0; j < mainTableCols; j++) {
                int x = j * buttonWidth;
                int y = i * buttonHeight;
                mainButtons[i][j] = new Button(rouletteNumbers[current], x, y, buttonWidth, buttonHeight, rouletteColors[current]);
                current++;
            }
        }
        int oldButtonWidth = buttonWidth;
        buttonWidth *= 4;
        for (int i = 0; i < table4Cols; i++) {
            int x = i * buttonWidth + oldButtonWidth;
            int y = mainTableRows * buttonHeight;
            table4Buttons[i] = new Button(table4Labels[i], x, y, buttonWidth, buttonHeight, green);
        }

        buttonWidth /= 2;
        final int[] color2 = {green, green, red, black, green, green};
        for (int i = 0; i < table5Cols; i++) {
            int x = i * buttonWidth + oldButtonWidth;
            int y = (mainTableRows + 1) * buttonHeight;
            table5Buttons[i] = new Button(table5Labels[i], x, y, buttonWidth, buttonHeight, color2[i]);
        }
        submitButton = new Button("Submit bet", (int) (100*unit_x), (int) (150*unit_y), (int) (50*unit_x), (int) (20*unit_y), color(212, 175, 55));
    }

    public void draw() {
        background(50);
        for (int i = 0; i < mainTableRows; i++) {
            for (int j = 0; j < mainTableCols; j++) {
                Button button = mainButtons[i][j];
                int pressCount = buttonStates[i][j];
                button.display(pressCount);
            }
        }
        for (int i = 0; i < table4Cols; i++) {
            Button button = table4Buttons[i];
            int pressCount = buttonStates[mainTableRows][i];
            button.display(pressCount);
        }
        for (int i = 0; i < table5Cols; i++) {
            Button button = table5Buttons[i];
            int pressCount = buttonStates[mainTableRows + 1][i];
            button.display(pressCount);
        }
        submitButton.display(0);
        displayMoney();
        if (no_money) {
            fill(255);
            text("Insufficient money", 150*unit_x, 195*unit_y);
        }
    }

    private void displayMoney() {
        fill(processing.g.backgroundColor);
        strokeWeight(0);
        rect(160*unit_x, 140*unit_y, 30*unit_x, 20*unit_y);
        fill(255);
        text("Money left: " + (money + current_bet), 175*unit_x, 150*unit_y);
    }

    public void mousePressed() {
        if ((money + current_bet) - 100 > -1) {
        for (int i = 0; i < mainTableRows; i++) {
            for (int j = 0; j < mainTableCols; j++) {
                Button button = mainButtons[i][j];
                if (button.isMouseInside()) {
                    if (buttonStates[i][j] < 5) {
                        buttonStates[i][j]++;
                        current_bet -= 100;
                    }
                }
            }
        }
        for (int i = 0; i < table4Cols; i++) {
            Button button = table4Buttons[i];
            if (button.isMouseInside()) {
                if (buttonStates[mainTableRows][i] < 5) {
                    buttonStates[mainTableRows][i]++;
                    current_bet -= 100;
                }
            }
        }
        for (int i = 0; i < table5Cols; i++) {
            Button button = table5Buttons[i];
            if (button.isMouseInside()) {
                if (buttonStates[mainTableRows + 1][i] < 5) {
                    buttonStates[mainTableRows + 1][i]++;
                    current_bet -= 100;
                }
            }
        }
        } else {
            no_money = true;
            fill(255);
            text("Insufficient money", 150*unit_x, 195*unit_y);
        }
        if (submitButton.isMouseInside()) {
            String[] joinedArray = Stream.concat(
                            Stream.concat(Arrays.stream(rouletteNumbers), Arrays.stream(table4Labels)),
                            Arrays.stream(table5Labels))
                    .toArray(String[]::new);

            int count = 0;
            for (int[] buttonState : buttonStates) {
                for (int i : buttonState) {
                    if (i > 0) {
                        if (count >= (rouletteNumbers.length)) {
                            BetManager.bets.put(joinedArray[count + 1], i);
                        } else {
                            BetManager.bets.put(joinedArray[count], i);
                        }
                        BetManager.bet = current_bet;
                    }
                    count++;
                }
            }
            boolean correct_bet = false;
            for (Map.Entry<String, Integer> entry : BetManager.bets.entrySet()) {
                correct_bet = true;
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            }
            if (correct_bet) {
                BetManager.bet_ready = true;
                MainClass.showBettingMenu = false;
                Frame frame = ((PSurfaceAWT.SmoothCanvas) BettingMenu.processing.getSurface().getNative()).getFrame();
                frame.dispose();
            }
        }
        displayMoney();
    }

    public void keyPressed() {
        if (key == 'm') {
            MainClass.showBettingMenu = false;
            Frame frame = ((PSurfaceAWT.SmoothCanvas) BettingMenu.processing.getSurface().getNative()).getFrame();
            frame.dispose();
        }
        if (key == 'r') {
            for (int[] buttonState : buttonStates) {
                Arrays.fill(buttonState, 0);
            }
            for (int i = 0; i < mainTableRows; i++) {
                for (int j = 0; j < mainTableCols; j++) {
                    mainButtons[i][j].display(0);
                }
            }
            for (int i = 0; i < table4Cols; i++) {
                table4Buttons[i].display(0);
            }
            for (int i = 0; i < table5Cols; i++) {
                table5Buttons[i].display(0);
            }
            current_bet = 0;
            no_money = false;
        }
    }

    class Button {
        private final String label;
        private final int x;
        private final int y;
        private final int w;
        private final int h;
        private final int default_color;

        Button(String label, int x, int y, int w, int h, int default_color) {
            this.label = label;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.default_color = default_color;
        }

        boolean isMouseInside() {
            return mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h;
        }

        void display(int pressCount) {
            if (pressCount > 0) {
                fill(0, 0, min(255, 50 * pressCount));
            } else {
                fill(default_color);
            }
            rect(x, y, w, h);
            fill(255);
            textAlign(CENTER, CENTER);
            textSize(16);
            text(label, x + (float) w / 2, y + (float) h / 2);
        }
    }
}
