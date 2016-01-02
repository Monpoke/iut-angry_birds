package angrybirds.trajectories.physic;

import angrybirds.PhysicsConstants;

/**
 * Created by Pierre on 30/12/2015.
 */
public class Gravity extends Force {

    private double mass;

    public Gravity(double mass) {
        super(0, PhysicsConstants.G *mass);
        this.mass=mass;
    }
}
