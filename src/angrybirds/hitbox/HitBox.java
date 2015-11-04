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

    
    public abstract void debugPaint(Graphics g);    
}
