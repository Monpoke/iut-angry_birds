/*
 *  Projet Angry Birds
 */
package angrybirds.trajectories;

import angrybirds.Constants;
import angrybirds.Tools;
import angrybirds.models.GameObjectModel;
import angrybirds.structures.Vector2d;
import angrybirds.views.Window;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Apply a movement to a game object.
 *
 * @author Pierre
 */
public class MovementApplyer {

    protected Movement movement;
    protected GameObjectModel model;

    protected int startMovementTime = 0;
    protected final Vector2d startPosition;
    protected boolean stopped = false;
    private int same;

    /**
     * Creates a movement applyer
     *
     * @param movement
     * @param model
     */
    public MovementApplyer(Movement movement, GameObjectModel model) {
        this.movement = movement;
        this.model = model;
        this.startPosition = model.getPosition().copy();
        this.startMovementTime = Window.getRefreshTimes();
    }

    /**
     * Apply the movement
     */
    public void apply() {
        if (!stopped) {
            movement.process(model, this);
        }
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public GameObjectModel getModel() {
        return model;
    }

    public void setModel(GameObjectModel model) {
        this.model = model;
    }

    public int getStartMovementTime() {
        return startMovementTime;
    }

    public void setStartMovementTime(int startMovementTime) {
        this.startMovementTime = startMovementTime;
    }

    public Vector2d getStartPosition() {
        return startPosition;
    }

    public void stop() {
        stopped = true;
    }
    
    public int getEllapsedTime(){
        return Window.getRefreshTimes() - getStartMovementTime();
    }

    /**
     * Paint for debug.
     *
     * @param g
     */
    public void paintDebug(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        movement.setMvtApplyer(this);
        for (double x = startPosition.getX(); x <= (Constants.DEBUG_TRAJECTORY ? Constants.WINDOW_WIDTH: model.getPosition().getX()); x += 20) {
            g.fillOval((int) x - 3, (int) (startPosition.getY() - movement.processY(x - startPosition.getX()) - 3), 6, 6);
        }
    }

}
