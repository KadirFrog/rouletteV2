import processing.core.PApplet;

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
        //MainClass.processing.stroke(255);
        MainClass.processing.strokeWeight(5);
        MainClass.processing.line(a.x, a.y, b.x, b.y);
        //System.out.println("Line " + a.x + " " + a.y + " " + b.x + " " + b.y);
    }
    public static String[] rouletteNumbers = {
            "0", "32", "15", "19", "4", "21", "2", "25", "17", "34",
            "6", "27", "13", "36", "11", "30", "8", "23", "10", "5",
            "24", "16", "33", "1", "20", "14", "31", "9", "22", "18",
            "29", "7", "28", "12", "35", "3", "26"
    };
    public static String[] rouletteNumbersDouble = {
            " 0 ", "32", "15", "19", " 4 ", "21", " 2 ", "25", "17", "34",
            " 6 ", "27", "13", "36", "11", "30", " 8 ", "23", "10", " 5 ",
            "24", "16", "33", " 1 ", "20", "14", "31", " 9 ", "22", "18",
            "29", " 7 ", "28", "12", "35", " 3 ", "26", " 0 ", "32", "15",
            "19", " 4 ", "21", " 2 ", "25", "17", "34",
            " 6 ", "27", "13", "36", "11", "30", " 8 ", "23", "10", " 5 ",
            "24", "16", "33", " 1 ", "20", "14", "31", " 9 ", "22", "18",
            "29", " 7 ", "28", "12", "35", " 3 ", "26",
    };
    public static class Button{
        public float x, y, width, height;
        public String text;
        public Button(float x, float y, float width, float height, String text){
            this.x = x - width / 2;
            this.y = y;
            this.width = width;
            this.height = height;
            this.text = text;
        }
        public void draw(){
            MainClass.processing.stroke(0);
            MainClass.processing.strokeWeight(5);
            MainClass.processing.fill(255);
            MainClass.processing.rect(x, y, width, height);
            MainClass.processing.fill(0);
            MainClass.processing.textAlign(MainClass.CENTER, MainClass.CENTER);
            MainClass.processing.textSize(16);
            MainClass.processing.text(text, x + width / 2, y + height / 2);
        }
        boolean isMouseInside(float mouseX, float mouseY){
            return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
        }
    }
    public static Button[] mainButtons;
}
