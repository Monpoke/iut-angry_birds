package angrybirds.models;


import angrybirds.Constants;
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
        
        // check position
        check();
        
        setChanged();
        notifyObservers();        
    }

    private void check() {
        if(position.getX() < 0){
            position.setX(0);
        }
        
        if(position.getY() < 0){
            position.setY(0);
        } 
        else if(position.getY() > Constants.WINDOW_HEIGHT - 10){
            position.setY(Constants.WINDOW_HEIGHT - 10);
        } 
    }
   
    
}
