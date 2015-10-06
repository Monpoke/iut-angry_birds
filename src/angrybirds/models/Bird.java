package angrybirds.models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * 
 * @author Messiaen kevin
 * @author Dumont paul
 */
public class Bird implements IDrawable {
	
	private Vector position;

	/**
	 * crée un nouvel oiseau
	 * 
	 * @param position
	 *            de l'oiseau
	 * @param mouvement
	 *            vecteur tangante a la courbe pendant le mouvement de l'oiseau
	 *            après normalisation
	 */
	public Bird(Vector position) {
		this.setPosition(position);
	}

	/**
	 * change le vecteur du mouvement et le normalise de taille 50 !
	 * 
	 * @param mouvement
	 *            vecteur tangante a la courbe pendant le mouvement de l'oiseau
	 */
	public void setPosition(Vector position) {
		position.scale(50.0f);
		this.position = position;
	}

	@Override
	public void draw(Graphics g) {

		drawBeak(g);
		g.setColor(Color.RED);
		g.fillOval((int) position.getX() - 25, (int) position.getY() - 25, 50, 50);

	}

	/**
	 * affiche le bec
	 * 
	 * @param g
	 *            Graphics
	 */
	private void drawBeak(Graphics g) {
		g.setColor(Color.ORANGE);

		Vector perp = position.getPerpendicular();
		int pointsX[] = { (int) position.getX() + (int) position.getLength(),
				(int) position.getX() + (int) (perp.getLength() * 0.5f),
				(int) position.getX() - (int) (perp.getLength() * 0.5f) };
		int pointsY[] = { (int) position.getY() + (int) position.getWidth(),
				(int) position.getY() + (int) (perp.getWidth() * 0.5f),
				(int) position.getY() - (int) (perp.getWidth() * 0.5f) };

		g.fillPolygon(pointsX, pointsY, 3);
	}

	/**
	 * retourne la position de l'oiseau
	 * 
	 * @return la position
	 */
	public Vector getPosition() {
		return position;
	}

}
