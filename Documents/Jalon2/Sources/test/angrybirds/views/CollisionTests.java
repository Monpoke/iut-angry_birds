/*
 *  Projet Angry Birds
 */
package angrybirds.views;

import angrybirds.controllers.ObstacleController;
import angrybirds.models.ObstacleModel;
import angrybirds.structures.Vector2d;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class CollisionTests {
    private CircleObstacle obs1;
    private CircleObstacle obs2;
    private CircleObstacle obs3;
    private RectangleObstacle rect4;
    private CircleObstacle circle5;
    
    public CollisionTests() {
    }
    
    // create some object
    
    @Before
    public void setUp() {
        
        // cercle de centre 0,0 de diametre 30
        ObstacleModel obsModel1 = new ObstacleModel(new Vector2d(0, 0), 30);        
        ObstacleController obsController1 = new ObstacleController(obsModel1);
        obs1 = new CircleObstacle(obsModel1, obsController1);
        
        // cercle de centre 50,0 de diametre 30
        ObstacleModel obsModel2 = new ObstacleModel(new Vector2d(50, 0), 30);        
        ObstacleController obsController2 = new ObstacleController(obsModel2);
        obs2 = new CircleObstacle(obsModel2, obsController2);
        
        // cercle de centre 60,0 de diametre 30
        ObstacleModel obsModel3 = new ObstacleModel(new Vector2d(60, 0), 30);        
        ObstacleController obsController3 = new ObstacleController(obsModel3);
        obs3 = new CircleObstacle(obsModel3, obsController3);
        
        // rectangle de position 100, 0 de taille 400 par 100
        ObstacleModel obsModel4 = new ObstacleModel(new Vector2d(100, 0));
        obsModel4.setWidth(400);
        obsModel4.setHeight(100);
        ObstacleController obsController4 = new ObstacleController(obsModel4);
        rect4 = new RectangleObstacle(obsModel4, obsController4);
        
        
        
        // cercle de centre 300,0 de diametre 30
        ObstacleModel obsModel5=new ObstacleModel(new Vector2d(300, 0));        
        ObstacleController obsController5 = new ObstacleController(obsModel5);
        circle5 = new CircleObstacle(obsModel5, obsController5);
        
    }
    
    @Test
    public void testCercleCollision_non_inclus(){
        System.out.println("Test Hitbox 2Cercles non inclus");
        assertFalse(obs1.getModel().getHitbox().intersect(obs2.getModel().getHitbox()));
    }
    @Test
    public void testCercleCollision_inclus(){
        System.out.println("Test Hitbox 2Cercles inclus");
        assertTrue(obs2.getModel().getHitbox().intersect(obs3.getModel().getHitbox()));
    }
    
    @Test
    public void testCercleRectangleCollision_non_inclus(){
        System.out.println("Test Hitbox cercle rectangle non inclus");
        assertFalse(rect4.getModel().getHitbox().intersect(obs3.getModel().getHitbox()));
    }
    
    @Test
    public void testCercleRectangleCollision_inclus(){
        System.out.println("Test Hitbox cercle rectangle inclus");
        assertTrue(rect4.getModel().getHitbox().intersect(circle5.getModel().getHitbox()));
    }
    
    
    
}
