package angrybirds.debugbox;

import angrybirds.Tools;
import angrybirds.structures.Vector2d;
import angrybirds.views.Bird;

/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */

/**
 *
 * @author Pierre
 */
public class DebugBoxController {
    private final DebugBoxModel model;

    private Bird bird;
    
    public DebugBoxController(DebugBoxModel model) {
        this.model = model;
    }

    void updatePosition(Vector2d vector2d) {
        // have to update bird position
        bird.getModel().getPosition().copy(vector2d);
        Tools.debug("OK");
    }

    public void setBird(Bird bird) {
        this.bird = bird;
    }
    
        
}
