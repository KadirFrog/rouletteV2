import processing.core.PApplet;

public class BettingMenu extends PApplet {
    public static PApplet processing;
    public static int window_width, window_height;
    public static float unit_x, unit_y, unit;

    private final int numRows = 3;
    private final int numCols = 13;
    private Button[][] buttons;
    private boolean[][] buttonStates;

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
        final String[] rouletteNumbers = {
                "0", "3", "6", "9", "12", "15", "18", "21", "24", "27", "30", "33", "36", "2 to 1", // 1st column
                "2", "5", "8", "11", "14", "17", "20", "23", "26", "29", "32", "35", "2 to 1", // 2nd column
                "1", "4", "7", "10", "13", "16", "19", "22", "25", "28", "31", "34", "2 to 1" // 3rd column
        };

        final int green = color(0, 255, 0);
        final int red = color(255, 0, 0);
        final int black = color(0, 0, 0);

        final int[] rouletteColors = {
                green, red, black, red, red, black, red, red, black, red, red, black, red, green, // 1st column
                black, red, black, black, red, black, black, red, black, black, red, black, green, // 2nd column
                red, black, red, black, black, red, red, black, red, black, black, red, green // 3rd column
        };


        processing = this;
        windowTitle("Betting Menu | Roulette V2");
        background(0);

        buttons = new Button[numRows][numCols];
        buttonStates = new boolean[numRows][numCols];

        int buttonWidth = 50;
        int buttonHeight = 50;
        int current = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                current++;
                int x = (int) ((j + 1) + buttonWidth * j + 100*unit_x - buttonWidth*numCols*.5);
                int y = (int) ((i + 1) + buttonHeight * i + 100*unit_y - buttonHeight*numRows*.5);
                buttons[i][j] = new Button(rouletteNumbers[current], x, y, buttonWidth, buttonHeight, rouletteColors[current]);
            }
        }
    }

    public void draw() {
        background(50);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                Button button = buttons[i][j];
                boolean buttonState = buttonStates[i][j];
                button.display(buttonState);
            }
        }
    }

    public void mousePressed() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                Button button = buttons[i][j];
                if (button.isMouseInside()) {
                    buttonStates[i][j] = !buttonStates[i][j];
                }
            }
        }
    }

    public void keyPressed() {
        if (key == 'm') {
            exit();
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

        void display(boolean pressed) {
            if (pressed) {
                fill(0, 0, 255);
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
