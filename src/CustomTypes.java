public class CustomTypes {
    public static class Position {
        public float x, y;
        public Position(float x, float y){
            this.x = x * MainClass.unit_x;
            this.y = y * MainClass.unit_y;
        }
    }
    public static class Length {
        public float f;
        public Length(float length){
            this.f = length * MainClass.unit;
        }
    }
}
