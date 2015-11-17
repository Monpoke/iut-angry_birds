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
public enum Background implements Resource {

    /**
     *
     */
    DEFAULT("backgrounds/default.png");
    private final String file;

    private Background(String file) {
        this.file = file;
    }

    @Override
    public Image getImage() {
        return ImageLoader.loadImage(file);
    }

}
