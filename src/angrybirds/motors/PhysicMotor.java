package angrybirds.motors;


import angrybirds.models.GameObjectModel;
import angrybirds.structures.Vector2d;
import angrybirds.trajectories.physic.Force;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Pierre on 04/01/2016.
 */
public class PhysicMotor implements Motor {

    private double timeElapsed = 0f;
    private double currentTime = 0f;

    public final static List<GameObjectModel> gameobjects = new ArrayList<>();
    private static double deltaTime;


    /**
     * Apply forces to every objects
     */
    public static void applyForces() {
        System.out.println("Application des forces");

        Force Acceleration, AccelerationAngu;
        Force Velocity, VitesseAngu;
        Vector2d Position;

        float vAngle;

        Force forcesSums, forcesMoms;

        Iterator<GameObjectModel> allObjects = gameobjects.iterator();

        GameObjectModel currentObject;
        while (allObjects.hasNext()) {
            currentObject = allObjects.next();

            if (currentObject.getType() != GameObjectModel.TYPES.BIRD && currentObject.canMove()) {
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
                    currentObject.setVelocity(new Force(0, 0));

                    /**
                     * @3: Correction de position
                     */

                    /**
                     * @4: si carré, alors modifier position
                     */


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

            }


        }


        // Deltatime
        deltaTime += 0.01;
    }

    public static void registerGameobject(GameObjectModel gameObjectModel) {
        gameobjects.add(gameObjectModel);
    }

    public static double getDeltaTime() {
        return deltaTime;
    }
}
