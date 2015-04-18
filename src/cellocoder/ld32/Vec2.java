package cellocoder.ld32;

public class Vec2 {
	
	public float x = 0, y = 0;
	
	public Vec2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vec2 add(Vec2 b) {
		return new Vec2(x + b.x, y + b.y);
	}
	
	public void mul(float b) {
		x = x * b;
		y = y * b;
	}
	
	public float dist(Vec2 b) {
		return (float) Math.sqrt(((b.x - x) * (b.x - x)) + ((b.y - y) * (b.y - y)));
	}
	
}
