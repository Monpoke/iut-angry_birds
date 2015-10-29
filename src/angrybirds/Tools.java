/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds;

/**
 *
 * @author Pierre
 */
public class Tools {
    
    public static void debug(String msg){
        if(Constants.DEBUG_MODE){
            System.out.println(msg);
        }
    }
    
}
