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
     * Returns true if the object has a movement.
     * @return 
     */
    public boolean hasMovement(){
        return movement != null;
    }

    /**
     * Returns the movement applyer.
     * @return 
     */
    public MovementApplyer getMovement() {
        return movement;
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

