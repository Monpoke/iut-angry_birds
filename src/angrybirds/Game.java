/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds;

import angrybirds.controllers.BirdController;
import angrybirds.controllers.GameObjectController;
import angrybirds.controllers.ObstacleController;
import angrybirds.models.BirdModel;
import angrybirds.models.ObstacleModel;
import angrybirds.structures.Vector2d;
import angrybirds.trajectories.Movement;
import angrybirds.trajectories.MovementApplyer;
import angrybirds.trajectories.curves.LinearMovement;
import angrybirds.trajectories.curves.ObsLinearMovementVertical;
import angrybirds.trajectories.curves.ParabolicMovement;
import angrybirds.views.Bird;
import angrybirds.views.CircleObstacle;
import angrybirds.views.GameObjectView;
import angrybirds.views.RectangleObstacle;
import angrybirds.views.Window;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Pierre
 */
public class Game extends BaseGame {

    /**
     * Parameters
     */
    double[][] parameters = new double[][]{
        {-0.0035, 1.5, -3, 4},
        {-0.0035, 2.5, -2, 4},
        {-0.0035, 2.5, -2, 4},
        {-0.0035, 3, -1, 4},
        {-0.004, 3, -1, 4},
        {-0.005, 3, -1, 4},
        {-0.005, 2, -1, 4},
        {-0.005, 1, -1, 4}};

    ArrayList<Movement> movements;

    int playId = 0;

    /**
     * Contains the Random generator.
     */
    private static final Random rnd = new Random();

    /**
     * Contains all objects on scene. They will be printed.
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

        // create scene
        createParameters();

        // Reset scene
        resetScene();

        window.refresh();

    }

    /**
     * Reset a scene.
     */
    public void resetScene() {
        Tools.debug("RESET SCENE");
        objects = new ArrayList<>();

        // create World hitbox
        createHitbox();

        // Create objects
        createObstacles();

        // create Bird
        createBird();

        window.refreshScene(this);

        if (!Constants.DEBUG_MODE) {
            launchAutomatic();
        }
    }

    /**
     * Create obstacles
     */
    private void createObstacles() {

        int nbObs = Constants.MIN_OBSTACLES + rnd.nextInt(Constants.MAX_OBSTACLES - Constants.MIN_OBSTACLES + 1);

        for (int i = 0; Constants.ENABLE_OBSTACLES && i < nbObs; i++) {

            ObstacleModel obsModel = new ObstacleModel(new Vector2d(
                    Constants.WINDOW_WIDTH - 200 - rnd.nextInt(400),
                    100 + ((10 + rnd.nextInt(250)))
            ), 10 + rnd.nextInt(10));
           
            ObstacleController obsController = new ObstacleController(obsModel);
            CircleObstacle obsView = new CircleObstacle(obsModel, obsController);
            obsModel.addView(obsView);
           
            	LinearMovement mouvementVertical = new ObsLinearMovementVertical();
            	MovementApplyer mvt = new MovementApplyer(mouvementVertical,obsModel);
            	obsController.addMovement(mvt);
            	
            	
            
            // add the view to object to draw
            objects.add(obsView);
        }
    }

    /**
     * This function creates a Bird.
     */
    private void createBird() {
        BirdModel birdModel = new BirdModel(new Vector2d(80, Constants.WINDOW_HEIGHT - 100));
        BirdController birdController = new BirdController(birdModel);
        bird = new Bird(birdModel, birdController);
        birdModel.addView(bird);

        birdModel.setController(birdController);

        // ON DEATH, RESET SCENE.
        birdController.setDeathAction(new AngryEvent() {

            @Override
            public void notif(Object data) {
                try {
                    Thread.sleep(Constants.PLAY_RESET_AFTER);
                    resetScene();
                } catch (InterruptedException ex) {
                }
            }
        });

        objects.add(bird);
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

    }

    public long getFPS() {
        return 1000 / this.fps;
    }

    public Bird getBird() {
        return bird;
    }

    /**
     * This function is called before repaint. It allows the processing of data.
     */
    public void updateElements() {
        // update bird
    	
        bird.getController().update();

        for (GameObjectView object : objects) {
            if (object.getModel().hasCollision()) {
                object.getModel().getHitbox().setCollided(false);
            }
        }

        // update objects
        for (GameObjectView currentObject : objects) {
            GameObjectController controller = currentObject.getController();
            if (controller != null) {
                controller.update();
            }

            // Foreach for collisions
            if (currentObject.getModel().hasCollision()) {
                for (GameObjectView objectCollided : objects) {
                    if (objectCollided == currentObject) {
                        break; // because all objects are organised by order
                    } else if (objectCollided.getModel().hasCollision()) {

                        // if there are collisions 
                        if (objectCollided.getModel().getHitbox().intersect(currentObject.getModel().getHitbox())) {
                            objectCollided.getModel().getHitbox().setCollided(true);
                            currentObject.getModel().getHitbox().setCollided(true);

                            // block on bird
                            if (objectCollided instanceof Bird) {
                                ((BirdModel) objectCollided.getModel()).setIsAlive(false);
                            } else if (currentObject instanceof Bird) {
                                ((BirdModel) currentObject.getModel()).setIsAlive(false);

                            }
                        }
                    }
                }
            }
        }

    }

    /**
     * World hitbox
     */
    private void createHitbox() {
        createHit(1, 0, "v");
        createHit(Constants.WINDOW_WIDTH - 10, 0, "v");
        createHit(0, 0, "h");
        createHit(0, Constants.WINDOW_HEIGHT - 30, "h");

    }

    /**
     * Create boundaries.
     *
     * @param x
     * @param y
     * @param v
     */
    private void createHit(int x, int y, String v) {

        ObstacleModel obsModel = new ObstacleModel(new Vector2d(x, y));

        obsModel.setHeight((v.equals("v") ? Constants.WINDOW_HEIGHT : 1));
        obsModel.setWidth((!v.equals("v") ? Constants.WINDOW_WIDTH : 1));

        ObstacleController obsController = new ObstacleController(obsModel);
        RectangleObstacle obsView = new RectangleObstacle(obsModel, obsController);

        // hide the obstacle
        obsView.setHidden(true);

        obsModel.addView(obsView);
        // add the view to object to draw
        objects.add(obsView);
    }

    /**
     * This function plays some flies.
     */
    private void launchAutomatic() {

        /**
         * end play
         */
        if (playId >= movements.size()) {
            System.exit(0);
        }

        final Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                /* System.out.println("fefkjzekfjez");
                 double[] s = parameters[playId++];
                 double a, b, c;
                 double xBy;
                 double div = 2;

                 a = s[0];
                 b = s[1];
                 c = s[2];
                 xBy = s[3];*/
                Movement mvt = movements.get(playId++);
                MovementApplyer mvtA = new MovementApplyer(mvt, bird.getModel());
                mvt.setMvtApplyer(mvtA);
                bird.getController().addMovement(mvtA);
//                   bird.getController().addMovement(new MovementApplyer(new ParabolicMovement(a, b, c, xBy, div), bird.getModel()));
//        bird.getController().addMovement(new MovementApplyer(new ParabolicMovement("-0.005 3 -1 8 2"), bird.getModel()));

                t.cancel();
            }
        }, 0, 1);
    }

    private void createParameters() {
        movements = new ArrayList<>();

        movements.add(new LinearMovement(-1, 0, 5));

        for (int i = 0; i < parameters.length; i++) {
            double[] s = parameters[i];
            double a, b, c;
            double xBy;
            double div = 2;

            a = s[0];
            b = s[1];
            c = s[2];
            xBy = s[3];
            movements.add(new ParabolicMovement(a, b, c, xBy, div));
        }

    }

}

