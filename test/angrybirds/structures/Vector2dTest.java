/*
 *  Projet Angry Birds
 */
package angrybirds.structures;

import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class Vector2dTest {

    private Vector2d position;
    
    public Vector2dTest() {
    }
    
    @Before
    public void setUp() {
        position = new Vector2d(3, 3);
    }

    /**
     * Test of getX method, of class Vector2d.
     */
    @Test
    public void testGetX() {
        assertEquals(3, position.getX(),0);
    }

    /**
     * Test of getY method, of class Vector2d.
     */
    @Test
    public void testGetY() {        
        assertEquals(3, position.getY(),0);
        
    }

    @Test
    public void testMultiply(){
        Vector2d v = new Vector2d(10,10).multiply(2);
        assertEquals(new Vector2d(20,20), v);
    }

    @Test
    public void test(){
        Vector2d a = new Vector2d(10,10);
        a.setPosition(20,20);
        assertEquals(a, new Vector2d(20,20));
    }

    /**
     * Test of equals method, of class Vector2d.
     */
    @Test
    public void testEquals() {
        Vector2d v = new Vector2d(3, 3);
        assertTrue(v.equals(position));
    }


}
