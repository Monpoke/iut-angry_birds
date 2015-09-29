package angrybirds.models;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;

/**
 * 
 * @author Samuel - Antoine
 *
 */

public class Obstacle extends Component{
	
	private Dimension dimension;
	private Point centre;
	
	public Obstacle(Point centre, Dimension dimension) {
		this.centre = centre;
		this.dimension = dimension;
	}
	
	public Obstacle() {}
	
	public Dimension getDimension() { return dimension;}
	
	public void setDimension(Dimension dimension) { this.dimension = dimension;}
	
	public Point getCentre() { return centre;}
	
	public void setCentre(Point point) {this.centre = centre;}
	
	public boolean collides(Bird bird) {
		
		//En attente
		return false;
		
	}
	
	public void paint (Graphics g) {
		
		g.drawRect((int) centre.getX(), (int) centre.getY(), (int) dimension.getWidth(), (int) dimension.getHeight());
		g.fillRect((int) centre.getX(), (int) centre.getY(), (int) dimension.getWidth(), (int) dimension.getHeight());
		g.setColor(Color.BLUE);
		
	}
	
}
