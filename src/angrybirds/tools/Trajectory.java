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
        
        x-=350;
        
        double a = -0.095f;
        double b = x/1f;
        double c = 0;

        double y = a * Math.pow(x, 2) + b * x + c;
        y=h-y;
        
        return (int) y/400;
    }

}
