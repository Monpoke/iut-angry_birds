/*
 *  Projet Angry Birds
 */
package angrybirds.controllers;

import angrybirds.AngryEvent;
import angrybirds.models.BirdModel;
import angrybirds.models.GameObjectModel;

/**
 *
 * @author Pierre
 */
public class BirdController extends GameObjectController {
    private AngryEvent deathEvent;

    public BirdController(GameObjectModel model) {
        super(model);
    }

    @Override
    public void update() {
        super.update();
        if(!((BirdModel)model).isAlive()){
            deathEvent.notif("dead");
        }
    }

    /**
     *
     * @param deathEvent
     */
    public void setDeathAction(AngryEvent deathEvent) {
        this.deathEvent = deathEvent;
    }
    
    
}
