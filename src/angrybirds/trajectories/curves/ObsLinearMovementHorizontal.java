package angrybirds.trajectories.curves;

import java.util.Random;

import angrybirds.models.GameObjectModel;
import angrybirds.structures.Vector2d;
import angrybirds.trajectories.MovementApplyer;

public class ObsLinearMovementHorizontal extends LinearMovement {
	
	boolean test = false; 
	int z,e;
	Random rnd = new Random();
	public ObsLinearMovementHorizontal() {
		super(1, 1, 1);
		z = rnd.nextInt(100);
		e = rnd.nextInt(100);
		System.out.println("Z : " + z + " E : " + e);
		// TODO Auto-generated constructor stub
	}

	
	  
	  public int findX() {
		  
		if ( x == (z* -1)) {
			test = true;
		}
		
		if (x == e) {
			test = false;
		}
		
		if(test) {
			x = (int) x +b;
			
		} else {
			x = (int) x-b;
		}
		
	        return x;
	  }
	  
	  public void process(GameObjectModel model, MovementApplyer mvt) {
	        Vector2d position = model.getPosition();
	 	        
	        y = findY();

	        // apply movement
	        position.setX(mvtApplyer.getStartPosition().getX() + x);
	        position.setY(mvtApplyer.getStartPosition().getY());

	    }

}
