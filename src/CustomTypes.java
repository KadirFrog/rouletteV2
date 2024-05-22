public class CustomTypes {
    public static class Position {
        public float x, y;
        public Position(float x, float y){
            this.x = x * MainClass.unit_x;
            this.y = y * MainClass.unit_y;
        }
        public Position(float x, float y, boolean abs){
            this.x = x;
            this.y = y;
        }
    }
    public static class Length {
        public float f;
        public Length(float length){
            this.f = length * MainClass.unit;
        }
    }
    public static void cline(Position a, Position b){
        MainClass.processing.stroke(255);
        MainClass.processing.strokeWeight(5);
        MainClass.processing.line(a.x, a.y, b.x, b.y);
        System.out.println("Line " + a.x + " " + a.y + " " + b.x + " " + b.y);
    }
}
