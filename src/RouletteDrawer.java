import java.util.Vector;

public class RouletteDrawer {
    public static int radiusInner, radiusOuter;

    public static void setup(int radiusInner, int radiusOuter) {
        RouletteDrawer.radiusInner = radiusInner;
        RouletteDrawer.radiusOuter = radiusOuter;
    }

    private static Vector<CustomTypes.Position> getRouletteCoordsBack(int radius) {
        Vector<CustomTypes.Position> coords = new Vector<>();
        int segments = 36;
        float angleIncrement = (float) (2 * Math.PI / segments);

        for (int i = 0; i < segments; i += 2) {
            float angleStart = i * angleIncrement;
            float xStart = (float) (radius * Math.cos(angleStart));
            float yStart = (float) (radius * Math.sin(angleStart));
            coords.add(new CustomTypes.Position(500 + xStart, 500 + yStart, true));
            float angleEnd = (i + 1) * angleIncrement;
            float xEnd = (float) (radius * Math.cos(angleEnd));
            float yEnd = (float) (radius * Math.sin(angleEnd));
            coords.add(new CustomTypes.Position(500 + xEnd, 500 + yEnd, true));
        }

        return coords;
    }



    public static Vector<CustomTypes.Position> getRouletteCoordsInner() {
        return getRouletteCoordsBack(radiusInner);
    }

    public static Vector<CustomTypes.Position> getRouletteCoordsOuter() {
        return getRouletteCoordsBack(radiusOuter);
    }
}
