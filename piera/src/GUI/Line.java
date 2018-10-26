package GUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Line {

    private Line2D.Double currentLine0, currentLine1, currentLine2, currentLine3, currentLine4, currentLine5,
            currentLine6, currentLine7, currentLine8, currentLine9, currentLine10, currentLine11,
            currentLine12, currentLine13, currentLine14, currentLine15, currentLine16, currentLine17,
            currentLine18, currentLine19, currentLine20, currentLine21, currentLine22, currentLine23;

    private Line2D.Double[] linesV;

    private Line2D.Double currentLine24, currentLine25, currentLine26, currentLine27, currentLine28, currentLine29;

    private Line2D.Double[] linesO;

    public Line() {
        //1
        currentLine0 = new Line2D.Double(65, 49, 65, 185);
        currentLine1 = new Line2D.Double(247, 49, 247, 575);
        currentLine2 = new Line2D.Double(454, 49, 454, 116);
        currentLine3 = new Line2D.Double(567, 49, 567, 128);
        currentLine4 = new Line2D.Double(780, 49, 780, 575);
        currentLine5 = new Line2D.Double(953, 49, 953, 185);
        //2
        currentLine6 = new Line2D.Double(351, 90, 351, 155);
        currentLine7 = new Line2D.Double(668, 90, 668, 155);
        //3
        currentLine8 = new Line2D.Double(457, 119, 457, 180);
        currentLine9 = new Line2D.Double(570, 119, 570, 180);
        //4
        currentLine10 = new Line2D.Double(351, 152, 351, 361);
        currentLine11 = new Line2D.Double(669, 152, 669, 361);
        //5
        currentLine12 = new Line2D.Double(466, 250, 466, 320);
        currentLine13 = new Line2D.Double(65, 250, 65, 320);
        currentLine14 = new Line2D.Double(570, 250, 570, 320);
        currentLine15 = new Line2D.Double(960, 250, 960, 320);
        //6
        currentLine16 = new Line2D.Double(667, 280, 667, 360);
        currentLine17 = new Line2D.Double(140, 280, 140, 360);
        currentLine18 = new Line2D.Double(352, 280, 352, 360);
        currentLine19 = new Line2D.Double(886, 280, 886, 360);
        //7
        currentLine20 = new Line2D.Double(65, 314, 65, 380);
        currentLine21 = new Line2D.Double(455, 314, 455, 380);
        currentLine22 = new Line2D.Double(565, 314, 565, 380);
        currentLine23 = new Line2D.Double(955, 314, 955, 380);

        linesV = new Line2D.Double[]{
            currentLine0, currentLine1, currentLine2, currentLine3, currentLine4, currentLine5,
            currentLine6, currentLine7, currentLine8, currentLine9, currentLine10, currentLine11,
            currentLine12, currentLine13, currentLine14, currentLine15, currentLine16, currentLine17,
            currentLine18, currentLine19, currentLine20, currentLine21, currentLine22, currentLine23
        };

        //1
        currentLine24 = new Line2D.Double(66, 52, 455, 52);
        currentLine25 = new Line2D.Double(568, 48, 952, 48);
        /*currentLine26 = new Line2D.Double(668, 90, 668, 155);
        currentLine27 = new Line2D.Double(457, 119, 457, 180);
        currentLine9 = new Line2D.Double(570, 119, 570, 180);
         */
        linesO = new Line2D.Double[]{
        currentLine24,currentLine25
        };
    }

    public Line2D.Double[] getLinesV() {
        return linesV;
    }

    public Line2D.Double[] getLinesO() {
        return linesO;
    }
    
    public void drawMaze(Graphics2D maze) {

        maze.setPaint(Color.white);

        /*
        //1
        maze.draw(currentLine0);
        maze.draw(currentLine1);
        maze.draw(currentLine2);
        maze.draw(currentLine3);
        maze.draw(currentLine4);
        maze.draw(currentLine5);
        //2 
        maze.draw(currentLine6);
        maze.draw(currentLine7);
        //3   
        maze.draw(currentLine8);
        maze.draw(currentLine9);
        //4
        maze.draw(currentLine10);
        maze.draw(currentLine11);
        //5
        maze.draw(currentLine12);
        maze.draw(currentLine13);
        maze.draw(currentLine14);
        maze.draw(currentLine15);
        //6
        maze.draw(currentLine16);
        maze.draw(currentLine17);
        maze.draw(currentLine18);
        maze.draw(currentLine19);
        //7
        maze.draw(currentLine20);
        maze.draw(currentLine21);
        maze.draw(currentLine22);
        maze.draw(currentLine23);
         */
        
        
        //1
        maze.draw(currentLine24);
        maze.draw(currentLine25);
       /* maze.draw(currentLine27);
        maze.draw(currentLine28);
        maze.draw(currentLine29);
*/
    }

}
