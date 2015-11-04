/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Pierre
 */
public class Tools {
    
    public static void debug(String msg){
        if(Constants.DEBUG_MODE){
            Calendar c = Calendar.getInstance();
            System.out.println("[InferNoDebug "+c.get(Calendar.HOUR_OF_DAY) + ":"+ c.get(Calendar.MINUTE) + ":"+c.get(Calendar.SECOND) + ":"+c.get(Calendar.MILLISECOND) + " @run" +"]\t"+msg);
        }
    }
    
}
