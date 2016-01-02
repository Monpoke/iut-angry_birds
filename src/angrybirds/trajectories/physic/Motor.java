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
     * Physics motor
     */
    private List<Force> forces = new ArrayList<>();


    /**
     * Gravity is the default
     */
    public Motor(double mass) {
        addForce(new Gravity(mass));

    }

    public Motor(){

    }


    public Force getForceResult() {

        int forcesNb = forces.size();

        int xDiv = 0;
        int yDiv = 0;

        for (Force force : forces) {
            xDiv += (force.getX());
            yDiv += (force.getY());
        }

        return new Force(xDiv / Math.max(1, forcesNb), yDiv / Math.max(1, forcesNb));

    }


    public void addForce(Force force) {
        forces.add(force);
    }

    public void removeForce(Force force) {
        forces.remove(force);
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
        for (Force force : forces) {
            force.update();
        }

        // Raccourcis vers variables
        double v0_x = 0,
                v0_y = 0,
                t = mvt.getEllapsedTime(),
                g = 9.81,
                x0 = mvt.getStartPosition().getX(),
                y0 = mvt.getStartPosition().getY(),
                mass = model.getMass(),
        angle = Math.toRadians(90);
        //System.out.println("MoveInit: " + x0 + ";"+y0);


        Force forceResult = getForceResult();

        double sumForcesX = forceResult.getX(),
                sumForcesY = forceResult.getY();


        /**
         * On ignore les x0 et y0
         */
        // mouvement axe X
        double x = 0.5 * (sumForcesX / mass) * Math.pow(t, 2) + v0_x * t * Math.cos(angle);

        // mouvement Y
        double y = 0.5 * (sumForcesY / mass) * Math.pow(t, 2) + v0_y * t * Math.sin(angle);

        // rapidité de mouvement
        x /= 50;
        y /= 50;


        model.getPosition().setPosition(
                x0 + x, y0 + y
        );
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
