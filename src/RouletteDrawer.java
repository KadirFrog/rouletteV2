import java.util.Vector;

public class RouletteDrawer {
    private static int radiusInner, radiusOuter;
    public static void setup(int radiusInner, int radiusOuter) {
        RouletteDrawer.radiusInner = radiusInner;
        RouletteDrawer.radiusOuter = radiusOuter;
    }
    private static Vector<CustomTypes.Position> getRouletteCoordsInnerBack(int d_offset) {
        Vector<CustomTypes.Position> coords1 = new Vector<>();
        int r_len = 37;
        float angleIncrement = (float) (2 * Math.PI / r_len);
        for (int i = 0; i < r_len; i++) {
            float angle = i * angleIncrement + d_offset;
            float x = (float) (radiusInner * Math.cos(angle));
            float y = (float) (radiusInner * Math.sin(angle));
            coords1.add(new CustomTypes.Position((500 + x), (500 + y), true));
        }
        return coords1;
    }
    private static Vector<CustomTypes.Position> getRouletteCoordsOuterBack(int d_offset) {
        Vector<CustomTypes.Position> coords2 = new Vector<>();
        int r_len = 37;
        float angleIncrement = (float) (2 * Math.PI / r_len);
        for (int i = 0; i < r_len; i++) {
            float angle = i * angleIncrement + d_offset;
            float x = (float) (radiusOuter * Math.cos(angle));
            float y = (float) (radiusOuter * Math.sin(angle));
            coords2.add(new CustomTypes.Position((500 + x), (500 + y), true));
        }
        return coords2;
    }
    public static Vector<CustomTypes.Position> getRouletteCoordsInner() {
        Vector<CustomTypes.Position> coords1 = getRouletteCoordsInnerBack(0);
        //Vector<CustomTypes.Position> coords2 = getRouletteCoordsInnerBack(1);
        Vector<CustomTypes.Position> coords = new Vector<>();
        for (int i = 0; i < coords1.size(); i++) {
            coords.add(coords1.get(i));
            //coords.add(coords2.get(i));
        }
        return coords;
    }
    public static Vector<CustomTypes.Position> getRouletteCoordsOuter() {
        Vector<CustomTypes.Position> coords1 = getRouletteCoordsOuterBack(0);
        Vector<CustomTypes.Position> coords2 = getRouletteCoordsOuterBack(1);
        Vector<CustomTypes.Position> coords = new Vector<>();
        for (int i = 0; i < coords1.size(); i++) {
            coords.add(coords1.get(i));
            coords.add(coords2.get(i));
        }
        return coords;
    }
}
