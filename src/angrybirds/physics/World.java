package angrybirds.physics;

import angrybirds.views.GameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pierre on 16/01/2016.
 */
public class World {

    /**
     * Contains all bodies
     */
    protected final List<GameObject> bodies = new ArrayList<>();


    /**
     * Gets bodies
     */
    public List<GameObject> getBodies() {
        return bodies;
    }

    /**
     * Get body
     * @param id
     * @return
     */
    public GameObject getBody(int id){
        return bodies.get(id);
    }






    public void updateWorld(){

    }

}
