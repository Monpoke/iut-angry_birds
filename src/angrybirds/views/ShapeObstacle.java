/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds.views;

import angrybirds.controllers.GameObjectController;
import angrybirds.hitbox.CircleHitbox;
import angrybirds.models.GameObjectModel;
import angrybirds.models.ObstacleModel;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Pierre
 */
abstract public class ShapeObstacle extends GameObjectView implements Observer, IDrawable {

    /**
     * Gives a model to the view.
     *
     * @param model
     * @param controller
     */
    public ShapeObstacle(GameObjectModel model, GameObjectController controller) {
        super(model, controller);
    }
}
