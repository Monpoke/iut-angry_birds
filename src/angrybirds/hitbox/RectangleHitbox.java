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
        else if (h instanceof RectangleHitbox) {
            return checkIntersect_Rectangle((RectangleHitbox) h);
        }

        return false;
    }

    private boolean checkIntersect_Rectangle(RectangleHitbox h) {
        return false;
    }

    private boolean checkIntersect_Circle(CircleHitbox circle) {

        Vector2d pCir = circle.getModel().getPosition();
        Vector2d pRect = this.getModel().getPosition();



        boolean c = Tools.intersectionCircleAndRectangle(
                (int) pCir.getX(),
                (int) pCir.getY(),
                circle.getDiameter() / 2,
                (int) pRect.getX(),
                (int) pRect.getY(),
                width,
                height
        );
        
        return c;
    }

}
