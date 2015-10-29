/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds.models;

import angrybirds.Constants;
import angrybirds.structures.Vector2d;
import angrybirds.views.CircleObstacle;

/**
 *
 * @author Pierre
 */
public class ObstacleModel extends GameObjectModel {

    protected int diameter;

    public ObstacleModel(Vector2d position) {
        this.diameter = Constants.OBSTACLE_DIAMETER;
        this.position = position;
    }

    public ObstacleModel(Vector2d position, int diameter) {
        this.position = position;
        this.diameter = diameter;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public void addView(CircleObstacle obsView) {
        this.addObserver(obsView);
    }

}
