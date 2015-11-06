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
    
    /**
     * Contains a reference to the movement applyer.
     */
    protected MovementApplyer mvtApplyer = null;
    
    public abstract void process(GameObjectModel model, MovementApplyer mvt);
    
    public abstract double processY(double x);
    
    public abstract int findX();
    public abstract int findY();

    public void setMvtApplyer(MovementApplyer mvtApplyer) {
        this.mvtApplyer = mvtApplyer;
    }
    
    
}
