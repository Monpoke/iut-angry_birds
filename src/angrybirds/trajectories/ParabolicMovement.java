/*
 *  Projet Angry Birds
 */
package angrybirds.trajectories;

import angrybirds.Constants;
import angrybirds.models.GameObjectModel;
import angrybirds.structures.Vector2d;
import angrybirds.views.Window;

/**
 *
 * @author Pierre
 */
public class ParabolicMovement extends Movement {

    private final String params;
    private final double a;
    private final double b;
    private final double c;

    /**
     * Gets a linear movement
     * @param params 
     */
    public ParabolicMovement(String params) {
        this.params = params;
        
        String[] split = params.split(" ");
        a = Double.parseDouble(split[0]);
        b = Double.parseDouble(split[1]);
        c = Double.parseDouble(split[2]);
    }

    /**
     * Process
     * @param model
     * @param mvt 
     */
    @Override
    public void process(GameObjectModel model, MovementApplyer mvt) {
        Vector2d position = model.getPosition();
        
        /**
         * @TODO:
         * Find parabolic
         * ax² + bx + c
         * 
         * I have to resolve x=0 with DELTA (in order to find decalage)
         */
    }

}
