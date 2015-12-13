/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds.models;

import angrybirds.events.AngryEvent;
import angrybirds.structures.Vector2d;
import angrybirds.views.Bird;
import java.util.ArrayList;
import java.util.List;

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
     * Has the bird been launched?
     */
    protected boolean hasBeenLaunched = false;
    
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
                m.sendNotification();
            }
        });
    }

    public void addView(Bird view) {
        this.addObserver(view);
        this.view = view;
    }
    
    

    public boolean isAlive(){
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isHasBeenLaunched() {
        return hasBeenLaunched;
    }

    public void setHasBeenLaunched(boolean hasBeenLaunched) {
        this.hasBeenLaunched = hasBeenLaunched;
    }

    
    
 
    
    
}
