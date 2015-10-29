package angrybirds.models;


import angrybirds.structures.Vector2d;
import angrybirds.views.GameObjectView;
import java.util.Observable;

/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
/**
 * Represents basic views.
 *
 * @author Pierre
 */
public abstract class GameObjectModel extends Observable {
    
    /**
     * Object position.
     */
    protected Vector2d position;

    public Vector2d getPosition() {
        return position;
    }

    /**
     * Notify all observers, all registred views.
     */
    public void sendNotification(){
        setChanged();
        notifyObservers();        
    }
   
    
}
