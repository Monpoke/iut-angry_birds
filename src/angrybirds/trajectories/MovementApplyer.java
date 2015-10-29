/*
 *  Projet Angry Birds
 */
package angrybirds.trajectories;

import angrybirds.models.GameObjectModel;
import angrybirds.structures.Vector2d;
import angrybirds.views.Window;

/**
 *
 * @author Pierre
 */
public class MovementApplyer {
    protected Movement movement;
    protected GameObjectModel model;
    
    protected int startMovementTime = 0;
    protected final Vector2d startPosition;
    
    /**
     * Creates a movement applyer
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
        movement.process(model, this);
    }
    
    
}
