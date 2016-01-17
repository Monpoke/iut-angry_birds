/*
 *  Projet Angry Birds
 */
package angrybirds.events;

import angrybirds.Constants;
import angrybirds.Game;
import angrybirds.Tools;
import angrybirds.models.BirdModel;
import angrybirds.structures.Vector2d;
import angrybirds.trajectories.MovementApplyer;
import angrybirds.trajectories.curves.GravityMovement;
import angrybirds.trajectories.physic.Force;
import angrybirds.views.Bird;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

/**
 * DragListener
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

    /**
     * Notif
     * @param data 
     */
    @Override
    public void notif(Object data) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(SwingUtilities.isMiddleMouseButton(e)){
            Game.BLOCK_STATUS = !Game.BLOCK_STATUS;
        }
    }

    /**
     * When the mouse is pressed.
     * @param e 
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (((BirdModel) game.getBird().getModel()).isHasBeenLaunched() == true) {
            return;
        }

        int mouseX = e.getX();
        int mouseY = e.getY();

        Bird bird = game.getBird();

        // center bird
        int birdX = (int) bird.getModel().getPosition().getX();
        int birdY = (int) bird.getModel().getPosition().getY();

        double dist = Tools.distancePoints(mouseX, birdX, mouseY, birdY);

        if (dist <= 420) {
            pan.setCursor(new Cursor(Cursor.MOVE_CURSOR));

            saveInitial(e);
        }

    }

    /***
     * When the mouse is released.
     * @param e 
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        pan.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        if (!Constants.DEBUG_DRAG && birdPosition != null) {
            // bird has been released
            // calcul du vecteur
            Vector2d pOri = game.getBird().getModel().getPosition();
            Vector2d force = new Vector2d(birdPosition.getX() - pOri.getX(),
                    (pOri.getY() - birdPosition.getY()));

            double angle = getCurrentAngle();

            
            this.launchMovement(force, angle);

            birdPosition = null;
            ((BirdModel) game.getBird().getModel()).setHasBeenLaunched(true);

        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * When the mouse is dragged.
     * @param e 
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (birdPosition == null) {
            return;
        }

        Vector2d po = game.getBird().getModel().getPosition();

        int decalX = mouseX - e.getX();
        int decalY = mouseY - e.getY();

        double nX = birdPosition.getX() - decalX;
        double nY = birdPosition.getY() - decalY;

        if (Constants.DEBUG_DRAG || Tools.distancePoints(birdPosition.getX(), nX, birdPosition.getY(), nY) <= 8000) {
            po.setX(nX);
            po.setY(nY);
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    /**
     * Saves initial saves.
     * @param e 
     */
    private void saveInitial(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();

        this.birdPosition = game.getBird().getModel().getPosition().copy();
    }

    /**
     * Launch movememnt.
     *
     * @param force
     */
    private void launchMovement(Vector2d force, double angle) {
        System.out.println("Force: " + force.toString());

        Bird bird = game.getBird();

        /*GravityMovement gm = new GravityMovement(force, angle);
        MovementApplyer ma = new MovementApplyer(gm, bird.getModel());
        bird.getController().addMovement(ma);
        */

        bird.getModel().setVelocity(new Force(2,-0.9));
        bird.getModel().setCanMove(true);

    }

    private double getCurrentAngle() {

        // bird has been released
        // calcul du vecteur
        Vector2d pOri = game.getBird().getModel().getPosition();
        Vector2d force = new Vector2d(birdPosition.getX() - pOri.getX(),
                (pOri.getY() - birdPosition.getY()));

        // ANGLE
        float angle = (float) Math.toDegrees(Math.atan2(birdPosition.getY() - pOri.getY(), birdPosition.getX() - pOri.getX()));
        
        angle+=180;
        
        if(angle < 0){
            angle+=360;
        }
        
        angle = (-angle + 180 + 360) % 360;
        
        
        return angle;

    }

}
