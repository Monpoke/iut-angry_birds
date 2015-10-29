/*
 *  Projet Angry Birds
 */
package angrybirds.models;

/**
 *
 * @author Pierre
 */
abstract class GameObject {

    protected double positionX = 100.0;
    protected double positionY = 175.0;

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }


    
    
    
    
}
