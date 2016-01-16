package angrybirds.views;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import angrybirds.Constants;
import angrybirds.controllers.BirdController;
import angrybirds.hitbox.CircleHitbox;
import angrybirds.models.BirdModel;
import angrybirds.structures.Vector2d;

/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */

/**
 * @author Pierre
 */
public class Bird extends GameObject implements IDrawable, Observer {

    /**
     * Creates a bird
     *
     * @param birdModel
     * @param birdController
     */
    public Bird(BirdModel birdModel, BirdController birdController) {
        super(birdModel, birdController);
        birdModel.setHitbox(new CircleHitbox(model, Constants.BIRD_DIAMETER));


    }

    /**
     * Draws the bird.
     *
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.RED);
        g.drawRect(getX(), getY(), getWidth(), getHeight());

        drawBeak(g);

        if (((BirdModel) model).isAlive()) {
            g.setColor(Color.ORANGE);
        } else {
            g.setColor(Color.RED);
        }

        // Body
        g.fillOval((int) model.getPosition().getX() - Constants.BIRD_DIAMETER
                / 2, (int) model.getPosition().getY() - Constants.BIRD_DIAMETER
                / 2, Constants.BIRD_DIAMETER, Constants.BIRD_DIAMETER);
    }

    /**
     * @param g
     */
    private void drawBeak(Graphics g) {
        if (((BirdModel) model).isAlive()) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.RED);
        }

        Vector2d position = model.getPosition();
        position.scale(50);

        
        
        Vector2d perp = position.getPerpendicular();
        int pointsX[] = {(int) (position.getX() + (int) position.getLength()),
                (int) (position.getX() + (int) (perp.getLength() * 0.5f)),
                (int) (position.getX() - (int) (perp.getLength() * 0.5f))};
        int pointsY[] = {(int) (position.getY() + (int) position.getWidth()),
                (int) (position.getY() + (int) (perp.getWidth() * 0.5f)),
                (int) (position.getY() - (int) (perp.getWidth() * 0.5f))};

        g.fillPolygon(pointsX, pointsY, 3);
    }

    @Override
    public void update(Observable o, Object arg) {
    }

}
