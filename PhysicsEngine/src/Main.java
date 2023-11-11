import java.awt.*;

public class Main {

    //Settings
    public static final int MAXSTEPS = 10;
    public static final int DT = 1;
    public static final boolean GRAVITY = true;

    public static void main(String[] args) {
        //Drawing Board initialization
        Graphics g = Render.initGraphics();

        //Create and initialize Objects
        Box b1 = new Box();
        b1.height = 30;
        b1.width = 30;
        b1.position = new double[]{100,100};
        Op.printArrayD(b1.position);

        Circle c1 = new Circle();
        c1.mass = 3;
        c1.radius = 30;
        c1.position = new double[]{0,0};

        //Master Array
        Shapes[] objects = {b1,c1};

        //Main Loop
        int steps = 0;


        while (steps < MAXSTEPS) {

            for (int i = 0; i < objects.length; i++) {

                Render.drawObject(g, objects[i]);

            }



            steps++;
        }
        g.setColor(Color.black);
        g.fillRect(100,Render.HEIGHT - 100,2,2);
    }
}