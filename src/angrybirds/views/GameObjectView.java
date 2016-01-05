/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds.views;

import angrybirds.Constants;
import angrybirds.controllers.GameObjectController;
import angrybirds.models.GameObjectModel;
import angrybirds.trajectories.physic.Force;
import angrybirds.trajectories.physic.Motor;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.awt.*;
import java.util.Observer;
import javax.swing.JPanel;

/**
 * @author Pierre
 */
public abstract class GameObjectView extends JPanel implements Observer {

    protected final GameObjectModel model;
    private final GameObjectController controller;
    protected boolean hidden = false;


    private static Font f = new Font("Arial", Font.PLAIN, 12);

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
            if (model.hasCollision()) {
                model.getHitbox().debugPaint(g);
            }

        }

        if (Constants.DEBUG_INFOS) {
            g.setFont(f);
            g.setColor(Color.BLACK);
            g.drawString("x:" + model.getPosition().getX(), (int) model.getPosition().getX() - 5, (int) model.getPosition().getY() - 55);
            g.drawString("y:" + model.getPosition().getY(), (int) model.getPosition().getX() - 5, (int) model.getPosition().getY() - 45);
            g.drawString("m:" + model.getMass(), (int) model.getPosition().getX() - 5, (int) model.getPosition().getY() - 35);


            //force result
            g.drawString("Acceleration: " + model.getAcceleration().toString(), (int) model.getPosition().getX() - 5, (int) model.getPosition().getY() - 75);
            g.drawString("Velocity: " + model.getVelocity().toString(), (int) model.getPosition().getX() - 5, (int) model.getPosition().getY() - 105);

        }

    }

}
