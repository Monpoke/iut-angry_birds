/*
 *  Projet Angry Birds
 */
package angrybirds.hitbox;

import angrybirds.Tools;
import angrybirds.models.GameObjectModel;
import angrybirds.models.ObstacleModel;
import angrybirds.structures.Vector2d;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Pierre
 */
public class RectangleHitbox extends HitBox {

    protected static int SPACE = 2;

    protected int width;
    protected int height;

    /**
     * Circular hitbox.
     *
     * @param model
     * @param width
     * @param height
     */
    public RectangleHitbox(GameObjectModel model, int width, int height) {
        super(model);
        this.width = width;
        this.height = height;

    }

    @Override
    public void debugPaint(Graphics g) {
        if (collided) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.GREEN);
        }

        g.drawRect((int) model.getPosition().getX(), (int) model.getPosition().getY(), width, height);

    }

    @Override
    public boolean intersect(HitBox h) {
        if (h instanceof CircleHitbox != true) {
            return false;
        }

        if (h instanceof CircleHitbox) {
            return checkIntersect_Circle((CircleHitbox) h);
        }

        return false;
    }

    private boolean checkIntersect_Circle(CircleHitbox circle) {

        Vector2d circlePos = circle.getModel().getPosition();
        Vector2d rectPos = model.getPosition();

        double w2 = ((ObstacleModel) model).getWidth() / 2;
        double h2 = ((ObstacleModel) model).getHeight() / 2;

        double circleDistanceX = Math.abs(circlePos.getX() - (rectPos.getX() + w2));
        double circleDistanceY = Math.abs(circlePos.getY() - (rectPos.getY()));

        if (circleDistanceX > (w2 + circle.getDiameter())) {
            return false;
        }
        if (circleDistanceY > (h2 + circle.getDiameter())) {
            return false;
        }

        if (circleDistanceX <= w2) {
            return true;
        }

        if (circleDistanceY <= h2) {
            return true;
        }

//   double cornerDistance_sq = (circleDistanceX - Math.pow((ObstacleModel)model).getWidth()/2,2) +
//                         (circleDistanceY - Math.pow((ObstacleModel)model).getHeight()/2,2);
        double cornerDistance_sq = Math.pow(circleDistanceX - ((ObstacleModel) model).getWidth() / 2, 2)
                + Math.pow(circleDistanceY - ((ObstacleModel) model).getHeight() / 2, 2);

        return (cornerDistance_sq <= Math.pow(circle.getDiameter(), 2));
    }

}
