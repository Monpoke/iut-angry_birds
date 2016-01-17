package angrybirds.structures;

import angrybirds.events.AngryEvent;

/*
 *  Projet AngryBirds
 *  DUT Informatique - 2e
 */
/**
 *
 * @author Pierre Messiaen Kevin
 */
public class Vector2d {

    protected double x, y;
    protected double length = 20, width = 20;

    public Vector2d(){
        this.x=0;
        this.y=0;
    }

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d(double x, double y, double length, double width) {
        this(x, y);
        this.length = length;
        this.width = width;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        sendNotif();
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        sendNotif();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vector2d other = (Vector2d) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        return true;
    }

    /**
     * Scale Vector
     * @param size 
     */
    public void scale(double size) {
        double hypothenus = (double) Math.sqrt(length * length + width * width);

        if (hypothenus == 0) {
            return;
        }

        double ratio = size / hypothenus;

        length *= ratio;
        width *= ratio;
    }

    /**
     * Returns a perpendicular vector
     * @return le vecteur perpendiculaire
     */
    public Vector2d getPerpendicular() {
        return new Vector2d(x, y, -width, length);
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
        sendNotif();
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
        sendNotif();
    }

    /**
     * Copy a vector
     * @param vector2d 
     */
    public void copy(Vector2d vector2d) {
        this.x = vector2d.x;
        this.y = vector2d.y;
        this.length = vector2d.length;
        this.width = vector2d.width;
        sendNotif();
    }

    public void setPosition(double x, double y){
        this.x=x;
        this.y=y;
    }
    
    private AngryEvent event;
    
    /**
     *
     * @param angryEvent
     */
    public void addChangeListener(AngryEvent angryEvent) {
        event = angryEvent;
    }
    
    /**
     * This sends notification
     */
    public void sendNotif(){
        if(event != null){
            event.notif(this);
        }
    }

    public Vector2d copy() {
        return new Vector2d(x, y, length, width);
    }
    
    @Override
    public String toString() {
    	return "x : " + x + ", y : " + y + ", length : " + length + ", width : " + width;
    }

    public Vector2d multiply(double j) {


        return new Vector2d(x*j,y*j);
    }

    public Vector2d substract(Vector2d multiply) {
        return new Vector2d(x - multiply.getX(), y-multiply.getY());
    }
}
