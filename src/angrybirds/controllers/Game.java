/*
 *  Projet Angry Birds
 */
package angrybirds.controllers;

import angrybirds.Constants;
import angrybirds.models.Bird;
import angrybirds.models.IDrawable;
import angrybirds.models.Vector;
import angrybirds.views.Frame;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

/**
 *
 * @author Pierre
 */
public class Game {

    private final Container contentPane;

    private ArrayList<IDrawable> drawables = new ArrayList<>();
    private Bird bird;

    /**
     * Constructeur temporaire.
     */
    public Game() {

        Frame frame = new Frame();
        contentPane = frame.getContentPane();

        this.addBird();

        frame.setVisible(true);

        
        // lance le refresh
        this.refresh();
    }

    /**
     * La fonction ajoute un oiseau.
     */
    private void addBird() {
      bird = new Bird(new Point(60, 60), new Vector(2, 1));
        contentPane.add(new JComponent() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                bird.draw(g);
            }
        });
    }

    
    /**
     * Rafraîchit le content pane via un thread.
     */
    private void refresh() {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    contentPane.repaint();
                    Point p = bird.getPosition();
                    
                    bird.getPosition().setLocation(p.getX()+2, p.getY());
                    try {
                        Thread.sleep(Constants.REFRESH_TIME);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        t.start();
    }

}
