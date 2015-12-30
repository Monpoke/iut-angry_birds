/*
 *  Projet Angry Birds
 */
package angrybirds.images;

import java.awt.*;

/**
 *
 * @author Pierre
 */
public enum Ennemy implements Resource {

    /**
     * ROUND ENNEMIES
     */
    ROUND_BLUE("ennemies/round_blue.png"),
    ROUND_PINK("ennemies/round_pink.png"),
    ROUND_GREEN("ennemies/round_green.png"),
    ROUND_YELLOW("ennemies/round_yellow.png");


    private final String file;

    private Ennemy(String file) {
        this.file = file;
    }

    @Override
    public Image getImage() {
        return ImageLoader.loadImage(file);
    }

}
