/*
 *  Projet Angry Birds
 */

package angrybirds;

import angrybirds.obstacles.FactoryTest;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Pierre
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        angrybirds.trajectories.curves.ParabolicMovementTest.class,
        angrybirds.trajectories.curves.ParabolicMovementTest.class,
        angrybirds.views.CollisionTests.class,
        angrybirds.structures.Vector2dTest.class,
        angrybirds.physics.PhysicTest.class,
        angrybirds.obstacles.FactoryTest.class

})
public class TestSuite {

    @Before
    public void setUp() throws Exception {

    }

}
