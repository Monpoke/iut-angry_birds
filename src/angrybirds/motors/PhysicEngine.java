package angrybirds.motors;


import angrybirds.Constants;
import angrybirds.Game;
import angrybirds.Tools;
import angrybirds.controllers.BirdController;
import angrybirds.models.BirdModel;
import angrybirds.models.GameObjectModel;
import angrybirds.models.ObstacleModel;
import angrybirds.structures.Vector2d;
import angrybirds.trajectories.physic.Force;
import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Pierre on 04/01/2016.
 */
public class PhysicEngine implements Motor {

    private double timeElapsed = 0f;
    private double currentTime = 0f;

    public final static List<GameObjectModel> gameobjects = new ArrayList<>();
    private static double deltaTime;


    /**
     * Process a step
     */
    public static void step() {

        // clear collisions
        Iterator<GameObjectModel> cle = gameobjects.iterator();
        while (cle.hasNext()) {
            cle.next().getHitbox().clearCollided();
        }

        // check collisions
        checkCollisions();

        Iterator<GameObjectModel> obj = gameobjects.iterator();
        while (obj.hasNext()) {
            GameObjectModel gom = obj.next();

            if (!gom.canMove()) {
                continue;
            }

            if (gom.getType().equals(GameObjectModel.TYPES.BIRD)) {
                checkBirdCollision(((BirdModel) gom));
            }


            // sums
            Force totalConstantsForces = gom.getTotalConstantsForces();

            // some dynamic laws...

            // acceleration
            double accel_x = totalConstantsForces.getX();
            double accel_y = totalConstantsForces.getY();

            double velocity_x = gom.getVelocity().getX(),
                    velocity_y = gom.getVelocity().getY();

            // position
            double x = gom.getPosition().getX() + velocity_x * deltaTime;
            double y = gom.getPosition().getY() + velocity_y * deltaTime;

            // velocity
            velocity_x = accel_x * deltaTime + gom.getVelocity().getX();
            velocity_y = accel_y * deltaTime + gom.getVelocity().getY();

            // on ajoute la friction
            if (gom.labelName.equals("bird")) {

                // > 0
                velocity_y += 3;


            }


            // set
            gom.getAcceleration().setPosition(accel_x, accel_y);
            gom.getVelocity().setPosition(velocity_x, velocity_y);
            gom.getPosition().setPosition(x, y);


            if (gom.getPosition().getY() > Constants.WINDOW_HEIGHT) {
                Game.BLOCK_STATUS = !Game.BLOCK_STATUS;
            }


            // IF COLLISION
            if (gom.getHitbox().isCollided()) {
                Iterator<GameObjectModel> iterator = gom.getHitbox().getObjectsCollided().iterator();

                while (iterator.hasNext()) {
                    GameObjectModel objectCollided = iterator.next();
                    processCollision2(gom, objectCollided);
                }


            }

        }


        deltaTime += 0.002;
    }

    /**
     * This function checks if a bird is in collision. However, it should'nt be here.
     *
     * @param bird
     */
    private static void checkBirdCollision(BirdModel bird) {

        double x = bird.getPosition().getX(),
                y = bird.getPosition().getY();
        boolean dead = (x < 0 || x > Constants.WINDOW_WIDTH) || (y < 0 || y > Constants.WINDOW_HEIGHT);

        if (dead == true) {
            JOptionPane.showMessageDialog(null, "You're died!");
            System.exit(0);
        }

        if (!bird.getHitbox().isCollided() && !dead) {
            return;
        }

        Iterator<GameObjectModel> iterator = bird.getHitbox().getObjectsCollided().iterator();
        while (iterator.hasNext()) {
            GameObjectModel n = iterator.next();
            if (n.getHitbox().isKiller()) {
                ((BirdController) bird.getController()).getDeathAction().notif("dead");
            }
        }


    }

    private static void processCollision2(GameObjectModel A, GameObjectModel B) {

        // system mass
        double total = A.getMass() + B.getMass();

        //   System.out.println("TotalSysteme: " + total);

        // Direction collision
        Force direction = new Force(
                (A.getMass() * A.getVelocity().getX() / total),
                (A.getMass() * A.getVelocity().getY()) / total
        );


        //   System.out.println("Collision between " + A.getLabelName() + " et " + B.getLabelName());

        double vx = -direction.getX(),
                vy = -(direction.getY());

        //System.out.println("ApplyForce: " + vx + ":" + vy);


        if (A.canMove()) {
            //    System.out.println("---------------");
            A.getVelocity().setPosition(0, 0);
            //    System.out.println("Force: " + A.getVelocity());
        }

    }

    /**
     * Apply forces to every objects
     */
    public static void applyForces() {
        Force Acceleration;
        Force Velocity;
        Force forcesSums;

        Iterator<GameObjectModel> allObjects = gameobjects.iterator();

        GameObjectModel currentObject;
        while (allObjects.hasNext()) {
            currentObject = allObjects.next();

            if (currentObject.canMove()) {
                forcesSums = new Force(0f, 0f);

                /**
                 * IS COLLIDING?
                 */
                if (currentObject.hasCollision() && currentObject.getHitbox().isCollided()) {
                    // Gérer collisions

                    /**
                     * @1 accélération =0
                     **/
                    currentObject.setAcceleration(new Force(0, 0));

                    /**
                     * @2: Vélocité de collision selon la masse des obhects
                     * @TODO: Modifier
                     */
                    // currentObject.setVelocity(new Force(0, 0));


                    /**
                     * Looking for collisions
                     */
                    Iterator<GameObjectModel> iterator = currentObject.getHitbox().getObjectsCollided().iterator();
                    while (iterator.hasNext()) {
                        GameObjectModel c = iterator.next();
                        if (c == currentObject) {
                            break;
                        }
                        System.out.println("Hello, i'm " + currentObject.getLabelName() + " and I collide with " + c.getLabelName());
                        resolveCollision(currentObject, c);
                    }


                    currentObject.getHitbox().clearCollided();

                }


                // Forces appliquées sur l'objet comme le poids
                Iterator<Force> forces = currentObject.getForces().iterator();
                Force force;
                while (forces.hasNext()) {
                    force = forces.next();
                    // addition des forces
                    forcesSums.setPosition(forcesSums.getX() + force.getX(), forcesSums.getY() + force.getY());
                }

                Force constants = currentObject.getTotalConstantsForces();
                forcesSums.setPosition(forcesSums.getX() + constants.getX(), forcesSums.getY() + constants.getY());

                // acceleration
                Acceleration = forcesSums.divide(currentObject.getMass());
                currentObject.setAcceleration(Acceleration);


                // velocity
                double v_x = currentObject.getVelocity().getX()
                        + Acceleration.getX() * getDeltaTime();

                double v_y = currentObject.getVelocity().getY()
                        + Acceleration.getY() * getDeltaTime();

                if (currentObject.getType().equals(GameObjectModel.TYPES.SQUARE)) {
                    //System.out.println("" + currentObject.getAcceleration().toString());
                    //  System.out.println("" + currentObject.getPosition().toString());
                    // System.out.println("");
                }

                currentObject.setAcceleration(Acceleration);

                Velocity = new Force(v_x, v_y);
                currentObject.setVelocity(Velocity);

                /**
                 * @TODO: Check si problème de position
                 */

                // Position
                double x = 0.5 * Acceleration.getX() * Math.pow(getDeltaTime(), 2)
                        + Velocity.getX() * getDeltaTime()
                        + currentObject.getPosition().getX();

                double y = 0.5 * Acceleration.getY() + Math.pow(getDeltaTime(), 2)
                        + Velocity.getY() * getDeltaTime()
                        + currentObject.getPosition().getY();


                currentObject.getPosition().setPosition(x, y);

                if (y > Constants.WINDOW_HEIGHT) {
                    //  Game.BLOCK_STATUS = true;
                    // delete object from motor
                    // unregisterGameobject(currentObject);
                }
            }


            currentObject.removeForces();
        }


        // Deltatime
        deltaTime += 0.005;
    }

    public static void registerGameobject(GameObjectModel gameObjectModel) {
        gameobjects.add(gameObjectModel);
    }

    public static void unregisterGameobject(GameObjectModel gameObjectModel) {
        gameobjects.remove(gameObjectModel);
    }

    public static double getDeltaTime() {
        return deltaTime;
    }

    /**
     * Process
     *
     * @param currentObject
     * @param objectCollided
     */
    public static boolean processCollision(GameObjectModel currentObject, GameObjectModel objectCollided) {

        if (currentObject.getType().equals(GameObjectModel.TYPES.CIRCLE) && objectCollided.getType().equals(GameObjectModel.TYPES.CIRCLE)) {
            return processCircleCollision(currentObject, objectCollided);
        } else if (currentObject.getType().equals(GameObjectModel.TYPES.SQUARE) && objectCollided.getType().equals(GameObjectModel.TYPES.SQUARE)) {
            return processSquareCollision(currentObject, objectCollided);
        } else if (currentObject.getType().equals(GameObjectModel.TYPES.BIRD) && objectCollided.getType().equals(GameObjectModel.TYPES.SQUARE)) {

            boolean t = Tools.intersectionCircleAndRectangle(
                    (int) currentObject.getPosition().getX(),
                    (int) currentObject.getPosition().getY(),
                    Constants.BIRD_DIAMETER,
                    (int) objectCollided.getPosition().getX(),
                    (int) objectCollided.getPosition().getY(),
                    ((ObstacleModel) objectCollided).getWidth(),
                    ((ObstacleModel) objectCollided).getHeight());

            /**
             * Check
             */
            if (t) {
                currentObject.getHitbox().setCollided(true);
                currentObject.getHitbox().addCollided(objectCollided);

                // second object
                objectCollided.getHitbox().setCollided(true);
                objectCollided.getHitbox().addCollided(currentObject);
            }

            return t;
        }

        return false;
    }

    private static boolean processSquareCollision(GameObjectModel objectA, GameObjectModel objectB) {
        if (Tools.intersectionRectangleAndRectangle(
                (int) objectA.getPosition().getX(),
                (int) objectA.getPosition().getY(),
                ((ObstacleModel) objectA).getWidth(),
                ((ObstacleModel) objectA).getHeight(),
                (int) objectB.getPosition().getX(),
                (int) objectB.getPosition().getY(),
                ((ObstacleModel) objectB).getWidth(),
                ((ObstacleModel) objectB).getHeight())
                ) {
            objectA.getHitbox().setCollided(true);
            objectB.getHitbox().setCollided(true);

            objectA.getHitbox().addCollided(objectB);
            objectB.getHitbox().addCollided(objectA);

            return true;
        }
        return false;
    }

    /**
     * Process circles collision
     *
     * @param objectA
     * @param objectB
     */
    private static boolean processCircleCollision(GameObjectModel objectA, GameObjectModel objectB) {

        Force velocityA, velocityB;

        /**
         * Collision Vector
         */
        double V_x = objectB.getPosition().getX() - objectA.getPosition().getX(),
                V_y = objectB.getPosition().getY() - objectA.getPosition().getY();

        Vector2d V = new Vector2d(V_x, V_y);

        velocityA = objectA.getVelocity();
        velocityB = objectB.getVelocity();


        objectA.setVelocity(new Force(-V_x, -V_y));
        objectB.setVelocity(new Force(-V_x * velocityB.getX(), -V_y * velocityB.getY()));


        // update des positions pour éviter les entrées de collision

        // Force de reaction
      /*  Force c = objectA.getTotalConstantsForces();
        Force reaction = new Force(c.getX(), -objectB.getVelocity().getY());
        objectA.addForce(reaction);
        objectB.addForce(reaction);*/
        /*

	Vector3 U1x = (V * VelocityA) * V;
	Vector3 U1y = VelocityA - U1x;

	Vector3 U2x = (-V * VelocityB) * (-V);
	Vector3 U2y = VelocityB - U2x;

	//Find new velocity
	Vector3 V1x = ( (U1x*fM1) + (U2x*fM2) - (U1x-U2x)*fM2 )/(fM1+fM2);
	Vector3 V1y = U1y;

	Vector3 V2x = ( (U1x*fM1) + (U2x*fM2) - (U2x-U1x)*fM1 )/(fM1+fM2);
	Vector3 V2y = U2y;

	Vector3 vReflechie1 = V1x+V1y;
	Vector3 vReflechie2 = V2x+V2y;
         */
        return true;
    }


    private static void resolveCollision(GameObjectModel A, GameObjectModel B) {


        // Calculate relative velocity
        Force rv = B.getVelocity().substract(A.getVelocity());

        Vector2d normal = rv.normal();

        // Calculate relative velocity in terms of the normal direction
        float velAlongNormal = 2;//DotProduct( rv, normal )

        // Do not resolve if velocities are separating
        //  if(velAlongNormal > 0)
        //    return;

        // Calculate restitution
        float e = Math.min(A.restitution, B.restitution);


        // Calculate impulse scalar
        float j = -(1 + e) * velAlongNormal;


        //  j /= 1 / A.getMass() + 1 / B.getMass()          ;
        // j /= A.getMass() + B.getMass();

        // Apply impulse
        Vector2d impulse = normal.multiply(j);

        Vector2d imp = impulse.multiply(A.getMass());

        A.setVelocity(A.getVelocity().substract(imp));
        B.setVelocity(B.getVelocity().add(impulse.multiply(B.getMass())));


        // correctPosition(A,B,normal);
    }

    private static void correctPosition(GameObjectModel A, GameObjectModel B, Vector2d normal) {
        final double percent = 0.2; // usually 20% to 80%
        double penetrationDepth = 15;


        double coeff = (penetrationDepth / (A.getMass() + B.getMass())) * percent;

        Vector2d correction = normal.multiply(coeff);
        Vector2d aPo = A.getPosition().substract(correction.multiply(A.getMass()));
        Vector2d bPo = B.getPosition().substract(correction.multiply(B.getMass()));
        A.getPosition().setPosition(aPo.getX(), aPo.getY());
        B.getPosition().setPosition(bPo.getX(), bPo.getY());
    }


    /**
     * Check collision
     */
    private static void checkCollisions() {
        Iterator<GameObjectModel> currentob = gameobjects.iterator();
        while (currentob.hasNext()) {
            GameObjectModel current = currentob.next();
            Iterator<GameObjectModel> others = gameobjects.iterator();

            // CHECK COLLIDED
            while (others.hasNext()) {
                GameObjectModel collided = others.next();
                if (collided == current) {
                    break;
                }


                // test collisions
                processCollision(current, collided);
            }
        }
    }

    private static void processImpulse(GameObjectModel A, GameObjectModel B) {
        Vector2d rv = B.getVelocity().substract(A.getVelocity());
        Vector2d normal = rv.normal();
        double contactVel = rv.dotProduct(normal);

        // Do not resolve if velocities are separating
        if (contactVel > 0)
            return;

        // Calculate restitution
        double e = Math.min(A.restitution, B.restitution);

        // Calculate impulse scalar
        double j = -(1.0f + e) * contactVel;
        j /= A.getMass() + B.getMass();

        // Apply impulse
        Vector2d impulse = normal.multiply(j);
        A.getVelocity().substract(impulse.multiply(A.getMass()));
        B.getVelocity().add(impulse.multiply(B.getMass()));
    }
}




