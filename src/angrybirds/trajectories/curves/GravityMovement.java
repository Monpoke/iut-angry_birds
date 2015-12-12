/*
 *  Projet Angry Birds
 */
package angrybirds.trajectories.curves;

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

    public GravityMovement(Vector2d force) {

        // Get speed
        v0 = Math.sqrt(Math.pow(force.getX(), 2) + Math.pow(force.getY(), 2));
        System.out.println("V0: " + v0);
        //   v0 /= 4;

        Vector2d zero = new Vector2d(0, 0);
        angle = Math.atan2(force.getY(), force.getX()) - Math.atan2(zero.getY(), zero.getX());
        System.out.println("angle: " + angle);

        v0 = 100;

        Point2D t = AffineTransform.getRotateInstance(angle).
                transform(new Point2D.Double(1, 0), null);
        velocity.setX(t.getX() * v0 * 0.5);
        velocity.setX(t.getY() * v0 * 0.5);

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
        /*
        int xPos = (int) mvtApplyer.getStartPosition().getX() + findX();
        int yPos = (int) mvtApplyer.getStartPosition().getY() - findY();
        int lenght = (int) mvtApplyer.getStartPosition().getX() + findNextX(2) - xPos;
        int width = (int) mvtApplyer.getStartPosition().getY() - findNextY(2) - yPos;
//        Tools.debug(xPos+":"+yPos);

        model.getPosition().setX(xPos);
        model.getPosition().setY(yPos);

        model.getPosition().setLength(lenght);
        model.getPosition().setWidth(width);*/

        double dt = 3 * mvtApplyer.getEllapsedTime() / 1e4;

        Vector2d position = model.getPosition();
        
        scaleAddAssign(velocity, dt, ACCELERATION, 1);
        scaleAddAssign(position, dt, velocity, -1);
        
        
        System.out.print("Time: " + dt);
        System.out.println(" -  position: " + position.toString());
        
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
