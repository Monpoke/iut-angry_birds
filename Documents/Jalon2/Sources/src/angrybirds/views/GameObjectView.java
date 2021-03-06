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
import javax.swing.JPanel;

/**
 *
 * @author Pierre
 */
public abstract class GameObjectView extends JPanel implements Observer {

    protected final GameObjectModel model;
    private final GameObjectController controller;
    protected boolean hidden = false;

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

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * Draws debug hitbox.
     *
     * @param g
     */
    public void draw(Graphics g) {
        if ((Constants.ENABLE_PAINT_TRAJECTORIES || Constants.DEBUG_MODE) && controller.hasMovement()) {
            controller.getMovement().paintDebug(g);
        }
        
        if ((Constants.DEBUG_MODE || Constants.DEBUG_HITBOX)) {
            System.out.println("Je m'affiche...");
            if (model.hasCollision()) {
                model.getHitbox().debugPaint(g);
            }

        }

    }

}
