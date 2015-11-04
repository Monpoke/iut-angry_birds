/*
 *  Projet Angry Birds
 */
package angrybirds.hitbox;

import angrybirds.models.GameObjectModel;
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

    @Override
    public void debugPaint(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval((int) model.getPosition().getX() - (SPACE / 2) - diameter / 2, (int) model.getPosition().getY() - (SPACE / 2) - diameter / 2,
                diameter + SPACE, diameter + SPACE);
    }

    
    public boolean intersectCircle(CircleHitbox circle){
        return false;
    }
    
}
