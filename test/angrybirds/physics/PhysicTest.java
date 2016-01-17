/*
 *  Projet Angry Birds
 */
package angrybirds.physics;

import angrybirds.builders.ObstacleFactory;
import angrybirds.models.ObstacleModel;
import angrybirds.motors.PhysicEngine;
import angrybirds.structures.Vector2d;
import angrybirds.views.RectangleObstacle;
import angrybirds.views.ShapeObstacle;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * @author Pierre
 */
public class PhysicTest {

    /**
     * Engine
     */
    private PhysicEngine engine;

    public PhysicTest() {

    }

    /**
     * @Before
     */
    public void setUp(){
        engine = new PhysicEngine();
    }


    /**
     * Test of generating rectangle obstacle
     */
    @Test
    public void test_default_obstacle() {

        ShapeObstacle a = ObstacleFactory.createObstacle("CIRCLE", 0, 20);
        ShapeObstacle b = ObstacleFactory.createObstacle("CIRCLE", 0, 30);


       assertTrue(PhysicEngine.processCollision(a.getModel(),b.getModel()));
    }





}
