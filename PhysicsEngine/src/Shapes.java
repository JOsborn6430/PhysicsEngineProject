public class Shapes {
    // Physical constants
    double mass = 1;
    double friction = 0;

    // Kinematic state

    boolean isStatic = false;
    double[] momentum = {0,0};
    double[] position = {0,0};
}

class Box extends Shapes {
    int height = 10;
    int width = 10;
}

class Circle extends Shapes {
    int radius = 10;

}