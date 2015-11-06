/*
 *  Projet Angry Birds
 */
package angrybirds.trajectories.curves;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class ParabolicMovementTest {

    private ParabolicMovement mvt1;
    private ParabolicMovement mvt2;

    public ParabolicMovementTest() {
    }

    @Before
    public void setUp() {

        mvt1 = new ParabolicMovement(2, 3, -1, 3, 100);
        mvt2 = new ParabolicMovement(2, 7, -1, 3, 100);

    }

    // it should be equals to 0
    @Test
    public void testA() {
        System.out.println("Test parabolique en x1 pour m1 (valoir 0)");
        assertEquals(0, mvt1.findY((int)mvt1.xDecale()));
    }

    // it should be equals to 0
    @Test
    public void testB() {
                System.out.println("Test parabolique en x2 (valoir 0)");
        assertEquals(0, mvt2.findY((int)mvt2.xDecale()));
    }

}
