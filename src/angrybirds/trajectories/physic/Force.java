package angrybirds.trajectories.physic;

/**
 * Created by Pierre on 30/12/2015.
 */
public class Force {
    public int x;
    public int y;
    public double intensite;

    public Force(int x, int y, double intensite) {
        this.x = x;
        this.y = y;
        this.intensite = intensite;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getIntensite() {
        return intensite;
    }

    public void setIntensite(double intensite) {
        this.intensite = intensite;
    }

    @Override
    public String toString() {
        return x +";"+y;
    }
}
