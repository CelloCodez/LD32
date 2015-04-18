package cellocoder.ld32;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.openal.Audio;

import cellocoder.ld32.rendering.Render;

public class Player {
	
	private Audio step = Utils.loadSound("./res/step.wav");
	
	private Vec2 pos = new Vec2(0, LudumDare.HEIGHT - 102);
	
	private float speed = 12f;
	
	private int[] tex;
	private int frame = 8;
	
	private float animTimer = 0;
	private final float animThresh = 15; // 60 FPS means 4 FPS anims
	
	private boolean throwing = false;
	
	boolean left = false;
	
	public Player() {
		tex = new int[9];
		frame = 8;
		tex[0] = Utils.loadTex("./res/tex/man_stand.png");
		tex[1] = Utils.loadTex("./res/tex/man_throw1.png");
		tex[2] = Utils.loadTex("./res/tex/man_throw2.png");
		tex[3] = Utils.loadTex("./res/tex/man_throw3.png");
		tex[4] = Utils.loadTex("./res/tex/man_walk.png");
		tex[5] = Utils.loadTex("./res/tex/man_walk2.png");
		tex[6] = Utils.loadTex("./res/tex/man_walk3.png");
		tex[7] = Utils.loadTex("./res/tex/man_walk4.png");
		tex[8] = Utils.loadTex("./res/tex/man.png");
		
		speed = 12f;
		
		animTimer = 0;
		
		throwing = false;
		
		pos = new Vec2(0, LudumDare.HEIGHT - 102);
	}
	
	public void tick() {
		animTimer++;
		
		boolean moveKey = false;
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			pos.x -= speed;
			moveKey = true;
			left = true;
			if (pos.x < 0) {
				pos.x = 0;
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			pos.x += speed;
			moveKey = true;
			left = false;
			if (pos.x > LudumDare.WIDTH) {
				pos.x = LudumDare.WIDTH;
			}
		}
		
		if (animTimer >= animThresh) {
			Utils.playAudio(step);
			if (frame == 7) {
				frame = 4;
			} else if (frame == 0) {
				frame = 4;
			} else {
				frame++;
			}
			animTimer = 0;
			if (frame == 4) {
				throwing = false;
			}
		}
		
		if (!moveKey) {
			if (!throwing) {
				animTimer = 0;
				frame = 0;
			}
		}
	}
	
	public Vec2 getPos() {
		return pos;
	}
	
	public void render() {
		Render.tex(tex[frame]);
		Render.box(pos, new Vec2(86, 86), left); // image is 32x32 but I want some scale
		Render.noTex();
	}
	
	public void throwAnimal() {
		throwing = true;
		frame = 1;
	}
	
}
