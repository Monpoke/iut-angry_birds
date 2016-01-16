
/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds.models;

import angrybirds.Constants;
import angrybirds.structures.Vector2d;
import angrybirds.views.GameObject;

/**
 *
 * @author Pierre
 */
public class ObstacleModel extends GameObjectModel {

    protected int diameter;
    protected int width;
    protected int height;

    protected String color = "pink";

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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void addView(GameObject obsView) {
        this.addObserver(obsView);
    }

}

