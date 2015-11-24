/*
 *  Projet Angry Birds
 */
package angrybirds.trajectories.curves;

import angrybirds.events.AngryEvent;
import angrybirds.Constants;
import angrybirds.Tools;
import angrybirds.controllers.BirdController;
import angrybirds.controllers.ObstacleController;
import angrybirds.models.BirdModel;
import angrybirds.models.ObstacleModel;
import angrybirds.structures.Vector2d;
import angrybirds.trajectories.MovementApplyer;
import angrybirds.views.Bird;
import angrybirds.views.CircleObstacle;
import angrybirds.views.Window;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class ParabolicCollisionTest {

    private ParabolicMovement mvt1;
    private ParabolicMovement mvt2;

    public ParabolicCollisionTest() {
    }

    @Before
    public void setUp() {

        mvt1 = new ParabolicMovement(2, 3, -1, 3, 100);
        mvt2 = new ParabolicMovement(2, 7, -1, 3, 100);

    }

    // it should be equals to 0
    @Test
    public void testCircleBird() {

        BirdModel birdModel = new BirdModel(new Vector2d(0, 0));
        BirdController birdController = new BirdController(birdModel);
        Bird bird = new Bird(birdModel, birdController);
        birdModel.addView(bird);
        birdModel.setController(birdController);

        // create movement
        LinearMovement lm = new LinearMovement(0, 0, 5);
        MovementApplyer mvt = new MovementApplyer(lm, birdModel);
        lm.setMvtApplyer(mvt);
        birdController.addMovement(mvt);

        // create 
        ObstacleModel obsModel = new ObstacleModel(new Vector2d(100, 0), 30);
        ObstacleController obsController = new ObstacleController(obsModel);
        CircleObstacle obsView = new CircleObstacle(obsModel, obsController);
        obsModel.addView(obsView);

        boolean touch = false;
        for (int i = 0; i < 100; i++) {
            birdController.update();
            Window.addTime();
            
            // check hitbox
            if(bird.getModel().getHitbox().intersect(obsModel.getHitbox())){
                touch=true;
                break;
            }
            
        }
        
        
        assertTrue(touch);
    }
    // it should be equals to 0
    @Test
    public void testNoCollisionBird() {

        BirdModel birdModel = new BirdModel(new Vector2d(60, 0));
        BirdController birdController = new BirdController(birdModel);
        Bird bird = new Bird(birdModel, birdController);
        birdModel.addView(bird);
        birdModel.setController(birdController);

        // create movement
        LinearMovement lm = new LinearMovement(0, 0, 5);
        MovementApplyer mvt = new MovementApplyer(lm, birdModel);
        lm.setMvtApplyer(mvt);
        birdController.addMovement(mvt);

        // create 
        ObstacleModel obsModel = new ObstacleModel(new Vector2d(0, 0), 30);
        ObstacleController obsController = new ObstacleController(obsModel);
        CircleObstacle obsView = new CircleObstacle(obsModel, obsController);
        obsModel.addView(obsView);

        boolean touch = false;
        for (int i = 0; i < 100; i++) {
            birdController.update();
            Window.addTime();
            
            // check hitbox
            if(bird.getModel().getHitbox().intersect(obsModel.getHitbox())){
                touch=true;
                break;
            }
            
        }
        
        
        assertFalse(touch);
    }
}
