/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds.models;

import angrybirds.AngryEvent;
import angrybirds.structures.Vector2d;
import angrybirds.views.Bird;
import angrybirds.views.GameObjectView;

/**
 *
 * @author Pierre
 */
public class BirdModel extends GameObjectModel {

    /**
     * Sets the bird alive or not
     */
    protected boolean isAlive = true;
    
    /**
     * Sets bird position
     *
     * @param position
     */
    public BirdModel(Vector2d position) {
        this.position = position;
        final BirdModel m = this;
        this.position.addChangeListener(new AngryEvent() {
            @Override
            public void notif(Object data) {
                m.notifyObservers();
            }
        });
    }

    public void addView(Bird view) {
        this.addObserver(view);
    }

    public boolean isAlive(){
        return isAlive;
    }
    
}