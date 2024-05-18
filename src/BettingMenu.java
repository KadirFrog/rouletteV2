import processing.core.PApplet;

public class BettingMenu extends PApplet {
    public static PApplet processing;
    public static int window_width, window_height;
    public static float unit_x, unit_y, unit;

    private int numRows = 3;
    private int numCols = 3;
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
        processing = this;
        windowTitle("Betting Menu | Roulette V2");
        background(0);

        buttons = new Button[numRows][numCols];
        buttonStates = new boolean[numRows][numCols];

        int buttonWidth = 100;
        int buttonHeight = 50;
        int spacingX = (width - buttonWidth * numCols) / (numCols + 1);
        int spacingY = (height - buttonHeight * numRows) / (numRows + 1);

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int x = spacingX * (j + 1) + buttonWidth * j;
                int y = spacingY * (i + 1) + buttonHeight * i;
                buttons[i][j] = new Button("Button " + (i * numCols + j + 1), x, y, buttonWidth, buttonHeight);
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
        private String label;
        private int x, y, w, h;

        Button(String label, int x, int y, int w, int h) {
            this.label = label;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

        boolean isMouseInside() {
            return mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h;
        }

        void display(boolean pressed) {
            if (pressed) {
                fill(255, 0, 0);
            } else {
                fill(150);
            }
            rect(x, y, w, h);
            fill(255);
            textAlign(CENTER, CENTER);
            textSize(16);
            text(label, x + w / 2, y + h / 2);
        }
    }
}
