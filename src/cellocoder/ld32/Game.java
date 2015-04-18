package cellocoder.ld32;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.openal.Audio;

import cellocoder.ld32.rendering.Font;
import cellocoder.ld32.rendering.Render;

public class Game {
	
	Audio newWaveFX = Utils.loadSound("./res/newwave.wav");
	Audio explosion = Utils.loadSound("./res/explosion.wav");
	Audio throwFX = Utils.loadSound("./res/throw.wav");
	Audio pass = Utils.loadSound("./res/enemypass.wav");
	
	public Player player;
	
	int bgTex;
	
	int wave = 0;
	
	private List<Enemy> enemies = new ArrayList<Enemy>();
	private List<Enemy> enemyRemoval = new ArrayList<Enemy>();
	private List<Animal> animals = new ArrayList<Animal>();
	private List<Animal> animalRemoval = new ArrayList<Animal>();
	
	private int health = 20;
	
	public Game() {
		player = new Player();
		bgTex = Utils.loadTex("./res/tex/bar.png");
		
		wave = 0;
		
		Enemy.setupTextures();
		Animal.setupTextures();
		
		enemies = new ArrayList<Enemy>();
		enemyRemoval = new ArrayList<Enemy>();
		animals = new ArrayList<Animal>();
		animalRemoval = new ArrayList<Animal>();
		
		newWave();
	}
	
	public void newWave() {
		Utils.playAudio(newWaveFX);
		wave++;
		
		// Good enough
		int num = (int) ((wave * wave / 3f) + 1);
		
		this.enemies.clear();
		for (int i = 0; i < num; i++) {
			this.enemies.add(new Enemy());
		}
	}
	
	private boolean oldSpace = false;
	
	public void tick() {
		player.tick();
		
		for (Enemy e : enemies) {
			e.tick();
		}
		
		for (Animal a : animals) {
			a.tick();
			
			for (Enemy e : enemies) {
				if (a.pos.dist(e.pos.add(new Vec2(64, 0))) < 127) {
					enemyRemoval.add(e);
					Utils.playAudio(explosion);
				}
			}
		}
		
		for (Enemy e : enemies) {
			if (e.pos.y > LudumDare.HEIGHT - 64) {
				if (!enemyRemoval.contains(e))
					enemyRemoval.add(e);
				Utils.playAudio(pass);
				health--;
				if (health < 1) {
					Render.close();
					System.exit(0);
				}
			}
		}
		
		for (Enemy e : enemyRemoval) {
			enemies.remove(e);
			if (enemies.size() < 1) {
				newWave();
			}
		}
		
		for (Animal a : animals) {
			if (a.pos.y < 0)
				animalRemoval.add(a);
		}
		
		for (Animal a : animalRemoval) {
			animals.remove(a);
		}
		
		enemyRemoval.clear();
		animalRemoval.clear();
		
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && !oldSpace) {
			// Fire!
			animals.add(new Animal(new Vec2(player.getPos().x, player.getPos().y)));
			player.throwAnimal();
			Utils.playAudio(throwFX);
		}
		
		oldSpace = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
	}
	
	public void render() {
		Render.tex(bgTex);
		Render.box(new Vec2(0, 0), new Vec2(LudumDare.WIDTH, LudumDare.HEIGHT), false);
		Render.noTex();
		
		player.render();
		
		for (Enemy e : enemies) {
			e.render();
		}
		
		for (Animal a : animals) {
			a.render();
		}
		
		Font.text(("Wave " + wave), new Vec2(0, 0));
		
		Font.text(("Health " + health), new Vec2(0, 24));
	}
	
}
