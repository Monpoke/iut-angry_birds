/*
 *  Projet Angry Birds
 */

package angrybirds;

/**
 *
 * @author Pierre
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    angrybirds.trajectories.curves.ParabolicMovementTest.class,
    angrybirds.trajectories.curves.ParabolicMovementTest.class,
    angrybirds.views.CollisionTests.class,
    angrybirds.structures.Vector2dTest.class
})
public class TestSuite {

    @Before
    public void setUp() throws Exception {
        
    }
    
}
