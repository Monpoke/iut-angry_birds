package angrybirds.trajectories.physic;

/**
 * Created by Pierre on 30/12/2015.
 */
public class Force {
    private double x;
    private double y;
    private double decrX;
    private double decrY;


    public Force(double x, double y) {
        this(x, y, 0, 0);
    }

    public Force(double x, double y, double decrX, double decrY) {
        this.x = x;
        this.y = y;
        this.decrX = decrX;
        this.decrY = decrY;
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

    public void update() {
        x += decrX;
        y += decrY;
    }

    public void setPosition(double x, double y){
        setX(x);
        setY(y);
    }


    public Force divide(Force f) {
        return new Force(getX() / f.getX(), getY() / f.getY());
    }

    public Force divide(double a) {
        return new Force(getX() / a, getY() / a);
    }


    @Override
    public String toString() {
        return x + ";" + y;
    }
}
