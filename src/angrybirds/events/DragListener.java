/*
 *  Projet Angry Birds
 */
package angrybirds.events;

import angrybirds.Game;
import angrybirds.Tools;
import angrybirds.structures.Vector2d;
import angrybirds.views.Bird;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 *
 * @author pierre
 */
public class DragListener implements AngryEvent, MouseListener, MouseMotionListener {

    private Game game;
    private final JPanel pan;
    private int mouseX;
    private int mouseY;
    private Vector2d birdPosition;


    /**
     *
     * @param game
     */
    public DragListener(Game game, JPanel pan) {
        this.game = game;
        this.pan = pan;

    }

    
    
    @Override
    public void notif(Object data) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        System.out.println("MouseX: "+ mouseX + "; MouseY: " + mouseY);
        
        Bird bird = game.getBird();
        
        // center bird
        int birdX = (int)bird.getModel().getPosition().getX();
        int birdY = (int)bird.getModel().getPosition().getY();
        
        double dist = Tools.distancePoints(mouseX, birdX, mouseY, birdY);
        
        if(dist <= 420){
            pan.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            
            saveInitial(e);
        }
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pan.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("lo" + e.getX());
        
        Vector2d po = game.getBird().getModel().getPosition();
        
        int decalX = mouseX - e.getX();
        int decalY = mouseY - e.getY();
        
        double nX = birdPosition.getX() - decalX;
        double nY = birdPosition.getY() - decalY;
        
        if(Tools.distancePoints(birdPosition.getX(), nX, birdPosition.getY(), nY) <= 8000){
            po.setX(nX);
            po.setY(nY);
        }
        
        
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    private void saveInitial(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
        
        this.birdPosition = game.getBird().getModel().getPosition().copy();
    }

}
