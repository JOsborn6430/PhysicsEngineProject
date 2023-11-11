public class Shapes {
    // Physical constants
    double mass = 1; //in Kg
    double friction = 0;

    // Kinematic state
    boolean isAtRest = false;
    boolean isStatic = false;
    double[] momentum = {0,0}; //in Kg * m/s
    double[] position = {0,0}; //in m
    double[] forceActing = {0,0}; //in N
}

class Box extends Shapes {
    int height = 10;
    int width = 10;
}

class Circle extends Shapes {
    int radius = 10;

}