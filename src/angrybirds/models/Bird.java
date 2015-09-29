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

	private Point position;
	private Vector mouvement;

	/**
	 * crée un nouvel oiseau
	 * 
	 * @param position
	 *            de l'oiseau
	 * @param mouvement
	 *            vecteur tangante a la courbe pendant le mouvement de l'oiseau
	 *            après normalisation
	 */
	public Bird(Point position, Vector mouvement) {
		this.position = position;
		this.setNormalizedMouvement(mouvement);
	}

	/**
	 * change le vecteur du mouvement et le normalise de taille 50 !
	 * 
	 * @param mouvement
	 *            vecteur tangante a la courbe pendant le mouvement de l'oiseau
	 */
	public void setNormalizedMouvement(Vector mouvement) {
		this.mouvement = normalize(mouvement, 50f);
	}

	/**
	 * normalise un vecteur en fonction d'une taille donnée
	 * 
	 * @param vecteur
	 *            anormalisée
	 * @param size
	 *            taille donnée
	 * @return vecteur normalisée
	 */
	private Vector normalize(Vector vecteur, float size) {
		float pyt = (float) Math.sqrt(vecteur.getX() * vecteur.getX()
				+ vecteur.getY() * vecteur.getY());
		float movementRatio = size / pyt;
		return new Vector(vecteur.getX() * movementRatio, vecteur.getY()
				* movementRatio);
	}

	/**
	 * renvoie le vecteur perpendiculaire au vecteur donnée
	 * 
	 * @param vector
	 *            vecteur auquel on cherche la perpendiculaire
	 * @return le vecteur perpendiculaire
	 */
	private Vector getPerpendicular(Vector vector) {
		Vector perpendicular = new Vector(-vector.getY(), vector.getX());
		return perpendicular;
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

		Vector perp = normalize(getPerpendicular(mouvement), 50f);
		int pointsX[] = { position.x + (int) mouvement.getX(),
				position.x + (int) (perp.getX() * 0.5f),
				position.x - (int) (perp.getX() * 0.5f) };
		int pointsY[] = { position.y + (int) mouvement.getY(),
				position.y + (int) (perp.getY() * 0.5f),
				position.y - (int) (perp.getY() * 0.5f) };

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
