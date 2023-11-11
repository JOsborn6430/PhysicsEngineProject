import java.awt.*;

public class Render {

    public static final int WIDTH = 700;
    public static final int HEIGHT = 400;
    public static Graphics initGraphics() {
        DrawingPanel panel = new DrawingPanel(WIDTH, HEIGHT);
        Graphics g = panel.getGraphics();
        g.setColor(Color.RED);
        return g;
    }

    public static void drawObject(Graphics g, Shapes s) {
        if (s instanceof Box) {
            g.fillRect((int) ((Box) s).position[0] - ((Box) s).width/2, HEIGHT - (int) ((Box) s).position[1] - ((Box) s).height/2, ((Box) s).width, ((Box) s).height);
        }
        else if (s instanceof Circle) {
            g.fillOval((int) s.position[0] - ((Circle) s).radius, (int) s.position[1] - ((Circle) s).radius, 2*((Circle) s).radius, 2*((Circle) s).radius);
        }
    }

}
