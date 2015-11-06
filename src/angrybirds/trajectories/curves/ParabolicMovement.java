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
        // calcul du x decale
        double xDecale = x;
        
        int y = (int) ((a * Math.pow((xDecale), 2)) + b * (xDecale) + c) / (int) div;

        return y;
    }

    /**
     * Process
     *
     * @param model
     * @param mvt
     */
    @Override
    public void process(GameObjectModel model, MovementApplyer mvt) {

        double x = model.getPosition().getX() + xBy;
        int y = (int) processY(x);

        model.getPosition().setX(x);
        model.getPosition().setY(mvt.getStartPosition().getY() - y);

    }

}
