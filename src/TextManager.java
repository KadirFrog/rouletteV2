import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static processing.core.PConstants.HALF_PI;
import static processing.core.PConstants.TWO_PI;

public class TextManager {
    public static class RotationCenterPoint {
        private final float cx;
        private final float cy;
        private final float r;
        public RotationCenterPoint(CustomTypes.Position middle_point, CustomTypes.Length radius) {
            cx = middle_point.x;
            cy = middle_point.y;
            r = radius.f;
        }
        public void text(String text, float starting_point, float ending_point) {
            float startAngle = (starting_point * TWO_PI) - HALF_PI;
            float endAngle = (ending_point * TWO_PI) - HALF_PI;

            float angleIncrement = (endAngle - startAngle) / (text.length() - 1);

            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                float angle = startAngle + i * angleIncrement;
                float x = (float) (cx + r * cos(angle));
                float y = (float) (cy + r * sin(angle));
                MainClass.processing.pushMatrix();
                MainClass.processing.translate(x, y);
                MainClass.processing.rotate(angle + HALF_PI);
                MainClass.processing.fill(255);
                MainClass.processing.text(c, 0, 0);
                MainClass.processing.popMatrix();
            }
        }
    }
}