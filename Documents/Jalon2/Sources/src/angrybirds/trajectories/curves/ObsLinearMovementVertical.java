package angrybirds.trajectories.curves;

import java.util.Random;

import angrybirds.models.GameObjectModel;
import angrybirds.structures.Vector2d;
import angrybirds.trajectories.MovementApplyer;


public class ObsLinearMovementVertical extends LinearMovement {
	
	boolean test = false;
	Random rnd = new Random();
	int z,e;
	public ObsLinearMovementVertical() {
		super(1, 1, 1);
		z = rnd.nextInt(100);
		e = rnd.nextInt(100);
		System.out.println("Z : " + z + " E : " + e);
		// TODO Auto-generated constructor stub
	}

	
	  
	  public int findY() {
		if ( y == (z * -1)) {
			test = true;
		}
		
		if (y == e) {
			test = false;
		}
		
		if(test) {
			y = (int) y +b;
			
		} else {
			y = (int) y-b;
		}
		 
	        return y;
	  }
	  
	  public void process(GameObjectModel model, MovementApplyer mvt) {
	        Vector2d position = model.getPosition();
	 	        
	        y = findY();

	        // apply movement
	        position.setX(mvtApplyer.getStartPosition().getX());
	        position.setY(mvtApplyer.getStartPosition().getY() + y);

	    }
	  

}
