package GUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class Incrocio {

    private Rectangle g0, g1, g2, g3, g4, g5, g6, g7, g8, g9, g10;

    private Rectangle[] cross;

    public Incrocio() {

        //1
        g0 = new Rectangle(50, 30, 42, 42);
        g1 = new Rectangle(50, 110, 40, 40);
        g2 = new Rectangle(50, 183, 40, 40);
        g3 = new Rectangle(50, 452, 40, 40);
        g4 = new Rectangle(50, 515, 40, 40);
        g5 = new Rectangle(50, 584, 40, 40);
        g6 = new Rectangle(50, 642, 40, 40);
//2
        //g3 = new Rectangle(232, 27, 40, 40);

        //g2 = new Rectangle(232, 110, 42, 42);
        //g3 = new Rectangle(232, 180, 39,39);
        cross = new Rectangle[]{
            g0, g1, g2, g3, g4, g5, g6
        };
    }

    public void drawCross(Graphics2D cross) {

        cross.setPaint(Color.white);

        cross.draw(g0);
        cross.draw(g1);
        cross.draw(g2);
        cross.draw(g3);
        cross.draw(g4);
        cross.draw(g5);
        cross.draw(g6);
//        cross.draw(g7);

    }

    public Rectangle[] getCross() {
        return cross;
    }

}
