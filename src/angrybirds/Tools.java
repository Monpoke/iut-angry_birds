/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds;

import java.util.Calendar;

/**
 *
 * @author Pierre
 */
public class Tools {
    
    /**
     * Debug trace.
     * @param msg 
     */
    public static void debug(String msg){
        if(Constants.DEBUG_MODE){
            Calendar c = Calendar.getInstance();
            System.out.println("[InferNoDebug "+c.get(Calendar.HOUR_OF_DAY) + ":"+ c.get(Calendar.MINUTE) + ":"+c.get(Calendar.SECOND) + ":"+c.get(Calendar.MILLISECOND) + " @run" +"]\t"+msg);
        }
    }
    
    
    /**
     * Returns the distance between two coordinates.
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     * @return 
     */
     public static double distancePoints(double x1, double x2, double y1, double y2) {
        return (((Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2))));
    }
}
