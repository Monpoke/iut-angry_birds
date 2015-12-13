/*
 *  Projet Angry Birds
 */
package angrybirds.hitbox;

import angrybirds.Tools;
import angrybirds.models.GameObjectModel;
import angrybirds.structures.Vector2d;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Pierre
 */
public class CircleHitbox extends HitBox {

    protected static int SPACE = 2;

    protected int diameter;

    /**
     * Circular hitbox.
     *
     * @param model
     * @param diameter
     */
    public CircleHitbox(GameObjectModel model, int diameter) {
        super(model);
        this.diameter = diameter;
    }

    public int getDiameter() {
        return diameter;
    }

    @Override
    public void debugPaint(Graphics g) {
        if (collided) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.GREEN);
        }
        g.fillOval((int) model.getPosition().getX() - (SPACE / 2) - diameter / 2, (int) model.getPosition().getY() - (SPACE / 2) - diameter / 2,
                diameter + SPACE, diameter + SPACE);
    }

    @Override
    public boolean intersect(HitBox h) {
        if (h instanceof CircleHitbox != true) {
            return false;
        }
        // calcul de la distance des centres
        Vector2d p1 = model.getPosition();
        Vector2d p2 = h.getModel().getPosition();

        double dist = Tools.distancePoints(p1.getX(), p2.getX(), p1.getY(), p2.getY());

        /**
         * Precision of 40%
         */
        double radius = 0.3 * Math.pow((((CircleHitbox) h).getDiameter()
                + getDiameter()), 2);

        return dist < radius;
    }

}
