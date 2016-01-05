package angrybirds.models;

import angrybirds.Constants;
import angrybirds.controllers.GameObjectController;
import angrybirds.hitbox.HitBox;
import angrybirds.structures.Vector2d;
import angrybirds.trajectories.physic.Force;
import angrybirds.views.GameObjectView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */

/**
 * Represents basic views.
 *
 * @author Pierre
 */
public abstract class GameObjectModel extends Observable {

    public enum TYPES {
        SQUARE,
        CIRCLE,
        BIRD
    }

    protected TYPES type;

    /**
     * =================
     * POSITIONS AND PHYSICS
     * =================
     */

    protected Force acceleration = new Force(0f, 0f);
    protected Force velocity = new Force(0f, 0f);
    protected List<Force> forces = new ArrayList<>();
    protected List<Force> constantsForces = new ArrayList<>();

    /**
     * Object position.
     */
    protected Vector2d position;

    /**
     * Object angle.
     */
    protected double angle;

    /**
     * Contains last positions.
     */
    protected List<Vector2d> positions = new ArrayList<>();

    /**
     * Contains the mass of entity
     */
    protected double mass;

    protected boolean canMove = true;

    /**
     * Enable trajectory paint
     */
    protected boolean enabledTrajectory = true;

    /**
     * Contains view.
     */
    protected GameObjectView view;

    protected GameObjectController controller;

    public void setController(GameObjectController controller) {
        this.controller = controller;
    }

    public GameObjectController getController() {
        return controller;
    }

    /**
     * Enable collisions if not null.
     */
    protected HitBox hitbox = null;

    public Vector2d getPosition() {
        return position;
    }

    /**
     * Notify all observers, all registred views.
     */
    public void sendNotification() {

        // check position
        check();

        setChanged();
        notifyObservers();
    }

    public boolean hasCollision() {
        return this.hitbox != null;
    }

    /**
     * Checks positions.
     */
    private void check() {
        if (position.getX() < 0) {
            position.setX(0);
        }

        if (position.getY() < 0) {
            position.setY(0);
        } else if (position.getY() > Constants.WINDOW_HEIGHT - 10) {
            position.setY(Constants.WINDOW_HEIGHT - 10);
        }
    }

    /**
     * Returns view.
     *
     * @return
     */
    public GameObjectView getView() {
        return view;
    }

    public void setHitbox(HitBox hitbox) {
        this.hitbox = hitbox;
    }

    public HitBox getHitbox() {
        return hitbox;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public List<Vector2d> getPositions() {
        return positions;
    }

    public void setPositions(List<Vector2d> positions) {
        this.positions = positions;
    }

    public boolean isEnableTrajectory() {
        return enabledTrajectory;
    }

    public void setEnableTrajectory(boolean enableTrajectory) {
        this.enabledTrajectory = enableTrajectory;
    }

    public double getMass() {
        return Math.max(1, mass);
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public TYPES getType() {
        return type;
    }

    public void setType(TYPES type) {
        this.type = type;
    }

    public boolean canMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public Force getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Force acceleration) {
        this.acceleration = acceleration;
    }

    public Force getVelocity() {
        return velocity;
    }

    public void setVelocity(Force velocity) {
        this.velocity = velocity;
    }

    public List<Force> getForces() {
        return forces;
    }

    public void setForces(List<Force> forces) {
        this.forces = forces;
    }


    public List<Force> getConstantsForces() {
        return constantsForces;
    }

    public Force getTotalConstantsForces() {
        Force f = new Force(0, 0);

        Iterator<Force> iterator = constantsForces.iterator();
        Force current;
        while (iterator.hasNext()) {
            current = iterator.next();
            f.setX(f.getX() + current.getX());
            f.setY(f.getY() + current.getY());
        }
        return f;
    }
}
