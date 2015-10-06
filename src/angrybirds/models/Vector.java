package angrybirds.models;

/**
 * 
 * @author messiaen kevin
 * 
 */
public class Vector {

	private float x;
	private float y;
	private float length;
	private float width;

	public Vector(float x, float y, float length, float width) {
		this.x = x;
		this.y = y;
		this.length = length;
		this.width = width;
	}

	public void scale(float size) {
		float hypothenus = (float) Math.sqrt(length * length + width * width);
		float ratio = size / hypothenus;

		length *= ratio;
		width *= ratio;
	}

	/**
	 * renvoie le vecteur perpendiculaire
	 * 
	 * @return le vecteur perpendiculaire
	 */
	public Vector getPerpendicular() {
		return new Vector(x, y, -width, length);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getLength() {
		return length;
	}

	public void setlength(float length) {
		this.length = length;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

}
