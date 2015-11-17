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
public class LinearMovement extends Movement {

    private String type;
    private int by;

    private int x, y;
    private int a, b;

    /**
     * Gets a linear movement
     *
     * @param type
     */
    public LinearMovement(String type) {
        String[] split = type.split(" ");
        a = Integer.parseInt(split[0]);
        b = Integer.parseInt(split[1]);
    }

    public LinearMovement(int a, int b, int xBy) {
        this.type = "";
        this.a = a;
        this.b = b;
        this.by = xBy;
    }


    /**
     * Process
     *
     * @param model
     * @param mvt
     */
    @Override
    public void process(GameObjectModel model, MovementApplyer mvt) {
        Vector2d position = model.getPosition();

        x = findX();
        y = findY(x);

        // apply movement
        position.setX(mvtApplyer.getStartPosition().getX() + x);
        position.setY(mvtApplyer.getStartPosition().getY() + y);
        
        
        
        int lenght = (int)  findNextX(2) ;
        int width = (int) findNextY(2) ;

        position.setLength(lenght);
        position.setWidth(width);
        
    }

    @Override
    public double processY(double x) {
        return 5 * x;
    }
    
    
    
    @Override
    public int findX() {
        return (int) (mvtApplyer.getEllapsedTime()) * by;
    }

    public int findY(int x) {
        y = (int) a * x + b;

        return y;
    }

    @Override
    public int findY() {
        return findY(findX());
    }
    
    
    public int findNextX(int time) {
    	return (int) ((mvtApplyer.getEllapsedTime() + time) * by);
    }
    
    
    public int findNextY(int time) {
    	return findY(findNextX(time));
    }


}
