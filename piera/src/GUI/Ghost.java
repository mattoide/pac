package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ghost extends Thread {

    private final int DX = 1;
    private final int SX = 2;
    private final int SU = 3;
    private final int GIU = 4;

    Rectangle rect;
    BufferedImage image;

    private int velocita;

    public boolean attivo;

    public boolean collisione = false;

    private Line2D.Double[] lines;
    private Arc2D.Double[] arches;

    public int movimentoCorrente;

    public Ghost(BufferedImage img, int px, int py, Arc2D.Double[] parches, Line2D.Double[] plines) {

        this.image = img;
        this.rect = new Rectangle(px, py, image.getWidth(null), image.getHeight(null));

        this.velocita = 2;

        this.lines = plines;
        this.arches = parches;

        movimentoCorrente = SU;

        this.start();
    }

    public BufferedImage getRed() {
        return image;
    }

    public void disegna(Graphics g) {
        //g.drawImage(image, rect.x, rect.y, image.getWidth(), image.getHeight(), null);
        g.setColor(Color.red);
        g.fillRect(rect.x, rect.y, image.getWidth(), image.getHeight());
    }

    public Rectangle getRect() {
        return rect;
    }

    public void movimentoDx() {
        this.rect.x += this.velocita;
        //System.out.println("dx");
    }

    public void movimentoSx() {
        this.rect.x -= this.velocita;
    }

    public void movimentoSu() {
        this.rect.y -= this.velocita;
    }

    public void movimentoGiu() {
        this.rect.y += this.velocita;

    }

    @Override
    public void run() {
        this.attivo = true;
        while (this.attivo) {

            collisioni();
            //System.out.println("ssisisis");
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ghost.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void movimento() {

        int randomNum = ThreadLocalRandom.current().nextInt(0, 1000);

        if (randomNum >= 0 && randomNum < 250) {
            movimentoCorrente = DX;
            movimentoDx();
        } else if (randomNum >= 250 && randomNum < 500) {
            movimentoCorrente = SU;
            movimentoSu();
        } else if (randomNum >= 500 && randomNum < 750) {
            movimentoCorrente = SX;
            movimentoSx();
        } else if (randomNum >= 750 && randomNum <= 1000) {
            movimentoCorrente = GIU;
            movimentoGiu();
        }
    }

    public void movimentoIncrocio() {

        int randomNum = ThreadLocalRandom.current().nextInt(0, 900);

        switch (movimentoCorrente) {

            case DX:
                if (randomNum >= 0 && randomNum < 300) {
                    movimentoCorrente = DX;
                    movimentoDx();
                } else if (randomNum >= 300 && randomNum < 600) {
                    movimentoCorrente = SU;
                    movimentoSu();

                } else if (randomNum >= 600 && randomNum <= 900) {
                    movimentoCorrente = GIU;
                    movimentoGiu();
                }
                break;

            case SX:
                if (randomNum >= 0 && randomNum < 300) {
                    movimentoCorrente = SX;
                    movimentoSx();
                } else if (randomNum >= 300 && randomNum < 600) {
                    movimentoCorrente = SU;
                    movimentoSu();

                } else if (randomNum >= 600 && randomNum <= 900) {
                    movimentoCorrente = GIU;
                    movimentoGiu();
                }
                break;

            case SU:
                if (randomNum >= 0 && randomNum < 300) {
                    movimentoCorrente = DX;
                    movimentoDx();
                } else if (randomNum >= 300 && randomNum < 600) {
                    movimentoCorrente = SX;
                    movimentoSx();

                } else if (randomNum >= 600 && randomNum <= 900) {
                    movimentoCorrente = SU;
                    movimentoSu();
                }
                break;

            case GIU:
                if (randomNum >= 0 && randomNum < 300) {
                    movimentoCorrente = DX;
                    movimentoDx();
                } else if (randomNum >= 300 && randomNum < 600) {
                    movimentoCorrente = SX;
                    movimentoSx();

                } else if (randomNum >= 600 && randomNum <= 900) {
                    movimentoCorrente = GIU;
                    movimentoGiu();
                }
        }
    }

    public void collisioni() {

        for (int i = 0; i < lines.length; i++) {
            if (this.getRect().intersectsLine(lines[i])) {
                collisione = true;
                //System.out.println("boooom");
            }
        }

        for (int j = 0; j < arches.length; j++) {
            if (arches[j].intersects(this.getRect())) {
                collisione = true;
                //System.out.println("tocco");
            }
        }

        if (collisione == false) {

            switch (movimentoCorrente) {

                case DX:
                    movimentoDx();
                    collisione = false;
                    //System.out.println("DESTRA");
                    break;

                case SX:
                    movimentoSx();
                    collisione = false;
                    //System.out.println("SX");
                    break;

                case SU:
                    movimentoSu();
                    collisione = false;
                    //System.out.println("SU");
                    break;

                case GIU:
                    movimentoGiu();
                    collisione = false;
                    //System.out.println("GIU");
                    break;
            }
        } else {
            collisione = false;

            switch (movimentoCorrente) {

                case DX:
                    movimentoSx();
                    break;

                case SX:
                    movimentoDx();
                    //System.out.println("non posso sxa");
                    break;

                case SU:
                    movimentoGiu();
                    //System.out.println("non posso su");
                    break;

                case GIU:
                    movimentoSu();
                    //System.out.println("non posso giu");
                    break;
            }
            movimento();
        }
    }
}
