package angrybirds.models;

import java.awt.Dimension;
import java.awt.Point;

/**
 * 
 * @author Samuel - Antoine
 *
 */
public class Obstacle {
	
	private Dimension dimension;
	private String forme;
	private Point centre;
	
	public Obstacle(String forme,Point centre,Dimension dimension) {
		this.forme = forme;	
		this.centre = centre;
		this.dimension = dimension;
	}
	
	public Obstacle() {}
	
	public Dimension getDimension() { return dimension;}
	
	public void setDimension(Dimension dimension) { this.dimension = dimension;}
	
	public String getForme() { return forme;}
	
	public void setForme(String forme) { this.forme = forme;}
	
	public Point getCentre() { return centre;}
	
	public void setCentre(Point point) {this.centre = centre;}
}
