/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds.views;

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

    public CircleObstacle(ObstacleModel model) {
        super(model);
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(
                (int)model.getPosition().getX(), 
                (int)model.getPosition().getY(), 
                ((ObstacleModel)model).getDiameter(), 
                ((ObstacleModel)model).getDiameter()
        );
    }

    @Override
    public void update(Observable o, Object arg) {
    }
    
    
    
}
