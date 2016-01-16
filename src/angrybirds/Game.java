/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds;

import angrybirds.builders.ObstacleFactory;
import angrybirds.controllers.BirdController;
import angrybirds.controllers.GameObjectController;
import angrybirds.controllers.ObstacleController;
import angrybirds.events.AngryEvent;
import angrybirds.hitbox.RectangleHitbox;
import angrybirds.models.BirdModel;
import angrybirds.models.ObstacleModel;
import angrybirds.motors.PhysicEngine;
import angrybirds.structures.Vector2d;
import angrybirds.trajectories.Movement;
import angrybirds.trajectories.MovementApplyer;
import angrybirds.trajectories.curves.LinearMovement;
import angrybirds.trajectories.curves.ParabolicMovement;
import angrybirds.trajectories.physic.Gravity;
import angrybirds.views.*;
import angrybirds.views.Window;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
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
    private List<GameObject> objects;

    /**
     * The bird object.
     */
    private Bird bird;
    private final Window window;
    private final int fps;

    /**
     * Check the availabity of game
     */
    public static boolean BLOCK_STATUS = true;

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

    }


    /**
     * Create one object
     */
    private void debug_object() {
        int x = 500,
                y = 50;


        /**
         * USE SOME FACTORY.
         */
        ShapeObstacle so;
        so = ObstacleFactory.createObstacle("SQUARE", x, y);
        System.out.println("Initialie avec " + x + ";" + y);


        System.out.println("ApresMotor " + so.getModel().getPosition().toString());

        so.getModel().setLabelName("topSquare");
        // add the view to object to draw
        objects.add(so);


        // Add to physic motor
        so.getModel().addConstantForce(new Gravity(so.getModel().getMass()));
        PhysicEngine.registerGameobject(so.getModel());


        // create immobile circle
        //so = ObstacleFactory.createObstacle("CIRCLE", x, y + 400);
        // so.getModel().setCanMove(false);
        //  objects.add(so);


    }


    /**
     * Create obstacles
     */
    private void createObstacles() {
        debug_object();
        /*
        int nbObs = Constants.MIN_OBSTACLES + rnd.nextInt(Constants.MAX_OBSTACLES - Constants.MIN_OBSTACLES + 1);

        for (int i = 0; Constants.ENABLE_OBSTACLES && i < nbObs; i++) {

            int x = Constants.WINDOW_WIDTH - 150 - rnd.nextInt(400),
                    y = 100 + ((10 + rnd.nextInt(350)));


            /**
             * USE SOME FACTORY.
             *
            ShapeObstacle so;
            if (rnd.nextInt(10) < 5) {
                so = ObstacleFactory.createObstacle("CIRCLE", x, y);
            } else {
                so = ObstacleFactory.createObstacle("SQUARE", x, y);
            }


            Movement mType;

            //mouvement physique
            mType = new Motor();


            MovementApplyer mvt = new MovementApplyer(mType, so.getModel());
            so.getController().addMovement(mvt);

            // add the view to object to draw
            objects.add(so);
        }*/
    }

    /**
     * This function creates a Bird.
     */
    private void createBird() {
        BirdModel birdModel = new BirdModel(new Vector2d(Constants.BIRD_START_X, Constants.WINDOW_HEIGHT - Constants.BIRD_START_Y_FROM_BOTTOM));
        BirdController birdController = new BirdController(birdModel);
        bird = new Bird(birdModel, birdController);
        birdModel.addView(bird);
        birdModel.setLabelName("bird");

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
        for (GameObject object : objects) {
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
        // Update World
        PhysicEngine.applyForces();


        // update bird
//        bird.getController().update();

        // update objects
        Iterator<GameObject> cur = objects.iterator();
        while(cur.hasNext()) {
            GameObject currentObject = cur.next();
            GameObjectController controller = currentObject.getController();
            if (controller != null) {
                //              controller.update();
            }

            ((BirdModel) bird.getModel()).setIsAlive(true);

            // Foreach for collisions
            if (currentObject.getModel().hasCollision()) {


                Iterator<GameObject> it = objects.iterator();
                while(it.hasNext()){
                    GameObject objectCollided = it.next();
                    if (currentObject.getModel().getLabelName().equals("topSquare")) {
                       // System.out.println("Comparing with:" + objectCollided.getModel().getLabelName());


                    }


                    if (objectCollided == currentObject) {
                        break; // because all objects are organised by order
                    } else if (objectCollided.getModel().hasCollision()) {

                        PhysicEngine.processCollision(currentObject.getModel(),objectCollided.getModel());

                        /*
                        currentObject.getModel().getHitbox().setCollided(false);

                        // if there are collisions 
                        if (objectCollided.getModel().getHitbox().intersect(currentObject.getModel().getHitbox())) {
                            objectCollided.getModel().getHitbox().setCollided(true);
                            currentObject.getModel().getHitbox().setCollided(true);

                            // Process collision
                            PhysicEngine.processCollision(currentObject.getModel(), objectCollided.getModel());


                            // block on bird
                            if (objectCollided instanceof Bird) {
                                ((BirdModel) objectCollided.getModel()).setIsAlive(false);
                            } else if (currentObject instanceof Bird) {
                                ((BirdModel) currentObject.getModel()).setIsAlive(false);
                            }
                        }*/
                    }
                }
            }
        }

    }

    /**
     * World hitbox
     */
    private void createHitbox() {
        createHit(1, 0, "v", "hitTop");
        createHit(Constants.WINDOW_WIDTH - 10, 0, "v", "hitRight");

        createHit(0, 0, "h", "hitLeft");

        // Ground
        createGround();

    }

    /**
     * Create ground on scene
     */
    private void createGround() {
        ShapeObstacle rectangle = ObstacleFactory.createObstacle("square", 0, Constants.WINDOW_HEIGHT - 50);
        ((ObstacleModel) rectangle.getModel()).setWidth(Constants.WINDOW_WIDTH);
        ((ObstacleModel) rectangle.getModel()).setHeight(30);
        // Disable hide rectangle
        rectangle.setHidden(false);
        rectangle.getModel().setLabelName("floor");
        objects.add(rectangle);
        // add some hitbox to ground
        rectangle.getModel().setHitbox(new RectangleHitbox(rectangle.getModel(), Constants.WINDOW_WIDTH, 30));
    }

    /**
     * Create boundaries.
     *
     * @param x
     * @param y
     * @param v
     */
    private void createHit(int x, int y, String v, String label) {

        ObstacleModel obsModel = new ObstacleModel(new Vector2d(x, y));
        obsModel.setLabelName(label);
        obsModel.setHeight((v.equals("v") ? Constants.WINDOW_HEIGHT : 15));
        obsModel.setWidth((!v.equals("v") ? Constants.WINDOW_WIDTH : 15));

        ObstacleController obsController = new ObstacleController(obsModel);
        RectangleObstacle obsView = new RectangleObstacle(obsModel, obsController);

        // hide the obstacle
        obsView.setHidden(true);

        obsModel.addView(obsView);
        // add the view to object to draw
        objects.add(obsView);
    }

    private void createHitTest() {

        ObstacleModel obsModel = new ObstacleModel(new Vector2d(150, 150));

        obsModel.setHeight(100);
        obsModel.setWidth(550);

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
