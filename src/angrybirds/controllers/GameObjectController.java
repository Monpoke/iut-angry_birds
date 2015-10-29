/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds.controllers;

import angrybirds.models.GameObjectModel;

/**
 *
 * @author Pierre
 */
public abstract class GameObjectController {
    protected final GameObjectModel model;

    /**
     * Gets the model
     * @param model 
     */
    public GameObjectController(GameObjectModel model) {
        this.model = model;
    }
    
    
    
    public abstract void update();
    
}
