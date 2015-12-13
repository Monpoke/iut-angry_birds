/*
 *  Projet Angry Birds
 */
package angrybirds.trajectories.curves;

import angrybirds.models.GameObjectModel;
import angrybirds.structures.Vector2d;
import angrybirds.trajectories.Movement;
import angrybirds.trajectories.MovementApplyer;

/**
 *
 * @author Pierre
 */
public class GravityMovement extends Movement {

    private final double rapportX = 20, rapportY = 0.45;
    private double rapportTime = 0.50;

    private double x, y;
    private int xBy;

    private final double g = 9;

    private double v0, angle;

    private Vector2d velocity = new Vector2d(0, 0);
    private final Vector2d ACCELERATION = new Vector2d(0, -9.81 * 0.1);

    public GravityMovement(Vector2d force, double angle) {
        this.angle = angle;
        this.angle=angle;
        
        v0 = Math.sqrt(Math.pow(force.getX(), 2) + Math.pow(force.getY(), 2));
        
        v0 = Math.max(1, v0);
        

    }

    @Override
    public double processY(double x) {
        return findY((int) x);
    }

    /**
     * Process
     *
     * @param model
     * @param mvt
     */
    @Override
    public void process(GameObjectModel model, MovementApplyer mvt) {

        double an = Math.toRadians(angle);
        double dt = mvt.getEllapsedTime();

        double xCarre,
                v0Carre = Math.pow(v0, 2),
                angleCosCarre = Math.pow(Math.cos(an), 2);

        x = v0 * Math.cos(an) * dt;
        x /= 4;
        xCarre = Math.pow(x, 2);
        y = ((-g * xCarre) / (2 * v0Carre * angleCosCarre)) + x * Math.tan(an);

        model.getPosition().setPosition(
                mvt.getStartPosition().getX() + x,
                mvt.getStartPosition().getY() - y
        );
    }

    @Override
    public int findX() {

        return (int) x;
    }

    public int findNextX(int time) {
        return (int) ((mvtApplyer.getEllapsedTime() + time) * xBy);
    }

    @Override
    public int findY() {
        return findY(findX());
    }

    public int findNextY(int time) {
        System.out.println("Appel");
        return findY(findNextX(time));
    }

    public int findY(int x) {

        
        
        
        return (int) y;
    }

}
