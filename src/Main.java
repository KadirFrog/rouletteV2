import processing.core.PApplet;

public class Main extends PApplet {
    public int window_width, window_height;
    public float unit_x, unit_y;
    public static PApplet processing;
    public static void main(String[] args) {
        PApplet.main("Main", args);
    }
    public void c_size(int width, int height) {
        window_width = width;
        window_height = height;
        unit_x = (float) width / 200;
        unit_y = (float) height / 200;
        size(width, height);
    }
    public void settings() {
        c_size(1000,1000);
        frameRate(60);
    }
    public void setup() {
        processing = this;
        background(255);
    }
    public void draw() {
        line(5,5, 50,50);

    }
}