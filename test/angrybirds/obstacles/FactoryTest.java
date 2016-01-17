/*
 *  Projet Angry Birds
 */
package angrybirds.obstacles;

import angrybirds.builders.ObstacleFactory;
import angrybirds.structures.Vector2d;
import angrybirds.views.CircleObstacle;
import angrybirds.views.RectangleObstacle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Pierre
 */
public class FactoryTest {


    public FactoryTest() {
    }


    /**
     * Test of generating circle obstacle
     */
    @Test
    public void test_circle_obstacle() {
        assertTrue(ObstacleFactory.createObstacle("CIRCLE", 0, 0) instanceof CircleObstacle);
    }

    /**
     * Test of generating rectangle obstacle
     */
    @Test
    public void test_rectangle_obstacle() {
        assertTrue(ObstacleFactory.createObstacle("SQUARE", 0, 0) instanceof RectangleObstacle);
    }

    /**
     * Test of generating rectangle obstacle
     */
    @Test
    public void test_default_obstacle() {
        assertFalse(ObstacleFactory.createObstacle("NULL", 0, 0) instanceof RectangleObstacle);
    }





}
