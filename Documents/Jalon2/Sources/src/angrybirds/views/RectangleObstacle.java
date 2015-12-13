/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds.views;

import angrybirds.controllers.GameObjectController;
import angrybirds.hitbox.CircleHitbox;
import angrybirds.hitbox.RectangleHitbox;
import angrybirds.models.GameObjectModel;
import angrybirds.models.ObstacleModel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

/**
 * Rectangle obstacle.
 *
 * @author Pierre
 */
public class RectangleObstacle extends GameObjectView implements Observer, IDrawable {

    public RectangleObstacle(GameObjectModel model, GameObjectController controller) {
        super(model, controller);
        
        // set a rectangle hitbox
        model.setHitbox(new RectangleHitbox(model, ((ObstacleModel) model).getWidth(), ((ObstacleModel) model).getHeight()));
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        if (!hidden) {
            int x = (int) model.getPosition().getX();
            int y = (int) model.getPosition().getY();
            int width = (int) ((ObstacleModel) model).getWidth();
            int height = (int) ((ObstacleModel) model).getHeight();

            g.setColor(Color.BLACK);
            g.fillRect(x, y, width, height);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
    }

}
