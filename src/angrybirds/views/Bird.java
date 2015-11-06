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
 * 
 * @author Pierre
 */
public class Bird extends GameObjectView implements IDrawable, Observer {

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

		drawBeak_old(g);
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
	 * 
	 * @param g
	 */
	private void drawBeak(Graphics g) {
		if (((BirdModel) model).isAlive()) {
			g.setColor(Color.BLACK);
		} else {
			g.setColor(Color.RED);
		}

		Vector2d position = model.getPosition();

		Vector2d perp = position.getPerpendicular();

		int d = Constants.BIRD_DIAMETER;

		double angle = Math.sin(model.getAngle());
		double r = Constants.BIRD_DIAMETER / 2;

		int largeur = 1;
		int hauteur = 1;

		int pointsX[] = {
				(int) (position.getX() + r * Math.cos(angle - largeur)),
				(int) (position.getX() + hauteur * r * Math.cos(angle)),
				(int) (position.getX() + Constants.BIRD_DIAMETER / 4 - (int) (perp
						.getLength() * 0.5f)) };

		int pointsY[] = {
				((int) position.getY() + Constants.BIRD_DIAMETER / 4 + (int) position
						.getWidth()),
				((int) position.getY() + Constants.BIRD_DIAMETER / 4 + (int) (perp
						.getWidth() * 0.5f)),
				((int) position.getY() + Constants.BIRD_DIAMETER / 4 - (int) (perp
						.getWidth() * 0.5f)) };

		g.fillPolygon(pointsX, pointsY, 3);
	}

	/**
	 * 
	 * @param g
	 */
	private void drawBeak_old(Graphics g) {
		if (((BirdModel) model).isAlive()) {
			g.setColor(Color.BLACK);
		} else {
			g.setColor(Color.RED);
		}

		Vector2d position = model.getPosition();
		
		position.scale(50);

		Vector2d perp = position.getPerpendicular();
		int pointsX[] = { (int) (position.getX() + (int) position.getLength()),
				(int) (position.getX() + (int) (perp.getLength() * 0.5f)),
				(int) (position.getX() - (int) (perp.getLength() * 0.5f)) };
		int pointsY[] = { (int) (position.getY() + (int) position.getWidth()),
				(int) (position.getY() + (int) (perp.getWidth() * 0.5f)),
				(int) (position.getY() - (int) (perp.getWidth() * 0.5f)) };

		g.fillPolygon(pointsX, pointsY, 3);
	}

	@Override
	public void update(Observable o, Object arg) {
	}

}
