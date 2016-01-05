package angrybirds.trajectories.physic;

import angrybirds.Constants;
import angrybirds.models.GameObjectModel;
import angrybirds.trajectories.Movement;
import angrybirds.trajectories.MovementApplyer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pierre on 30/12/2015.
 */
public class Motor extends Movement {

    /**
     * Gravity is the default
     */
    public Motor(double mass) {
        addForce(new Gravity(mass));

    }

    public Motor(){

    }


    public void addConstantForce(Force f){
        mvtApplyer.getModel().getConstantsForces().add(f);
    }

    public void addForce(Force force) {

        if(mvtApplyer== null){
            System.exit(1);
        }

       // mvtApplyer.getModel().getForces().add(force);
    }


    /**
     * Process positions
     *
     * @param model
     * @param mvt
     * @todo Have to include forces
     */
    @Override
    public void process(GameObjectModel model, MovementApplyer mvt) {

    }

    @Override
    public double processY(double x) {
        return 0;
    }

    @Override
    public int findX() {
        return 0;
    }

    @Override
    public int findY() {
        return 0;
    }
}
