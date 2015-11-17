package angrybirds.trajectories.curves;

import angrybirds.models.GameObjectModel;
import angrybirds.structures.Vector2d;
import angrybirds.trajectories.MovementApplyer;


public class ObsLinearMovementVertical extends LinearMovement {

	public ObsLinearMovementVertical(int a, int b, int xBy) {
		super(a, b, xBy);
		// TODO Auto-generated constructor stub
	}

	
	
	  
	  public int findY() {
		  y = (int) a * x + b;

	        return y;
	  }
	  
	  public void process(GameObjectModel model, MovementApplyer mvt) {
	        Vector2d position = model.getPosition();
	 	        
	        y = findY();

	        // apply movement
	        position.setX(mvtApplyer.getStartPosition().getX() + x);
	        position.setY(mvtApplyer.getStartPosition().getY() + y);

	    }
	  


}
