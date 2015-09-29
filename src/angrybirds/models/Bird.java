package angrybirds.models;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Bird implements Drawable {
	
	private Point position;
	
	public Bird(Point position) {
		this.position = position;
	}
	
	public void draw(Graphics2D g2d) {
		
		g2d.setColor(Color.RED);
		g2d.fillOval(position.x, position.y, 10, 10);
		
	}

}
