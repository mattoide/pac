package GUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class Incrocio {

    private Rectangle g0, g1, g2, g3, g4, g5, g6, g7, g8, g9, g10;

    private Rectangle[] cross;

    public Incrocio() {
        
        g1 = new Rectangle(232, 27, 39, 39);
        //g0 = new Rectangle(52, 110, 39,39);
        //g2 = new Rectangle(232, 110, 39,39);
        //g3 = new Rectangle(232, 180, 39,39);
        
        cross = new Rectangle[]{
           // g0, g1, g2, g3
        };
    }

    public void drawCross(Graphics2D cross) {

        cross.setPaint(Color.white);

        cross.draw(g0);
        cross.draw(g1);
        cross.draw(g2);
        cross.draw(g3);
        
    }

    public Rectangle[] getCross() {
        return cross;
    }
    

}
