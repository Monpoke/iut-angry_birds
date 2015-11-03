/*
 *  Projet Angry Birds
 */
package angrybirds.hitbox;

import angrybirds.models.GameObjectModel;
import java.awt.Graphics;

/**
 *
 * @author Pierre
 */
public abstract class HitBox {
    protected final GameObjectModel model;

    public HitBox(GameObjectModel model) {
        this.model = model;
    }

    
    public abstract void debugPaint(Graphics g);    
}
