package angrybirds.trajectories.physic;

import angrybirds.structures.Vector2d;

/**
 * Created by Pierre on 30/12/2015.
 */
public class Force extends Vector2d {


    public Force(double x, double y) {
        this(x, y, 0, 0);
    }

    public Force(double x, double y, double decrX, double decrY) {
        this.x = x;
        this.y = y;
    }

    public Force() {
        this.x=0;
        this.y=0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    public void setPosition(double x, double y){
        setX(x);
        setY(y);
    }


    public Force divide(Force f) {
        return new Force(getX() / f.getX(), getY() / f.getY());
    }

    public Force divide(double a) {
        if(a==0){
            return new Force(getX(),getY());
        }
        return new Force(getX() / a, getY() / a);
    }


    public Force substract(Vector2d b){
        return new Force(x-b.getX(), y-b.getY());
    }
    public Force add(Vector2d b){
        return new Force(x+b.getX(), y+b.getY());
    }
    public Force multiply(double b){
        return new Force(x*b, y*b);
    }



    @Override
    public String toString() {
        return x + ";" + y;
    }
}
