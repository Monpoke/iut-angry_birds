/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds.views;

import angrybirds.Constants;
import angrybirds.controllers.GameObjectController;
import angrybirds.hitbox.CircleHitbox;
import angrybirds.hitbox.HitBox;
import angrybirds.images.Ennemy;
import angrybirds.models.GameObjectModel;
import angrybirds.models.ObstacleModel;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Pierre
 */
public class CircleObstacle extends ShapeObstacle {

    private final Image image;
    private int cpt = 0;
    private int[] coords;

    public CircleObstacle(GameObjectModel model, GameObjectController controller) {
        super(model, controller);
        model.setHitbox(new CircleHitbox(model, ((ObstacleModel) model).getDiameter()));
        coords = new int[3];

        image = Ennemy.ROUND_BLUE.getImage();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.BLACK);
        int d = ((ObstacleModel) model).getDiameter();

        int x = (int) model.getPosition().getX();
        int y = (int) model.getPosition().getY();
        if (cpt == 0) {
            coords[0] = d;
            coords[1] = x;
            coords[2] = y;
        }
        g.fillOval(
                x - d / 2,
                y - d / 2,
                d,
                d
        );

        g.drawImage(image, x-d/2, y -d/2, d, d, this);


        cpt++;
        g.setColor(Color.WHITE);
        g.drawRect(x, y, 1, 1);
    }

    @Override
    public void update(Observable o, Object arg) {
    }

}
