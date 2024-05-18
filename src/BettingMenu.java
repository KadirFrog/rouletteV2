import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

import java.awt.*;
import java.util.Arrays;

public class BettingMenu extends PApplet {
    public static PApplet processing;
    public static int window_width, window_height;
    public static float unit_x, unit_y, unit;

    private final int mainTableRows = 3;
    private final int mainTableCols = 13;
    private final int table4Cols = 3;
    private final int table5Cols = 6;

    private Button[][] mainButtons;
    private Button[] table4Buttons;
    private Button[] table5Buttons;
    private int[][] buttonStates;

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

        int buttonWidth = 50;
        int buttonHeight = 50;

        mainButtons = new Button[mainTableRows][mainTableCols];
        table4Buttons = new Button[table4Cols];
        table5Buttons = new Button[table5Cols];

        buttonStates = new int[mainTableRows + 2][mainTableCols];

        final String[] rouletteNumbers = {
                "0", "3", "6", "9", "12", "15", "18", "21", "24", "27", "30", "33", "36", "2 to 1",
                "2", "5", "8", "11", "14", "17", "20", "23", "26", "29", "32", "35", "2 to 1",
                "1", "4", "7", "10", "13", "16", "19", "22", "25", "28", "31", "34", "2 to 1"
        };

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
        final String[] table4Labels = {"1st 12", "2nd 12", "3rd 12"};
        buttonWidth *= 4;
        for (int i = 0; i < table4Cols; i++) {
            int x = i * buttonWidth + oldButtonWidth;
            int y = mainTableRows * buttonHeight;
            table4Buttons[i] = new Button(table4Labels[i], x, y, buttonWidth, buttonHeight, green);
        }

        final String[] table5Labels = {"1 to 18", "EVEN", "RED", "BLACK", "ODD", "19 to 36"};
        buttonWidth /= 2;
        final int[] color2 = {green, green, black, red, green, green};
        for (int i = 0; i < table5Cols; i++) {
            int x = i * buttonWidth + oldButtonWidth;
            int y = (mainTableRows + 1) * buttonHeight;
            table5Buttons[i] = new Button(table5Labels[i], x, y, buttonWidth, buttonHeight, color2[i]);
        }
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
    }

    public void mousePressed() {
        for (int i = 0; i < mainTableRows; i++) {
            for (int j = 0; j < mainTableCols; j++) {
                Button button = mainButtons[i][j];
                if (button.isMouseInside()) {
                    if (buttonStates[i][j] < 10) {
                        buttonStates[i][j]++;
                    }
                }
            }
        }
        for (int i = 0; i < table4Cols; i++) {
            Button button = table4Buttons[i];
            if (button.isMouseInside()) {
                if (buttonStates[mainTableRows][i] < 10) {
                    buttonStates[mainTableRows][i]++;
                }
            }
        }
        for (int i = 0; i < table5Cols; i++) {
            Button button = table5Buttons[i];
            if (button.isMouseInside()) {
                if (buttonStates[mainTableRows + 1][i] < 10) {
                    buttonStates[mainTableRows + 1][i]++;
                }
            }
        }
    }

    public void keyPressed() {
        if (key == 'm') {
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
                fill(0, 0, min(255, 25 * pressCount));
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
