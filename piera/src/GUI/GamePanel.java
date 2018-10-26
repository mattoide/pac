package GUI;

import GameLogic.Giocatore;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import utils.Resources;

public class GamePanel extends JPanel {

    private final Image sfondoGamePanel;
    private final int LIMITE_DX_FRAME = 6400;
    private final int LIMITE_SX_FRAME = 6000;
    private final int LIMITE_GIU_FRAME = 6000;
    private final int LIMITE_SU_FRAME = 6000;

    private final int DX = 1;
    private final int SX = 2;
    private final int SU = 3;
    private final int GIU = 4;

    private final BufferedImage imgArancione;
    private final BufferedImage imgCeleste;
    private final BufferedImage imgRosa;
    private final BufferedImage imgRosso;

    private Giocatore player;
    private BufferedImage viteGiocatore;

    private Thread threadGeneratore;
    private Thread threadCollisioni;
    private Thread threadIncroci;

    public int movimentoCorrente;
    public boolean collisione = false;

    private Labirinto labirinto;

    private Line2D.Double[] lines;
    private Arc2D.Double[] arches;

    private Ghost rosso;
    private Ghost rosa;
    private Ghost celeste;
    private Ghost arancione;

    private Line linee;
    private Line2D.Double[] linesV;
private Line2D.Double[] linesO;

    private Pallino point;
    private ArrayList<Pallino> pallini;

    private Incrocio incrocio;
    private Rectangle[] incroci;
    private boolean incrocioAttivo = true;

    public GamePanel(Giocatore pGiocatore) {

        this.setSize(MainFrame.DIM_FRAME);

        this.sfondoGamePanel = Resources.getImage("/img/gamePanel.png");
        this.imgArancione = Resources.getImage("/img/arancione.png");
        this.imgCeleste = Resources.getImage("/img/celeste.png");
        this.imgRosa = Resources.getImage("/img/rosa.png");
        this.imgRosso = Resources.getImage("/img/rosso.png");

        this.player = pGiocatore;

        this.labirinto = new Labirinto();

        lines = labirinto.getLines();
        arches = labirinto.getArches();

        this.rosso = new Ghost(imgRosso, 66, 119, arches, lines);
        this.rosa = new Ghost(imgRosa, 423, 342, arches, lines);
        this.celeste = new Ghost(imgCeleste, 474, 341, arches, lines);
        this.arancione = new Ghost(imgArancione, 535, 341, arches, lines);

        this.linee = new Line();
        linesV = linee.getLinesV();
        linesO = linee.getLinesO();

        this.point = new Pallino(100, 100);
        pallini = new ArrayList<>();

        this.incrocio = new Incrocio();
        incroci = incrocio.getCross();
        this.threadIncroci = new Thread(new ThreadIncroci());

        this.threadCollisioni = new Thread(new ThreadCollisioni());

        this.addKeyListener(new KeyListener());
        this.startThread();

        this.addMouseListener(new MouseListener());

        generaPallini();
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.update(g);

        player.disegnaPac(g);

        rosso.disegna(g);
        this.labirinto.drawMaze((Graphics2D) g);

        linee.drawMaze((Graphics2D) g);
        disegnaPallini(g);

        incrocio.drawCross((Graphics2D) g);
    }

    @Override
    public void update(Graphics g) {

        this.viteGiocatore = returnVita(player);
        g.drawImage(viteGiocatore, 823, 676, this.getParent());

        g.drawImage(sfondoGamePanel, 0, 0, this.getParent());
        repaint();
    }

    public void generaPallini() {

        int distanzaPallini = 30;
        int aggiustaPosizione = distanzaPallini + 20;

        for (int j = 0; j < linesV.length; j++) {
            // for (int i = (int) lines[j].getY1(); i < 5; i++) {

            for (int i = (int) linesV[j].getY1() - aggiustaPosizione; i < (int) linesV[j].getY2() - aggiustaPosizione; i = i + distanzaPallini) {
                pallini.add(new Pallino((int) linesV[j].getX1() - 5, (int) linesV[j].getY1() + (i)));
            }
        }
        //
        //
        //
        //
        //
        /*for (int j=0; j < linesO.length; j++){
            
            for (int i = (int) linesO[j].getX1(); i < (int) linesO[j].getX2() - aggiustaPosizione; i = i + distanzaPallini) {
                pallini.add(new Pallino((int) linesO[j].getX1() - 5, (int) linesO[j].getX1() + (i)));
            }
        }*/
    }

    
    public void disegnaPallini(Graphics g) {

        for (int i = 0; i < pallini.size(); i++) {
            g.drawImage(point.getPoint(), pallini.get(i).getX(), pallini.get(i).getY(), point.getPoint().getWidth(), point.getPoint().getHeight(), null);
        }
    }

    public void mangiaPalline() {

        for (int i = 0; i < pallini.size(); i++) {
            if (player.getPacRect().intersects(pallini.get(i).getRect())) {
                pallini.remove(i);
            }
        }
    }

    public boolean pensaDoveAndare() {

        for (int i = 0; i < incroci.length; i++) {

            if (incroci[i].contains(rosso.getRect())) {
                System.out.println("ok");
                rosso.movimentoIncrocio();

                return true;
            } else {
                return false;
            }

        }
        return false;
    }

    public void startThread() {
        // this.threadGeneratore.start();
        this.threadCollisioni.start();
        this.threadIncroci.start();
    }

    public class ThreadGeneratore implements Runnable {

        @Override
        public void run() {
            while (player.isVivo()) {

                switch (movimentoCorrente) {

                    case DX:
                        player.movimentoDx();
                        repaint();
                        break;

                    case SX:
                        player.movimentoSx();
                        repaint();
                        break;

                    case SU:
                        player.movimentoSu();
                        repaint();
                        break;

                    case GIU:
                        player.movimentoGiu();
                        repaint();
                        break;
                }

                try {
                    Thread.sleep(player.getVelocita());
                } catch (InterruptedException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public class ThreadCollisioni implements Runnable {

        @Override
        public void run() {
            while (player.isVivo()) {

                collisioni();
                mangiaPalline();
                PacIntersectGhost();
                
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public class ThreadIncroci implements Runnable {

        private final Object pausa = new Object();
        private boolean valore;

        @Override
        public void run() {
            while (player.isVivo()) {

                valore = pensaDoveAndare();

                //System.out.println(valore);
                if (valore == true) {
                    synchronized (this) {
                       try {
                           //pausa.wait(10000);
                           this.wait(2000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public class KeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent ke) {

            switch (ke.getKeyCode()) {

                case KeyEvent.VK_RIGHT:

                    if (player.getPacRect().x <= LIMITE_DX_FRAME) {

                        player.PacDx();
                        player.movimentoDx();
                        movimentoCorrente = DX;

                    } else {
                        System.out.println("Errore: limite destro del Frame raggiunto");
                    }

                    collisione = false;
                    break;

                case KeyEvent.VK_LEFT:

                    if (player.getPacRect().x >= LIMITE_SX_FRAME) {

                        player.PacSx();
                        player.movimentoSx();
                        movimentoCorrente = SX;

                    } else {
                        //System.out.println("Errore");
                    }
                    collisione = false;
                    break;

                case KeyEvent.VK_UP:

                    player.PacSu();

                    if (player.getPacRect().y >= LIMITE_SU_FRAME) {

                        player.movimentoSu();
                        movimentoCorrente = SU;
                       
                    } else {
                        //System.out.println("Errore");
                    }
                    collisione = false;

                    break;

                case KeyEvent.VK_DOWN:

                    player.PacGiu();

                    if (player.getPacRect().y <= LIMITE_GIU_FRAME) {

                        player.movimentoGiu();
                        movimentoCorrente = GIU;

                    } else {
                        //System.out.println("Errore");
                    }

                    collisione = false;
                    break;
            }
        }
    }

    public void collisioni() {

        for (int i = 0; i < lines.length; i++) {

            if (player.getPacRect().intersectsLine(lines[i])) {
                collisione = true;
                //System.out.println("boooom");
            }

            for (int j = 0; j < arches.length; j++) {
                if (arches[j].intersects(player.getPacRect())) {
                    collisione = true;
                    //System.out.println("tocco");
                }
            }
            if ((collisione == true) && (movimentoCorrente == SX)) {

                player.movimentoDx();
                //System.out.println("non posso sxa");
                collisione = false;

            } else if ((collisione == true) && (movimentoCorrente == DX)) {

                player.movimentoSx();
                //System.out.println("non posso dx");
                collisione = false;

            } else if ((collisione == true) && (movimentoCorrente == SU)) {

                player.movimentoGiu();
                //System.out.println("non posso su");

                collisione = false;

            } else if ((collisione == true) && (movimentoCorrente == GIU)) {

                player.movimentoSu();
                //System.out.println("non posso giu");

                collisione = false;
            }
        }
    }

    public class MouseListener extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            Point p = e.getPoint();

            System.out.println(p);
        }
    }

    public void PacIntersectGhost() {
        if (player.getPacRect().intersects(rosso.getRect())) {
            player.restart();
            player.togliVita();
        }
    }

    private BufferedImage returnVita(Giocatore pPlayer) {

        BufferedImage vitePlayer = null;

        if (pPlayer.getVita() == 3) {
            vitePlayer = Resources.getImage("/img/3Vite.png");
        }
        if (pPlayer.getVita() == 2) {
            vitePlayer = Resources.getImage("/img/2Vite.png");
        }
        if (pPlayer.getVita() == 1) {
            vitePlayer = Resources.getImage("/img/1Vita.png");
        }

        return vitePlayer;
    }
}
