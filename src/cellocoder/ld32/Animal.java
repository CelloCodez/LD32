package cellocoder.ld32;

import cellocoder.ld32.rendering.Render;

public class Animal {
	
	public Vec2 pos = new Vec2(0, 0);
	
	private static int[] tex;
	
	private int animal = 0;
	
	public Animal(Vec2 pos) {
		this.pos = pos;
		animal = (int) Enemy.rand.nextFloat();
	}
	
	// To avoid init every time an animal spawns
	public static void setupTextures() {
		tex = new int[1];
		tex[0] = Utils.loadTex("./res/tex/cat.png");
	}
	
	public void tick() {
		pos.y -= 8;
	}
	
	public void render() {
		Render.tex(tex[animal]);
		Render.box(pos, new Vec2(64, 64), false);
		Render.noTex();
	}
	
}
