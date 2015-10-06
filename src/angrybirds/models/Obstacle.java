package angrybirds.models;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;

/**
 * Crée un obstacle de forme rectangulaire avec lequel l'oiseau peut entrer en collision.
 * 
 * 
 * @author Samuel - Antoine
 *
 */

public class Obstacle extends Component{
	
	private Dimension dimension;
	private Point centre;	

	/**
	 * Constructeur
	 */
	public Obstacle() {}
	
	/**
	 * Constructeur spécifiant le centre et la dimension
	 * @param centre
	 * @param dimension
	 */
	public Obstacle(Point centre, Dimension dimension) {
		this.centre = centre;
		this.dimension = dimension;
	}
	
	/**
	 * @return la dimension de l'obstacle
	 */
	public Dimension getDimension() { return dimension;}
	
	/**
	 * Donne une dimension à l'obstacle
	 * @param dimension
	 */
	public void setDimension(Dimension dimension) { this.dimension = dimension;}
	
	/**
	 * @return le centre de l'obstacle
	 */
	public Point getCentre() { return centre;}
	
	/**
	 * Donne un centre à l'obstacle
	 * @param point
	 */
	public void setCentre(Point point) {this.centre = centre;}
	
	/**
	 * Dis si l'oiseau est entré en collision avec l'obstacle
	 * @param bird
	 * @return true si il y a collision, false sinon
	 */
	public boolean collides(Bird bird) {
		//En attente 
		double[] surfaceOccupee = new double[]{centre.getX()-dimension.getWidth(),centre.getX()+dimension.getWidth(),
				centre.getY()-dimension.getHeight(),centre.getY()+dimension.getHeight()};
		if(bird.getPosition().getX() >= surfaceOccupee[0] && bird.getPosition().getX() <= surfaceOccupee[1]) {
			if(bird.getPosition().getY() >= surfaceOccupee[2] && bird.getPosition().getY() <= surfaceOccupee[3]) {
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * Dessine l'obstacle
	 */
	public void paint (Graphics g) {
		
		g.drawRect((int) centre.getX(), (int) centre.getY(), (int) dimension.getWidth(), (int) dimension.getHeight());
		g.fillRect((int) centre.getX(), (int) centre.getY(), (int) dimension.getWidth(), (int) dimension.getHeight());
		g.setColor(Color.BLUE);
		
	}
	
}
