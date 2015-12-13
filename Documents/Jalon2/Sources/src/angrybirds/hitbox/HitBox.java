/*
 *  Projet Angry Birds
 */
package angrybirds.hitbox;

import angrybirds.models.GameObjectModel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pierre
 */
public abstract class HitBox {
    protected final GameObjectModel model;
    
    protected final List<GameObjectModel> objectsCollided;
    
    protected boolean collided = false;
    
    /**
     * Is the collision cause of death?
     */
    protected boolean killer = false;
    
    
    public HitBox(GameObjectModel model) {
        this.model = model;
        objectsCollided=new ArrayList<>();
    }

    public void setKiller(boolean killer) {
        this.killer = killer;
    }

    public boolean isKiller() {
        return killer;
    }

    public boolean isCollided() {
        return collided;
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }

    
    
    public GameObjectModel getModel() {
        return model;
    }

    
    public abstract void debugPaint(Graphics g);    
    
    public abstract boolean intersect(HitBox h);    
}
