package angrybirds.views;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;


/**
 *
 * @author Messiaen kevin
 * @author Dumont paul
 *
 */
public class Frame extends JFrame implements ComponentListener {

    /**
     * Taille de la fenetre
     */
    Dimension dimension = new Dimension(800, 450);

    /**
     * crée une fenetre pour afficher le jeu
     */
    public Frame() {
        this.setTitle("Angry Birds");
        this.setSize(new Dimension(800, 450));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.addComponentListener(this);
    }

    /**
     * renvoie la taille actuelle de la fenetre
     *
     * @return dimension
     */
    public Dimension getDimension() {
        return dimension;
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        // TODO Auto-generated method stub
    }

    /**
     * actualise la taille de la fenetre lors des modifications
     */
    @Override
    public void componentResized(ComponentEvent e) {
        dimension = e.getComponent().getSize();
    }

    @Override
    public void componentShown(ComponentEvent e) {
        // TODO Auto-generated method stub
    }
}
