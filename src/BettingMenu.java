import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

import java.awt.*;

public class BettingMenu extends PApplet {
    public static PApplet processing;
    public static int window_width, window_height;
    public static float unit_x, unit_y, unit;

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
    }

    public void draw() {
        background(50);
        fill(255);
        textAlign(CENTER, CENTER);
        textSize(32);
        text("This is an additional window", (float) width / 2, (float) height / 2);
    }

    public void keyPressed() {
        if (key == 'm') {
                Frame frame = ((PSurfaceAWT.SmoothCanvas) BettingMenu.processing.getSurface().getNative()).getFrame();
                frame.dispose();
        }
    }

}
