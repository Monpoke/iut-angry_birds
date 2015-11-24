package angrybirds.trajectories.curves;

import angrybirds.models.GameObjectModel;
import angrybirds.structures.Vector2d;
import angrybirds.trajectories.MovementApplyer;


public class ObsLinearMovementVertical extends LinearMovement {
	
	boolean test = false;
	
	public ObsLinearMovementVertical() {
		super(1, 1, 1);
		// TODO Auto-generated constructor stub
	}

	
	  
	  public int findY() {
		if ( y == -100) {
			test = true;
		}
		
		if (y == 0) {
			test = false;
		}
		
		if(test) {
			y = (int) y +b;
			
		} else {
			y = (int) y-b;
		}
		  System.out.println(y);
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
