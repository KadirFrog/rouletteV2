import java.util.Vector;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class RouletteDrawer {
    public static int radiusInner, radiusOuter;
    public static float last_turn;

    public static void setup(int radiusInner, int radiusOuter) {
        RouletteDrawer.radiusInner = radiusInner;
        RouletteDrawer.radiusOuter = radiusOuter;
    }

    private static Vector<CustomTypes.Position> getRouletteCoordsBack(int radius, float turned_segment) {
        Vector<CustomTypes.Position> coords = new Vector<>();
        int segments = 37;
        float angleIncrement = (float) (2 * Math.PI / segments);

        for (int i = 0; i < segments; i++) {
            float angle = (i + turned_segment) * angleIncrement;
            float x = (float) (radius * cos(angle));
            float y = (float) (radius * sin(angle));
            coords.add(new CustomTypes.Position(500 + x, 500 + y, true));
        }

        return coords;
    }

    public static Vector<CustomTypes.Position> getRouletteCoordsInner(float turn) {
        //System.out.println("Turn = " + turn + "°");
        last_turn = turn;
        return getRouletteCoordsBack(radiusInner, turn);
    }

    public static Vector<CustomTypes.Position> getRouletteCoordsOuter(float turn) {
        return getRouletteCoordsBack(radiusOuter, turn);
    }
}
