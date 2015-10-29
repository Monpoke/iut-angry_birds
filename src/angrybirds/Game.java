/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds;

import angrybirds.models.BirdModel;
import angrybirds.models.ObstacleModel;
import angrybirds.structures.Vector2d;
import angrybirds.views.Bird;
import angrybirds.views.CircleObstacle;
import angrybirds.views.GameObjectView;
import angrybirds.views.Window;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Pierre
 */
public class Game extends BaseGame {

    /**
     * Contains the Random generator.
     */
    private static final Random rnd = new Random();

    /**
     * Contains all objects on scene, except the bird.
     */
    private List<GameObjectView> objects;

    /**
     * The bird object.
     */
    private Bird bird;
    private final Window window;
    private final int fps;
    
    public static boolean BLOCK_STATUS = false;
    

    public Game(int fps) {
        this.fps = fps;

        // Open Game window
        window = new Window(this);

        // Reset scene
        resetScene();

        window.refresh();
    }

    public void resetScene() {
        Tools.debug("RESET SCENE");

        // Create objects
        createObstacles();

        // create Bird
        createBird();

        window.refreshScene(this);
    }

    /**
     * Create obstacles
     */
    private void createObstacles() {
        objects = new ArrayList<>();

        for (int i = 0; i < 3; i++) {

            ObstacleModel obsModel = new ObstacleModel(new Vector2d(
                    500,
                    100 + i * ((10 + rnd.nextInt(80)) + Constants.OBSTACLE_DIAMETER)
            ));
            CircleObstacle obsView = new CircleObstacle(obsModel);
            obsModel.addView(obsView);
            // add the view to object to draw
            objects.add(obsView);
        }
    }

    private void createBird() {
        BirdModel birdModel = new BirdModel(new Vector2d(30, 30+rnd.nextInt(150)));
        bird = new Bird(birdModel);
        birdModel.addView(bird);
    }

    /**
     * Render loop.
     *
     * @param g
     */
    public void loop(Graphics g) {
        for (GameObjectView object : objects) {
            object.draw(g);
        }

        // Draws bird
        bird.draw(g);
    }

    public long getFPS() {
        return 1000 / this.fps;
    }

    public Bird getBird() {
        return bird;
    }
    
    

}
