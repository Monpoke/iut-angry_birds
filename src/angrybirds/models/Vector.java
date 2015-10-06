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
		
		if (hypothenus == 0)
			return;
		
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

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	
	@Override
	public String toString() {
		return "Vector [x=" + x + ", y=" + y + ", length=" + length
				+ ", width=" + width + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(length);
		result = prime * result + Float.floatToIntBits(width);
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		
		if (length != other.length)
			return false;
		if (width != other.width)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
