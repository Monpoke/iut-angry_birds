/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds.controllers;

import angrybirds.models.GameObjectModel;
import angrybirds.trajectories.MovementApplyer;

/**
 *
 * @author Pierre
 */
public abstract class GameObjectController {
    protected final GameObjectModel model;

    protected MovementApplyer movement;
    
    /**
     * Gets the model
     * @param model 
     */
    public GameObjectController(GameObjectModel model) {
        this.model = model;
    }
    
    /**
     * Adds a movement.
     * @param mvt 
     */
    public void addMovement(MovementApplyer mvt){
        movement = mvt;
    }
    
    /**
     * Update loop.
     */
    public void update(){
        if(movement!=null){
            movement.apply();
        }
    }
    
}
