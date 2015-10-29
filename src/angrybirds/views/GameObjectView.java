/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds.views;

import angrybirds.models.GameObjectModel;
import java.awt.Graphics;
import java.util.Observer;

/**
 *
 * @author Pierre
 */
public abstract class GameObjectView implements Observer {

    protected final GameObjectModel model;

    /**
     * Gives a model to the view.
     *
     * @param model
     */
    public GameObjectView(GameObjectModel model) {
        this.model = model;
    }

    public GameObjectModel getModel() {
        return model;
    }
    
    
    
    public abstract void draw(Graphics g);

}
