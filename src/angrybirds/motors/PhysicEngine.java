package angrybirds.motors;


import angrybirds.Constants;
import angrybirds.Tools;
import angrybirds.models.GameObjectModel;
import angrybirds.models.ObstacleModel;
import angrybirds.structures.Vector2d;
import angrybirds.trajectories.physic.Force;

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
     * Apply forces to every objects
     */
    public static void applyForces() {
        Force Acceleration, AccelerationAngu;
        Force Velocity, VitesseAngu;
        Vector2d Position;

        float vAngle;

        Force forcesSums, forcesMoms;

        Iterator<GameObjectModel> allObjects = gameobjects.iterator();

        GameObjectModel currentObject;
        while (allObjects.hasNext()) {
            currentObject = allObjects.next();

            System.out.println(currentObject.getLabelName());

            if (currentObject.canMove()) {
                forcesSums = new Force(0f, 0f);
                forcesMoms = new Force(0f, 0f);

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
                    while(iterator.hasNext()){
                        GameObjectModel c = iterator.next();
                        if(c==currentObject){
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
    public static void processCollision(GameObjectModel currentObject, GameObjectModel objectCollided) {
        if (currentObject.getType().equals(GameObjectModel.TYPES.CIRCLE) && objectCollided.getType().equals(GameObjectModel.TYPES.CIRCLE)) {
            processCircleCollision(currentObject, objectCollided);
        } else if (currentObject.getType().equals(GameObjectModel.TYPES.SQUARE) && objectCollided.getType().equals(GameObjectModel.TYPES.SQUARE)) {
            processSquareCollision(currentObject, objectCollided);
        }

    }

    private static void processSquareCollision(GameObjectModel objectA, GameObjectModel objectB) {
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
        }
    }

    /**
     * Process circles collision
     *
     * @param objectA
     * @param objectB
     */
    private static void processCircleCollision(GameObjectModel objectA, GameObjectModel objectB) {

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

    private static void correctPosition(GameObjectModel A, GameObjectModel B,Vector2d normal){
        final double percent = 0.2; // usually 20% to 80%
        double penetrationDepth=15;


        double coeff = (penetrationDepth / (A.getMass() + B.getMass())) * percent;

        Vector2d correction = normal.multiply(coeff);
        Vector2d aPo = A.getPosition().substract(correction.multiply(A.getMass()));
        Vector2d bPo = B.getPosition().substract(correction.multiply(B.getMass()));
        A.getPosition().setPosition(aPo.getX(), aPo.getY());
        B.getPosition().setPosition(bPo.getX(), bPo.getY());
    }
}




