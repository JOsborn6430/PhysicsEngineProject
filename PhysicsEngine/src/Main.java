import java.awt.*;

public class Main {
    public static void main(String[] args) {
        //Drawing Board
//        DrawingPanel panel = new DrawingPanel(200, 200);
//        Graphics g = panel.getGraphics();
//        g.setColor(Color.RED);
//        g.fillRect(1,1,1,1);
        Box b1 = new Box();
        b1.position = new double[]{100, 200};
        b1.momentum[0] = 1;
        b1.momentum[1] = 2;
        Op.printArrayD(b1.position);
        Op.printArrayD(Op.scalarMultiplyD(b1.position,3.0));
        Op.printArrayD(b1.position);
    }
}