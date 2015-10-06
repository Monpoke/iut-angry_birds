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
	public Bird(Vector mouvement) {
		this.position = position;
		this.setPosition(mouvement);
	}

	/**
	 * change le vecteur du mouvement et le normalise de taille 50 !
	 * 
	 * @param mouvement
	 *            vecteur tangante a la courbe pendant le mouvement de l'oiseau
	 */
	public void setPosition(Vector mouvement) {
		mouvement.scale(50.0f);
		this.mouvement = mouvement;
	}

	@Override
	public void draw(Graphics g) {

		drawBeak(g);
		g.setColor(Color.RED);
		g.fillOval(position.x - 25, position.y - 25, 50, 50);

	}

	/**
	 * affiche le bec
	 * 
	 * @param g
	 *            Graphics
	 */
	private void drawBeak(Graphics g) {
		g.setColor(Color.ORANGE);

		Vector perp = mouvement.getPerpendicular();
		int pointsX[] = { position.x + (int) mouvement.getLength(),
				position.x + (int) (perp.getLength() * 0.5f),
				position.x - (int) (perp.getLength() * 0.5f) };
		int pointsY[] = { position.y + (int) mouvement.getWidth(),
				position.y + (int) (perp.getWidth() * 0.5f),
				position.y - (int) (perp.getWidth() * 0.5f) };

		g.fillPolygon(pointsX, pointsY, 3);
	}

	/**
	 * retourne la position de l'oiseau
	 * 
	 * @return la position
	 */
	public Point getPosition() {
		return position;
	}

}
