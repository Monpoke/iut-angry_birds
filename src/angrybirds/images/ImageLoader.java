/*
 *  Projet Angry Birds
 */

package angrybirds.images;

import angrybirds.Tools;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author Pierre
 */
public class ImageLoader {

    static Image loadImage(String file) {
        Image image = null;
        try {
            image = Toolkit.getDefaultToolkit().getImage(ImageLoader.class.getResource("/resources/"+ file) );
        } catch (Exception e) {
            Tools.debug("file not found: " + file+"\nEx:"+e.getMessage());
        }
        return image;
    }
    
    
    
    
    
}
