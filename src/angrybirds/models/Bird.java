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
        float pyt = (float) Math.sqrt(mouvement.getX() * mouvement.getX()
                + mouvement.getY() * mouvement.getY());
        System.out.println(pyt);
        float movementRatio = 50.0f / pyt;
        this.mouvement = new Vector(mouvement.getX() * movementRatio,
                mouvement.getY() * movementRatio);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(position.x - 25, position.y - 25, 50, 50);
        g.setColor(Color.BLACK);
        g.drawLine(position.x, position.y, position.x + (int) mouvement.getX(),
                position.y + (int) mouvement.getY());

    }

    public Point getPosition() {
        return position;
    }

}
