/*
 *  Projet Angry Birds
 */
package angrybirds;

import angrybirds.controllers.Game;
import javax.swing.SwingUtilities;

/**
 *
 * @author Pierre
 */
public class AngryBirds {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Game();
            }
        });
        
    }
    
}
