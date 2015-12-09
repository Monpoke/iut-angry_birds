package angrybirds.trajectories.curves;


import angrybirds.models.GameObjectModel;
import angrybirds.structures.Vector2d;
import angrybirds.trajectories.Movement;
import angrybirds.trajectories.MovementApplyer;

public class ObsVectorMovement extends Movement {

    private final Vector2d vector;

    public ObsVectorMovement(Vector2d vector) {
        this.vector = vector;
    }

    
    
    @Override
    public void process(GameObjectModel model, MovementApplyer mvt) {
    }

    @Override
    public double processY(double x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int findX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int findY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
    
}
