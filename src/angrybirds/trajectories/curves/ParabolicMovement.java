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
        System.out.println("Parabolic a[" + a + "], b[" + b + "], c[" + c + "] xBy[" + xBy + "]");
    }

    /**
     * Process
     *
     * @param model
     * @param mvt
     */
    @Override
    public void process(GameObjectModel model, MovementApplyer mvt) {

        /**
         * @TODO: Find parabolic ax² + bx + c
         *
         * I have to resolve x=0 with DELTA (in order to find decalage)
         */
        // Calcul du delta
        double delta = Math.pow(b, 2) - 4 * a * c;

        // Calcul de x1
        double x1 = ((-b + Math.sqrt(delta)) / -a) * 10;

        // calcul du x decale
        double xDecale = x1 *2  + xBy * (Window.getRefreshTimes() - mvt.getStartMovementTime());
        
        double x = model.getPosition().getX() + xBy;

//        double fakeX = x + x1 - mvt.getStartPosition().getX();
        
        int y = (int) ((a * Math.pow((xDecale), 2)) + b * (xDecale) + c) / (int)div;
        Tools.debug("Y: " + y);
        Tools.debug("Delta: "+ delta + " -> x1: " + x1 +" y: "+y +" x " + x);

        y = Math.abs(y);
        
        model.getPosition().setX(x);
        model.getPosition().setY(mvt.getStartPosition().getY() - y);
        
    }

}
