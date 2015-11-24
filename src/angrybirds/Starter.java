/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds;

import javax.swing.SwingUtilities;

/**
 *
 * @author Pierre
 */
public class Starter {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Game(Constants.REFRESH_FPS);
            }
        });
    }

}
