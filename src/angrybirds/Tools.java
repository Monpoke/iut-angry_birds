/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds;

import angrybirds.structures.Vector2d;

import java.util.Calendar;

/**
 * @author Pierre
 */
public class Tools {

    /**
     * Debug trace.
     *
     * @param msg
     */
    public static void debug(String msg) {
        if (Constants.DEBUG_MODE) {
            Calendar c = Calendar.getInstance();
            System.out.println("[InferNoDebug " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND) + ":" + c.get(Calendar.MILLISECOND) + " @run" + "]\t" + msg);
        }
    }

    /**
     * Returns the distance between two coordinates.
     *
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     * @return
     */
    public static double distancePoints(double x1, double x2, double y1, double y2) {
        return (((Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2))));
    }

    public static boolean intersectionCircleAndCircle(Vector2d a, double radiusCircleA, Vector2d b, double radiusCircleB){
        double r = radiusCircleA + radiusCircleB;
        r *= r;
        return r < Math.pow((a.getX() + b.getX()),2) + Math.pow((a.getY() + b.getY()),2);
    }

    /**
     * @param circleX
     * @param circleY
     * @param circleR
     * @param rectangleX
     * @param rectangleY
     * @param rectangleWidth
     * @param rectangleHeight
     * @return
     */
    public static boolean intersectionCircleAndRectangle(int circleX, int circleY, int circleR, int rectangleX, int rectangleY, int rectangleWidth, int rectangleHeight) {
        double angle = 0;
        double rectCenterX = rectangleX + rectangleWidth / 2;
        double rectCenterY = rectangleY + rectangleHeight / 2;

        // Rotate circle's center point back
        double unrotatedCircleX = Math.cos(angle) * (circleX - rectCenterX)
                - Math.sin(angle) * (circleY - rectCenterY) + rectCenterX;
        double unrotatedCircleY = Math.sin(angle) * (circleX - rectCenterX)
                + Math.cos(angle) * (circleY - rectCenterY) + rectCenterY;

        // Closest point in the rectangle to the center of circle rotated backwards(unrotated)
        double closestX, closestY;

        // Find the unrotated closest x point from center of unrotated circle
        if (unrotatedCircleX < rectangleX) {
            closestX = rectangleX;
        } else if (unrotatedCircleX > rectangleX + rectangleWidth) {
            closestX = rectangleX + rectangleWidth;
        } else {
            closestX = unrotatedCircleX;
        }

        // Find the unrotated closest y point from center of unrotated circle
        if (unrotatedCircleY < rectangleY) {
            closestY = rectangleY;
        } else if (unrotatedCircleY > rectangleY + rectangleHeight) {
            closestY = rectangleY + rectangleHeight;
        } else {
            closestY = unrotatedCircleY;
        }

        // Determine collision
        boolean collision = false;


        double radius = circleR * 2;

        double distance = distancePoints(unrotatedCircleX, closestX, unrotatedCircleY, closestY);
        if (distance < radius) {
            collision = true; // Collision
        } else {
            collision = false;
        }




        return collision;

    }

    public static boolean intersectionRectangleAndRectangle(int rectangleX, int rectangleY, int rectangleWidth, int rectangleHeight, int rectangleX2, int rectangleY2, int rectangleWidth2, int rectangleHeight2) {

        if((rectangleX2 >=rectangleX + rectangleWidth)      // trop à droite
                || (rectangleX2 + rectangleWidth2 <= rectangleX) // trop à gauche
                || (rectangleY2 >= rectangleY + rectangleHeight) // trop en bas
                || (rectangleY2 + rectangleHeight2 <= rectangleY))  // trop en haut
            return false;
        else
            return true;

    }

}
