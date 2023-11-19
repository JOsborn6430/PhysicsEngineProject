import java.awt.*;

public class Main {

    //Settings
    public static final int MAXSTEPS = 5000;
    public static final double DT = 0.05;
    public static final boolean GRAVITY = true;

    public static void main(String[] args) {
        //Drawing Board initialization
        DrawingPanel panel = Render.initPanel();
        Graphics g = Render.initGraphics(panel);

        //Create and initialize Objects
        Box b1 = new Box();
        b1.name = "Box 1";
        b1.height = 30;
        b1.width = 30;
        b1.position = new double[]{300,100};
        b1.momentum = new double[]{10,0};

        Box b2 = new Box();
        b2.name = "Box 2";
        b2.height = 30;
        b2.width = 30;
        b2.position = new double[]{150,300};

        Box b3 = new Box();
        b3.name = "Box 3";
        b3.height = 30;
        b3.width = 30;
        b3.position = new double[]{150,200};

        Box b4 = new Box();
        b4.name = "Box 4";
        b4.height = 20;
        b4.width = 40;
        b4.position = new double[]{300,300};

        Circle c1 = new Circle();
        c1.name = "c1";
        c1.mass = 3;
        c1.radius = 30;
        c1.position = new double[]{300,300};

        Circle c2 = new Circle();
        c2.name = "c2";
        c2.mass = 3;
        c2.radius = 30;
        c2.position = new double[]{300,300};

        Box floor = new Box();
        floor.name = "floor";
        floor.isStatic = true;
        floor.height = 20;
        floor.width = 500;
        floor.position = new double[]{350,10};

        Shapes testShape = new Shapes();

        //Master Array
        Shapes[] objects = {floor,b1,b2,b3,b4};

        //create sub arrays
        // This stupid code sorts the master array into sub arrays of each object type. I will make working with large numbers of objects easier.
        // It also removes the need to check for specific object type when resolving collisions.
        int boxArrayLength = 0;
        for (int i = 0; i < objects.length; i++) if (objects[i] instanceof Box) boxArrayLength++;
        Box[] boxes = new Box[boxArrayLength];
        int boxesIndex = 0;
        for (int i = 0; boxesIndex < boxArrayLength; i++) {
            if (objects[i] instanceof Box) {
                boxes[boxesIndex] = (Box)objects[i];
                boxesIndex++;
            }
        }
        int circleArrayLength = 0;
        for (int i = 0; i < objects.length; i++) if (objects[i] instanceof Circle) circleArrayLength++;
        Circle[] circles = new Circle[circleArrayLength];
        int circlesIndex = 0;
        for (int i = 0; circlesIndex < circleArrayLength; i++) {
            if (objects[i] instanceof Circle) {
                circles[circlesIndex] = (Circle) objects[i];
                circlesIndex++;
            }
        }
        //test to make sure sorting worked
//        System.out.println("Circles:");
//        for (int i = 0; i < circles.length; i++) {
//            System.out.println(circles[i].name);
//        }
//        System.out.println("Boxes:");
//        for (int i = 0; i < boxes.length; i++) {
//            System.out.println(boxes[i].name);
//        }

        //Main Loop
        for (int steps = 0; steps < MAXSTEPS; steps++) {

            //Apply force of gravity
            if (GRAVITY) {
                for (int i = 0; i < objects.length; i++) {
                    if (!objects[i].isOnFloor && !objects[i].isStatic) {
                        objects[i].forceActing[1] = -objects[i].mass * 9.8;
                    }
                }
            }

            for (int k = 0; k < objects.length; k++) boxes[k].isOnFloor = false;

            // Box collision detection
            for (int i = 0; i < boxes.length - 1; i++) {
                for (int j = i + 1; j < boxes.length; j++) {
                    if (Math.abs(boxes[i].position[1] - boxes[j].position[1]) <= (boxes[i].height/2 + boxes[j].height/2) &&
                            Math.abs(boxes[i].position[0] - boxes[j].position[0]) < (boxes[i].width/2 + boxes[j].width/2)) {
                        boxes[i].isOnFloor = true;
                        boxes[j].isOnFloor = true;

                        if (!boxes[i].isStatic) {
                            boxes[i].momentum[1] *= -1 * boxes[i].bounce;
                            boxes[i].position = Op.vectorAdditionD(boxes[i].position, Op.scalarMultiplyD(boxes[i].momentum, DT));

                        }
                        if (!boxes[j].isStatic) {
                            boxes[j].momentum[1] *= -1 * boxes[j].bounce;
                            boxes[j].position = Op.vectorAdditionD(boxes[j].position, Op.scalarMultiplyD(boxes[j].momentum, DT));
                        }

                    }
                    }
                }



            //Move Objects
            for (int i = 0; i < objects.length; i++) {

                // I converted everything into acceleration and velocity before making movement calculation
                // I'm sure there is a more elegant way to do this.
                // I was originally going to generalize everything to n-dimensions but i am too lazy so everything for here on is hard coded for 2d

                if (objects[i].momentum[1] < 1 && objects[i].isOnFloor) {
                    objects[i].momentum[1] = 0;
                    objects[i].forceActing[1] = 0;
                }

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

                objects[i].forceActing = new double[]{0,0};

            }


            // Render Objects
            panel.clear();
            for (int i = 0; i < objects.length; i++) Render.drawObject(g, objects[i]);
            panel.sleep(10);

        } //end of main loop
    }
}