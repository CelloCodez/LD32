package cellocoder.ld32;

import java.util.Random;

import cellocoder.ld32.rendering.Render;

public class Enemy {
	
	public boolean crashing = false;
	
	public Vec2 pos = new Vec2(0, 0);
	
	private static int crashTex;
	private static int tex;
	
	public static Random rand;
	
	public Enemy() {
		crashing = false;
		
		pos = new Vec2((rand.nextFloat() * (LudumDare.WIDTH - 400)) + 200, rand.nextFloat() * 32);
	}
	
	// To avoid init every time an enemy spawns
	public static void setupTextures() {
		crashTex = Utils.loadTex("./res/tex/enemy.png");
		tex = Utils.loadTex("./res/tex/enemy_default.png");
		rand = new Random();
	}
	
	public void tick() {
		pos.y += LudumDare.ld32.game.wave * 0.25f + 0.25f;
	}
	
	public void render() {
		Render.tex(crashing ? crashTex : tex);
		Render.box(pos, new Vec2(128, 128), false);
		Render.noTex();
	}
	
}
