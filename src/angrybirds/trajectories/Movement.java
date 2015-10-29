/*
 *  Projet Angry Birds
 */
package angrybirds.trajectories;

import angrybirds.models.GameObjectModel;

/**
 *
 * @author Pierre
 */
public abstract class Movement {

    public abstract void process(GameObjectModel model, MovementApplyer mvt);
}
