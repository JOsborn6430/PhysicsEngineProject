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
        printArrayD(b1.position);

        b1.position = vectorAdditionD(b1.position, b1.momentum);

        printArrayD(b1.position);

        double[] errortest = {7,2};
        printArrayD(vectorAdditionD(errortest, b1.momentum));
        printArrayD(errortest);
        printArrayD(scalarMultiplyD(errortest, 2.0));

    }

    public static double[] vectorAdditionD(double[] v1, double[] v2) {
        if (v1.length != v2.length) {
            System.out.println("Error: Vector dimensions do not match");
            return new double[]{0};
        } else for (int i = 0; i < v1.length; i++) {
            v1[i] += v2[i];
        }
        return v1;
    }

    public static double[] scalarMultiplyD(double[] v, double a) {
        for (int i = 0; i < v.length; i++) v[i] *= a;
        return v;
    }

    public static void printArrayD(double[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i == array.length-1) break;
            System.out.print(", ");
        }
        System.out.println("]");
    }
}