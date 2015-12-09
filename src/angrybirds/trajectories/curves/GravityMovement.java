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

    private final double rapportX = 10, rapportY = 50;
    
    private double x,y;
    private int xBy;
    
    private final double g = 9;
    
    private double v0, angle;


    public GravityMovement(Vector2d force) {
        
        // Get speed
        v0 = Math.sqrt(Math.pow(force.getX(), 2) + Math.pow(force.getY(), 2));
        
        v0 /= 4;
        
        Vector2d zero = new Vector2d(0, 0);
        angle = Math.atan2(force.getY(), force.getX()) - Math.atan2(zero.getY(), zero.getX());
        
    }

    @Override
    public double processY(double x) {
        return findY((int)x);
    }

    /**
     * Process
     *
     * @param model
     * @param mvt
     */
    @Override
    public void process(GameObjectModel model, MovementApplyer mvt) {

        int xPos = (int) mvtApplyer.getStartPosition().getX() + findX();
        int yPos = (int) mvtApplyer.getStartPosition().getY() - findY();
        int lenght = (int) mvtApplyer.getStartPosition().getX() + findNextX(2) - xPos;
        int width = (int) mvtApplyer.getStartPosition().getY() - findNextY(2) - yPos;
//        Tools.debug(xPos+":"+yPos);
        
        
        
        model.getPosition().setX(xPos);
        model.getPosition().setY(yPos);
        
        model.getPosition().setLength(lenght);
        model.getPosition().setWidth(width);
    }

    @Override
    public int findX() {
        
        x = v0 * mvtApplyer.getEllapsedTime() * Math.cos(angle) + mvtApplyer.getStartPosition().getY();
        
        System.out.print("ANGLE: "+ angle + " | V0: " + v0 + " - x: " + x);
        
        x/=rapportX;
        
        return (int)x;
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
        
        y = -(1/2) * g * Math.pow(mvtApplyer.getEllapsedTime(), 2) +
                v0 * mvtApplyer.getEllapsedTime() * Math.sin(angle) + y0;
        
        
        System.out.print("y: " + y + " - ");
        y /= rapportY;
        
        System.out.println("yn: " + y);
        
        return (int) y;
    }
    
    
}
