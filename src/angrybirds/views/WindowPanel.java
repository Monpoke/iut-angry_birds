/*
 *  Projet Angry Birds
 */
package angrybirds.views;

import angrybirds.Game;
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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        
        
        // clear screen
        g.setColor(Color.WHITE);
        //g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(bg, 0, 0, this);

        game.loop(g);
    }

    
}
