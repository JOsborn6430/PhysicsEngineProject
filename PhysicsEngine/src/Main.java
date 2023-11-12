import java.awt.*;

public class Main {

    //Settings
    public static final int MAXSTEPS = 50;
    public static final double DT = 0.1;
    public static final boolean GRAVITY = true;

    public static void main(String[] args) {
        //Drawing Board initialization
        DrawingPanel panel = Render.initPanel();
        Graphics g = Render.initGraphics(panel);

        //Create and initialize Objects
        Box b1 = new Box();
        b1.height = 30;
        b1.width = 30;
        b1.position = new double[]{100,400};
        Op.printArrayD(b1.position);

        Circle c1 = new Circle();
        c1.mass = 3;
        c1.radius = 30;
        c1.position = new double[]{50,300};

        //Master Array
        Shapes[] objects = {b1,c1};

        //Main Loop
        b1.momentum[0] = 30;
        Op.printArrayD(b1.position);
        for (int steps = 0; steps < MAXSTEPS; steps++) {

            //Apply force of gravity
            if (GRAVITY) {
                for (int i = 0; i < objects.length; i++) {
                    if (!objects[i].isOnFloor && !objects[i].isStatic) {
                        objects[i].forceActing[1] = -objects[i].mass * 9.8;
                    }
                }
            }

            //Move Objects
            for (int i = 0; i < objects.length; i++) {

                // I converted everything into acceleration and velocity before making movement calculation
                // I'm sure there is a more elegant way to do this.
                // I was originally going to generalize everything to n-dimensions but i am too lazy so everything for here on is hard coded for 2d

                double[] a = {0,0};
                a[0] = objects[i].forceActing[0]/objects[i].mass;
                a[1] = objects[i].forceActing[1]/objects[i].mass;

                double[] v = {0,0};
                v[0] = objects[i].momentum[0]/objects[i].mass;
                v[1] = objects[i].momentum[1]/objects[i].mass;

                objects[i].position[0] += v[0]*DT + 0.5*Math.pow(DT,2)*a[0];
                objects[i].momentum[0] += objects[i].forceActing[0]*DT;

                objects[i].position[1] += v[1]*DT + 0.5*Math.pow(DT,2)*a[1];
                objects[i].momentum[1] += objects[i].forceActing[1]*DT;

            }


            // Render Objects
            panel.clear();
            for (int i = 0; i < objects.length; i++) Render.drawObject(g, objects[i]);
            panel.sleep(10);

        }
        Op.printArrayD(b1.position);
        // Testing Marker
        g.setColor(Color.black);
        g.fillRect(100,Render.HEIGHT - 100,2,2);
    }
}