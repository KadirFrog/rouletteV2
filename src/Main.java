import processing.core.PApplet;

public class Main extends PApplet {
    public int window_width, window_height;
    public float unit_x, unit_y, unit;
    public static PApplet processing;
    public static void main(String[] args) {
        PApplet.main("Main", args);
    }
    public void c_size(int width, int height) {
        window_width = width;
        window_height = height;
        unit_x = (float) width / 200;
        unit_y = (float) height / 200;
        unit = (unit_x * unit_y) / 2;
        size(width, height);
    }
    public void settings() {
        c_size(1000, 1000);
        //frameRate(60);
    }
    public void setup() {
        processing = this;
        background(0);
    }
    public void draw() {
        background(0);
        String text = "Processing";
        float cx = 100 * unit_x;
        float cy = 100 * unit_y;
        float r = 30 * unit;
        float circle_percentage = 0.5F;
        float starting_point = 0.5F;
        float startAngle = (starting_point*4*HALF_PI) -HALF_PI;

        strokeWeight(3);
        noFill();
        ellipse(cx, cy, r * 2, r * 2);

        float angleIncrement = (TWO_PI * circle_percentage) / (text.length() - 1);

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            float angle = startAngle + i * angleIncrement;
            float x = cx + r * cos(angle);
            float y = cy + r * sin(angle);
            pushMatrix();
            translate(x, y);
            rotate(angle + HALF_PI);
            fill(255);
            text(c, 0, 0);
            popMatrix();
        }
    }
}
