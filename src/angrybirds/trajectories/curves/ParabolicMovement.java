/*
 *  Projet Angry Birds
 */
package angrybirds.trajectories.curves;

import angrybirds.Tools;
import angrybirds.models.GameObjectModel;
import angrybirds.structures.Vector2d;
import angrybirds.trajectories.Movement;
import angrybirds.trajectories.MovementApplyer;
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
    private final double xBy;
    private final double div;

    /**
     * Gets a linear movement
     *
     * @param params
     */
    public ParabolicMovement(String params) {
        this.params = params;

        String[] split = params.split(" ");
        a = Double.parseDouble(split[0]);
        b = Double.parseDouble(split[1]);
        c = Double.parseDouble(split[2]);
        xBy = Double.parseDouble(split[3]);
        div = Double.parseDouble(split[4]);
        Tools.debug("Parabolic a[" + a + "], b[" + b + "], c[" + c + "] xBy[" + xBy + "]");
    }

    public ParabolicMovement(double a, double b, double c, double xBy, double div) {
        this.params = "";
        this.a = a;
        this.b = b;
        this.c = c;
        this.xBy = xBy;
        this.div = div;
    }

    @Override
    public double processY(double x) {
       /* // calcul du x decale
        double xDecale = x;

        int y = (int) ((a * Math.pow((xDecale), 2)) + b * (xDecale) + c) / (int) div;

        return y;*/
      
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
        int length = (int) mvtApplyer.getStartPosition().getX() + findNextX(2) - xPos;
        int width = (int) mvtApplyer.getStartPosition().getY() - findNextY(2) - yPos;
//        Tools.debug(xPos+":"+yPos);
        
        
        
        model.getPosition().setX(xPos);
        model.getPosition().setY(yPos);
        
        model.getPosition().setLength(length);
        model.getPosition().setWidth(width);
    }

    @Override
    public int findX() {
        return (int) (mvtApplyer.getEllapsedTime() * xBy);
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
        
//        x-= xDecale();
        double y = ((a * Math.pow((x), 2)) + b * (x) + c) / 5;
        
        //Tools.debug("DebugY: "+y + " pour x=" + x + "->xDecale: "+ (x-xDecale()));
        
        
        return (int) y;
    }

    public double xDecale(){
        double delta =  Math.pow(b, 2)  - 4 * a * c;
        
        double x1 = (-b - Math.sqrt(delta)) / (2 * a);
       
        
        return x1;
    }
    
}
