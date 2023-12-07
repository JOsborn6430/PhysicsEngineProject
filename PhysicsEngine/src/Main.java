import java.awt.*;
import java.awt.image.CropImageFilter;
import java.util.Scanner;

public class Main {

    //Settings
    public static final int MAXSTEPS = 5000;
    public static final double DT = 0.05;
    public static final boolean GRAVITY = true;

    public static void main(String[] args) {


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
        b2.position = new double[]{150,100};

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
        c1.position = new double[]{300,100};

        Circle c2 = new Circle();
        c2.name = "c2";
        c2.mass = 3;
        c2.radius = 5;
        c2.position = new double[]{400,100};

        Box floor = new Box();
        floor.name = "floor";
        floor.isStatic = true;
        floor.height = 20;
        floor.width = 500;
        floor.position = new double[]{350,10};

        Scanner scanner = new Scanner(System.in);

        System.out.print("How many boxes do you want? ");
        Box[] boxes = new Box[scanner.nextInt()];
        for (int i = 0; i < boxes.length; i++) {
            boxes[i] = new Box();
        }

        for (int i = 0; i < boxes.length; i++) {
            System.out.print("Is box " + (i+1) + " static? (true or false): ");
            boxes[i].isStatic = scanner.nextBoolean();
            System.out.print("Height and width of box " + (i+1) + ": ");
            boxes[i].height = scanner.nextInt();
            boxes[i].width = scanner.nextInt();
            System.out.print("Mass of Box" + (i+1) + ": ");
            boxes[i].mass = scanner.nextInt();
            System.out.print("Position of Box" + (i+1) + ": ");
            boxes[i].position = new double[]{scanner.nextInt(), scanner.nextInt()};


        }


        System.out.println("How many circles do you want? ");
        Circle[] circles = new Circle[scanner.nextInt()];
        for (int i = 0; i < circles.length; i++) {
            circles[i] = new Circle();
        }
        for (int i = 0; i < circles.length; i++) {
            System.out.print("Radius of circle " + (i+1) + ": ");
            circles[i].radius = scanner.nextInt();
            System.out.println(circles[i].radius);

            System.out.print("Mass of Circle" + (i+1) + ": ");
            circles[i].mass = scanner.nextInt();
        }

        // Create new init loop:
        // How many boxes do you want?
        // take input and create box array of that length.
        // loop through new array, and use user input to init each field.

        //Example
        // C: How many boxes would you like?
        // U: 3
        // *Creates new array length 3
        // C: Mass of box 1?
        // U: 2
        // C: x and y position of box 1?
        // u: 100 300
        // C: velocity of box 1?
        // U: 20 0
        // C: Mass of Box 2?
        //.... repeat for all boxes


        //Master Array
        //This code combines the two separate circle and box arrays into one master array
        Shapes[] objects = new Shapes[boxes.length + circles.length];
        for (int i = 0; i < boxes.length; i++) {
            objects[i] = boxes[i];
        }
        for (int i = boxes.length; i < objects.length; i++) {
            objects[i] = circles[i-boxes.length];
        }

        for (int i = 0; i < objects.length; i++) {
            System.out.println(objects[i].mass);
        }

        //Drawing Board initialization
        DrawingPanel panel = Render.initPanel();
        Graphics g = Render.initGraphics(panel);



        //create sub arrays
        // This stupid code sorts the master array into sub arrays of each object type. I will make working with large numbers of objects easier.
        // It also removes the need to check for specific object type when resolving collisions.
//        int boxArrayLength = 0;
//        for (int i = 0; i < objects.length; i++) if (objects[i] instanceof Box) boxArrayLength++;
//        Box[] boxes = new Box[boxArrayLength];
//        int boxesIndex = 0;
//        for (int i = 0; boxesIndex < boxArrayLength; i++) {
//            if (objects[i] instanceof Box) {
//                boxes[boxesIndex] = (Box)objects[i];
//                boxesIndex++;
//            }
//        }
//        int circleArrayLength = 0;
//        for (int i = 0; i < objects.length; i++) if (objects[i] instanceof Circle) circleArrayLength++;
//        Circle[] circles = new Circle[circleArrayLength];
//        int circlesIndex = 0;
//        for (int i = 0; circlesIndex < circleArrayLength; i++) {
//            if (objects[i] instanceof Circle) {
//                circles[circlesIndex] = (Circle) objects[i];
//                circlesIndex++;
//            }
//        }
//        //test to make sure sorting worked
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

            for (int k = 0; k < objects.length; k++) objects[k].isOnFloor = false;

            // Box collision detection
            for (int i = 0; i < boxes.length - 1; i++) {
                for (int j = i + 1; j < boxes.length; j++) {
                    if (Math.abs(boxes[i].position[1] - boxes[j].position[1]) <= (boxes[i].height/2.0 + boxes[j].height/2.0) &&
                            Math.abs(boxes[i].position[0] - boxes[j].position[0]) < (boxes[i].width/2.0 + boxes[j].width/2.0)) {
                        boxes[i].isOnFloor = true;
                        boxes[j].isOnFloor = true;

                        if (!boxes[i].isStatic) {
                            boxes[i].momentum[1] *= -1 * boxes[i].bounce;
                            boxes[i].position = Op.vectorAdditionD(boxes[i].position, Op.scalarMultiplyD(Op.scalarMultiplyD(boxes[i].momentum, DT), boxes[i].mass));

                        }
                        if (!boxes[j].isStatic) {
                            boxes[j].momentum[1] *= -1 * boxes[j].bounce;
                            boxes[j].position = Op.vectorAdditionD(boxes[j].position, Op.scalarMultiplyD(Op.scalarMultiplyD(boxes[j].momentum, DT), boxes[j].mass));
                        }

                    }
                    }
                }
            //circle on box vertical collision
            for (int i = 0; i < circles.length; i++) {
                for (int j = 0; j < boxes.length; j++) {
                    if (Math.abs(circles[i].position[1] - boxes[j].position[1]) <= (circles[i].radius + boxes[j].height/2.0) &&
                            Math.abs(circles[i].position[0] - boxes[j].position[0]) < (circles[i].radius + boxes[j].width/2.0)) {
                        circles[i].isOnFloor = true;
                        boxes[j].isOnFloor = true;

                        if (!circles[i].isStatic) {
                            circles[i].momentum[1] *= -1 * circles[i].bounce;
                            circles[i].position = Op.vectorAdditionD(circles[i].position, Op.scalarMultiplyD(Op.scalarMultiplyD(circles[i].momentum, DT), circles[i].mass));

                        }
                        if (!boxes[j].isStatic) {
                            boxes[j].momentum[1] *= -1 * boxes[j].bounce;
                            boxes[j].position = Op.vectorAdditionD(boxes[j].position, Op.scalarMultiplyD(Op.scalarMultiplyD(boxes[j].momentum, DT), boxes[j].mass));
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

//            System.out.println("c1: " + c1.isOnFloor);
//            System.out.println("c2: " + c2.isOnFloor);
//            System.out.println("b1: " + b1.isOnFloor);
        } //end of main loop
    }
}