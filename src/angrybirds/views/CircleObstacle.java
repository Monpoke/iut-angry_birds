/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds.views;

import angrybirds.Constants;
import angrybirds.controllers.GameObjectController;
import angrybirds.hitbox.CircleHitbox;
import angrybirds.hitbox.HitBox;
import angrybirds.models.GameObjectModel;
import angrybirds.models.ObstacleModel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Pierre
 */
public class CircleObstacle extends GameObjectView implements Observer, IDrawable {

    public CircleObstacle(GameObjectModel model, GameObjectController controller) {
        super(model, controller);
        model.setHitbox(new CircleHitbox(model, ((ObstacleModel)model).getDiameter()));
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.BLACK);
        int d = ((ObstacleModel)model).getDiameter();
        g.fillOval(
                (int)model.getPosition().getX() - d/2, 
                (int)model.getPosition().getY() - d/2, 
                d, 
                d
        );
        
    }

    @Override
    public void update(Observable o, Object arg) {
    }
    
    
    
}
