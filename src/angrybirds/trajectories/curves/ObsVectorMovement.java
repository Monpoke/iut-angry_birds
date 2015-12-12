package angrybirds.trajectories.curves;


import java.util.Random;

import angrybirds.models.GameObjectModel;
import angrybirds.structures.Vector2d;
import angrybirds.trajectories.Movement;
import angrybirds.trajectories.MovementApplyer;
/**
 * 
 * @author Antoine
 *
 */
public class ObsVectorMovement extends Movement {
	
	
	/**
	 * Vecteur du mouvement de l'obstacle
	 */
    protected final Vector2d vector;
    
    /**
     * Compteur
     */
    protected int cpt = 1;
    
    /**
     * false --> l'obstacle s'eloigne de son point de départ
     * true --> l'obstacle se rapproce de son point de départ
     */
    protected boolean test = false;
    
    private Random r = new Random();
    
    /**
     * Valeur à partir de la quelle l'obstacle va se rapprocer de son point de départ
     */
    private int max;
    
    /**
     * Constructeur du mouvement de l'obstacle
     * @param vector
     * Initialisation de max (entre 5 et 45)
     */
    public ObsVectorMovement(Vector2d vector) {
        this.vector = vector;
        max = r.nextInt(40)+5;
    }

    
    /**
     * Calcul de la nouvelle position de l'obstacle en fonction des attributs length et width du Vector2d vector
     */
    @Override
    public void process(GameObjectModel model, MovementApplyer mvt) {
    	 Vector2d position = model.getPosition();
    	 
         // apply movement
    	
         position.setX(mvtApplyer.getStartPosition().getX() + vector.getLength()*cpt);
         position.setY(mvtApplyer.getStartPosition().getY() + vector.getWidth()*cpt);
         
      	 if(cpt == max || cpt == 0) {
    		 test = !test;
    	 }
    	 
    	 if(test)
    		 cpt--;
    	 else
    		 cpt++;
    	
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
