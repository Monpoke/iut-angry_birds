/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds;

/**
 * 
 * @author Pierre
 */
public abstract class Constants {
    public static final boolean ENABLE_OBSTACLES = true;
    public static final int OBSTACLE_DIAMETER = 50;
    public static final int BIRD_DIAMETER = 50;
    public static final int REFRESH_FPS = 60;
    public static final boolean DEBUG_MODE = false;
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 600;
    public static boolean ENABLE_PAINT_TRAJECTORIES = true;
    public static int PLAY_LAUNCH_AFTER = 1000; // 2 seconds
    public static int PLAY_RESET_AFTER = 1000; // 11 seconds
    public static int MIN_OBSTACLES = 1;
    public static final int MAX_OBSTACLES = 1;
    public static boolean DEBUG_TRAJECTORY = true;
    public static boolean DEBUG_HITBOX = false;
    public static boolean DEBUG_INFOS = true;
    public static boolean DEBUG_DRAG = false;
    static double BIRD_START_X = 180;
    static double BIRD_START_Y_FROM_BOTTOM = 210;
    public static boolean ENABLE_DEATH = true;
}
