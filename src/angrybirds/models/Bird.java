package angrybirds.models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Bird implements IDrawable {

    private Point position;
    private Vector mouvement;

    public Bird(Point position, Vector mouvement) {
        this.position = position;
        this.setNormalizedMouvement(mouvement);
    }

    public void setNormalizedMouvement(Vector mouvement) {
        float movementRatio = 75.0f / (float) Math.sqrt(mouvement.getX()
                * mouvement.getX() + mouvement.getY() * mouvement.getY());
        this.mouvement = new Vector(mouvement.getX() * movementRatio,
                mouvement.getY() * movementRatio);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(position.x, position.y, 50, 50);

    }

    public Point getPosition() {
        return position;
    }
    
    
    

}
