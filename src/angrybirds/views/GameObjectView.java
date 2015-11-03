/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds.views;

import angrybirds.Constants;
import angrybirds.controllers.GameObjectController;
import angrybirds.models.GameObjectModel;
import java.awt.Graphics;
import java.util.Observer;

/**
 *
 * @author Pierre
 */
public abstract class GameObjectView implements Observer {

    protected final GameObjectModel model;
    private final GameObjectController controller;

    /**
     * Gives a model to the view.
     *
     * @param model
     * @param controller
     */
    public GameObjectView(GameObjectModel model, GameObjectController controller) {
        this.model = model;
        this.controller = controller;
    }

    public GameObjectModel getModel() {
        return model;
    }

    public GameObjectController getController() {
        return controller;
    }

    /**
     * Draws debug hitbox.
     * @param g 
     */
    public void draw(Graphics g){
        if(Constants.DEBUG_MODE && model.hasCollision()){
            model.getHitbox().debugPaint(g);
        }
        
    }

}
