/*
 *  Projet Angry Birds
 */
package angrybirds.tools;

/**
 *
 * @author Pierre
 */
public abstract class Trajectory {
 
    public static int parabolic(int x, int h) {
        
        double a = -0.09f;
        double b = x/2f;
        double c = x;

        double y = a * Math.pow(x, 2) + b * x + c;

        y = h -y;
        
        return (int) y / 120;
    }

}
