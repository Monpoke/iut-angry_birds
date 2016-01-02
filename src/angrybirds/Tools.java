/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
package angrybirds;

import java.util.Calendar;

/**
 *
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

    /**
     *
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


        double radius = circleR*2;
        
        double distance = distancePoints(unrotatedCircleX, closestX, unrotatedCircleY, closestY);
        if (distance < radius) {
            collision = true; // Collision
        } else {
            collision = false;
        }


        /**
         * Check collision
         */
        if(collision==true){
            Game.BLOCK_STATUS=true;
            System.out.println("Collision");
        }


        return collision;

    }

    public static boolean intersectionRectangleAndRectangle(int rectangleX, int rectangleY, int rectangleWidth, int rectangleHeight, int rectangleX2, int rectangleY2, int rectangleWidth2, int rectangleHeight2) {
        boolean result = false;

        float rectHalfWidth = rectangleWidth / 2.0f;
        float rectHalfHeight = rectangleHeight / 2.0f;
        float rectHalfWidth2 = rectangleWidth2 / 2.0f;
        float rectHalfHeight2 = rectangleHeight2 / 2.0f;

        float deltax = Math.abs((rectangleX + rectHalfWidth) - (rectangleX2 + rectHalfWidth2));
        float deltay = Math.abs((rectangleY + rectHalfHeight) - (rectangleY2 + rectHalfHeight2));

        float lengthHypotenuseSqure = deltax * deltax + deltay * deltay;

        do {
            // check that distance between the centerse is more than the distance between the circumcircle
            if (lengthHypotenuseSqure > ((rectHalfWidth + rectHalfWidth2) * (rectHalfWidth + rectHalfWidth2) + (rectHalfHeight + rectHalfHeight2) * (rectHalfHeight + rectHalfHeight2))) {
                //System.out.println("distance between the centerse is more than the distance between the circumcircle");
                break;
            }

            // check that distance between the centerse is less than the distance between the inscribed circle
            float rectMinHalfSide = Math.min(rectHalfWidth, rectHalfHeight);
            float rectMinHalfSide2 = Math.min(rectHalfWidth2, rectHalfHeight2);
            if (lengthHypotenuseSqure < ((rectMinHalfSide + rectMinHalfSide2) * (rectMinHalfSide + rectMinHalfSide2))) {
                //System.out.println("distance between the centerse is less than the distance between the inscribed circle");
                result = true;
                break;
            }

            // check that the squares relate to angles
            if ((deltax > (rectHalfWidth + rectHalfWidth2) * 0.9) && (deltay > (rectHalfHeight + rectHalfHeight2) * 0.9)) {
                //System.out.println("squares relate to angles");
                result = true;
            }
        } while (false);

        return result;
    }

}
