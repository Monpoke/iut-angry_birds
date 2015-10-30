/*
 *  Projet Angry Birds
 */
package angrybirds.trajectories.curves;

import angrybirds.Constants;
import angrybirds.models.GameObjectModel;
import angrybirds.structures.Vector2d;
import angrybirds.trajectories.Movement;
import angrybirds.trajectories.MovementApplyer;
import angrybirds.views.Window;

/**
 *
 * @author Pierre
 */
public class LinearMovement extends Movement {

    private final String type;
    private final int by;

    private int x, y;

    /**
     * Gets a linear movement
     * @param type
     * @param by 
     */
    public LinearMovement(String type, int by) {
        this.type = type;
        this.by = by;
    }

    /**
     * Process
     * @param model
     * @param mvt 
     */
    @Override
    public void process(GameObjectModel model, MovementApplyer mvt) {
        Vector2d position = model.getPosition();
        
        if (type.equals("vertical")) {
            position.setY(mvt.getStartPosition().getY() - (Window.getRefreshTimes() - mvt.getStartMovementTime()) * by);
        } else {
            position.setX(position.getX() + by);
        }
    }
}
