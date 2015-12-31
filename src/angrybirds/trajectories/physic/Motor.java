package angrybirds.trajectories.physic;

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
     * Physics motor
     */
    private List<Force> forces = new ArrayList<>();


    public Motor() {
        addForce(new Gravity());
    }


    public Force getForceResult() {

        int forcesNb = forces.size();

        int xDiv = 0;
        int yDiv = 0;

        for (Force force : forces) {
            xDiv += Math.abs(force.getX());
            yDiv += Math.abs(force.getY());
        }

        return new Force(xDiv / forcesNb, yDiv / forcesNb, 0);

    }


    public void addForce(Force force) {
        forces.add(force);
    }

    public void removeForce(Force force) {
        forces.remove(force);
    }


    @Override
    public void process(GameObjectModel model, MovementApplyer mvt) {

        double v0_x = 0,
                v0_y = 0,
                t = mvt.getEllapsedTime(),
                g = 9.81,
                x0 = mvt.getStartPosition().getX(),
                y0 = mvt.getStartPosition().getY();


        double x = v0_x * t +  x0;
        double y =  (0.5) * g * Math.pow(t,2) + v0_y * t +  y0;

        // rapidité de mouvement
        y/=50;


        System.out.println("Time: " + t + " | SystemeX:"+x + "|"+y);

        model.getPosition().setPosition(x,y);

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
