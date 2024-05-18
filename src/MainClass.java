import processing.core.PApplet;

public class MainClass extends PApplet {
    public static int window_width, window_height;
    public static float unit_x, unit_y, unit;
    public static PApplet processing;
    public static void main(String[] args) {
        PApplet.main("MainClass", args);
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
        CustomTypes.Position m = new CustomTypes.Position(100, 100); CustomTypes.Length m2 = new CustomTypes.Length(20);
        TextManager.RotationCenterPoint main_center_point = new TextManager.RotationCenterPoint(m, m2);
        main_center_point.text("Testing really good", 0, 0.25F);
    }
}
