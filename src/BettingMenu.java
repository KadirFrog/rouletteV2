import processing.core.PApplet;

public class BettingMenu extends PApplet {
    public static PApplet processing;

    public void settings() {
        size(800, 800);
    }

    public void setup() {
        processing = this;
        background(0);
    }

    public void draw() {
        background(50);
        fill(255);
        textAlign(CENTER, CENTER);
        textSize(32);
        text("This is an additional window", width / 2, height / 2);
    }
}
