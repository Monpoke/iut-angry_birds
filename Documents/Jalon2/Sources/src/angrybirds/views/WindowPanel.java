/*
 *  Projet Angry Birds
 */
package angrybirds.views;

import angrybirds.Game;
import angrybirds.events.DragListener;
import angrybirds.images.Background;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author Pierre
 */
public class WindowPanel extends JPanel {
    private final Game game;
    private final Image bg;

    
    WindowPanel(Game game) {
        this.game = game;
        this.bg = Background.DEFAULT.getImage();
        
        // Register an event listener
        DragListener event = new DragListener(game, this);
        this.addMouseListener(event);
        this.addMouseMotionListener(event);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        
        
        // clear screen
        g.setColor(Color.WHITE);
        //g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), Color.WHITE, this);

        game.loop(g);
    }

    
}
