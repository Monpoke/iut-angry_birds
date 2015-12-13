/*
 *  Projet Angry Birds
 */
package angrybirds.trajectories.curves;

import angrybirds.Tools;
import angrybirds.models.GameObjectModel;
import angrybirds.structures.Vector2d;
import angrybirds.trajectories.Movement;
import angrybirds.trajectories.MovementApplyer;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

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
        
        angle=90;
        v0 = Math.sqrt(Math.pow(force.getX(), 2) + Math.pow(force.getY(), 2));
        
        v0 = Math.max(1, v0);
        
        System.out.println("Vitesse: " + v0 + "\nAngle:" + angle);

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

        x = v0 * mvtApplyer.getEllapsedTime() * Math.cos(angle) + mvtApplyer.getStartPosition().getY();

        System.out.println(v0 + "*" + mvtApplyer.getEllapsedTime() + "*" + Math.cos(angle) + "+" + mvtApplyer.getStartPosition().getY() + " / " + rapportX);

        x /= rapportX;

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
        return findY(findNextX(time));
    }

    public int findY(int x) {

        double y0 = mvtApplyer.getStartPosition().getY();

        y = -(1 / 2) * g * Math.pow(mvtApplyer.getEllapsedTime() / rapportTime, 2)
                + v0 * mvtApplyer.getEllapsedTime() / rapportTime * Math.sin(angle) + y0;

        System.out.print(-(0.5) + "*" + g + "*" + Math.pow(mvtApplyer.getEllapsedTime() / rapportTime, 2) + " + "
                + v0 + "*" + (mvtApplyer.getEllapsedTime() / rapportTime) + "*" + "sin(" + angle + ") + " + y0);
        y /= rapportY;

        System.out.println("\n\n");

        return (int) y;
    }

    private static void scaleAddAssign(
            Vector2d result, double factor, Vector2d addend, int fctEnd) {
        double x = result.getX() + factor * addend.getX();
        double y = result.getY() + factor * addend.getY() * fctEnd;
        result.setX(x);
        result.setY(y);
    }

}
