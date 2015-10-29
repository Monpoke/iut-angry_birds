package angrybirds.views;

import angrybirds.models.BirdModel;
import angrybirds.models.GameObjectModel;
import angrybirds.structures.Vector2d;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
/**
 *
 * @author Pierre
 */
public class Bird extends GameObjectView implements IDrawable, Observer {

    /**
     * Takes bird model in parameter.
     *
     * @param model
     */
    public Bird(BirdModel model) {
        super(model);
    }

    /**
     * Draws the bird.
     *
     * @param g
     */
    @Override
    public void draw(Graphics g) {

        drawBeak(g);
        if (((BirdModel) model).isAlive()) {
            g.setColor(Color.ORANGE);
        } else {
            g.setColor(Color.RED);
        }

        // Body
        g.fillOval((int) model.getPosition().getX() - 25, (int) model.getPosition().getY(), 50, 50);

    }

    private void drawBeak(Graphics g) {
        if (((BirdModel) model).isAlive()) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.RED);
        }

        Vector2d position = model.getPosition();

        Vector2d perp = position.getPerpendicular();
        int pointsX[] = {
            (int) position.getX() + (int) position.getLength(),
            (int) position.getX() + (int) (perp.getLength() * 0.5f),
            (int) position.getX() - (int) (perp.getLength() * 0.5f)
        };

        int pointsY[] = {
            ((int) position.getY() + (int) position.getWidth()),
            ((int) position.getY() + (int) (perp.getWidth() * 0.5f)),
            ((int) position.getY() - (int) (perp.getWidth() * 0.5f))
        };

        g.fillPolygon(pointsX, pointsY, 3);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("TRACACACA Object Bird   ");
    }

}
