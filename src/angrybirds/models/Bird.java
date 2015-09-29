package angrybirds.models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Bird implements IDrawable {

	private Point position;
	private Vector mouvement;

	public Bird(Point position, Vector mouvement) {
		this.position = position;
		this.setNormalizedMouvement(mouvement);
	}

	public void setNormalizedMouvement(Vector mouvement) {
		this.mouvement = normalize(mouvement, 50f);
	}

	private Vector normalize(Vector vecteur, float size) {
		float pyt = (float) Math.sqrt(vecteur.getX() * vecteur.getX()
				+ vecteur.getY() * vecteur.getY());
		float movementRatio = size / pyt;
		return new Vector(vecteur.getX() * movementRatio, vecteur.getY()
				* movementRatio);
	}

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

	public Point getPosition() {
		return position;
	}

}
