/*
 *  Projet Angry Birds
 */
package angrybirds.controllers;

import angrybirds.Constants;
import angrybirds.models.Bird;
import angrybirds.models.Drawable;
import angrybirds.views.Frame;
import java.awt.Container;
import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pierre
 */
public class Game {
    private final Container contentPane;

    private ArrayList<Drawable> drawables = new ArrayList<>();
    
    
    public Game() {
        
        Frame frame = new Frame();
        contentPane = frame.getContentPane();
        
        this.addBird();
        
        frame.setVisible(true);
        
        
        this.refresh();
        
    }

    
    
    /**
     * This function adds a bird
     */
    private void addBird() {
        Bird bird = new Bird(new Point(150, 200));
        drawables.add(bird);
    }

    

    /**
     * Refresh all MS
     */
    private void refresh() {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                contentPane.repaint();
                try {
                    Thread.sleep(Constants.REFRESH_TIME);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    
    
}
