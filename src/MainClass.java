import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import java.awt.*;
import java.util.Vector;

public class MainClass extends PApplet {
    public static int window_width, window_height;
    public static float unit_x, unit_y, unit;
    public static PApplet processing;
    public static boolean showBettingMenu = false;
    private boolean rolling = false;
    private int roll_frame, stop_frame;
    private float last_rotation, starting_rotation = 0;
    public static void main(String[] args) {
        PApplet.main("MainClass", args);
    }
    public static int current_frame = 0;
    CustomTypes.Button roll_button = new CustomTypes.Button(500, 900, 100, 50, "Roll");
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
    }

    public void setup() {
        processing = this;
        background(color(0, 150, 0));
        windowTitle("Roulette V2 - von Kadir 9c");
        RouletteDrawer.setup(200, 300);
        frameRate(30);
    }

    public void draw() {
        background(color(0, 150, 0));
        TextManager.RotationCenterPoint main_center_point = new TextManager.RotationCenterPoint(new CustomTypes.Position(100, 100), new CustomTypes.Length(22));
        //main_center_point.text("Testing really good", 0, 0.25F);
        Vector<CustomTypes.Position> inner1 = null;
        Vector<CustomTypes.Position> outer1 = null;
        stroke(0);
        fill(color(140, 42, 42));
        circle(500F, 500F, (float) (RouletteDrawer.radiusOuter*2));
        if (rolling) {
            last_rotation = (float) (current_frame - roll_frame) / 30;
            float rotation = last_rotation + starting_rotation;
            inner1 = RouletteDrawer.getRouletteCoordsInner(rotation);
            outer1 = RouletteDrawer.getRouletteCoordsOuter(rotation);
            main_center_point.text_based_on_field(0, rotation);
            if (current_frame == stop_frame) {
                rolling = false;
                BetManager.bet_ready = false;
                starting_rotation += last_rotation;
            }
        } else {
            inner1 = RouletteDrawer.getRouletteCoordsInner(starting_rotation);
            outer1 = RouletteDrawer.getRouletteCoordsOuter(starting_rotation);
            main_center_point.text_based_on_field(0, starting_rotation);
            roll_button.draw();
        }

        int loc0 = 28;
        int col0 = color(255, 255, 0);
        for (int i = 0; i < inner1.size(); i++) {
            if (i == loc0 || i == loc0 + 1) {
                stroke(col0);
            } else {
                stroke(color(130, 40, 40));
            }
            CustomTypes.cline(inner1.get(i), outer1.get(i));
        }
        boolean locf = false;
        for (int i = 0; i < inner1.size(); i++) {
            if (i == loc0) {
                locf = true;
                continue;
            } else if (i % 2 == 0) {
                stroke(locf ? 0 : -65536);
            } else {
                stroke(locf ? -65536 : 0);
            }
            try {
                CustomTypes.cline(inner1.get(i), inner1.get(i + 1));
                CustomTypes.cline(outer1.get(i), outer1.get(i + 1));
            } catch (ArrayIndexOutOfBoundsException e) {
                CustomTypes.cline(inner1.get(0), inner1.get(i));
                CustomTypes.cline(outer1.get(0), outer1.get(i));
            }
        }
        stroke(col0);
        CustomTypes.cline(inner1.get(loc0), inner1.get(loc0 + 1));
        CustomTypes.cline(outer1.get(loc0), outer1.get(loc0 + 1));
        current_frame++;
        //System.out.println(current_frame);
    }

    public void keyPressed() {
        if (!rolling) {
            if (key == 'm') {
                showBettingMenu = !showBettingMenu;
                if (showBettingMenu) {
                    String[] additionalWindowArgs = {"BettingMenu"};
                    PApplet.runSketch(additionalWindowArgs, new BettingMenu());
                } else {
                    Frame frame = ((PSurfaceAWT.SmoothCanvas) BettingMenu.processing.getSurface().getNative()).getFrame();
                    frame.dispose();
                }
            }
        }
    }

    public void mousePressed() {
        if (roll_button.isMouseInside(mouseX, mouseY) && !rolling) {
            System.out.println("Rolling");
            if (BetManager.bet_ready) {
                BetManager.bet_ready = false;
                roll_frame = current_frame;
                stop_frame = roll_frame + 30 * 3;
                rolling = true;
            } else {
                System.out.println("Bet not ready");
            }
        }
    }

}