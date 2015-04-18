package cellocoder.ld32;

import cellocoder.ld32.rendering.Font;
import cellocoder.ld32.rendering.Render;

public class LudumDare {
	
	public static LudumDare ld32;
	
	public boolean running = false;
	
	public Game game;
	
	public LudumDare() {
		running = false;
	}
	
	private void begin() {
		running = true;
		Render.setup(WIDTH, HEIGHT, TITLE);
		Render.init(WIDTH, HEIGHT);
		Font.setup();
		game = new Game();
		while (running) {
			game.tick();
			Render.clear();
			game.render();
			Render.updateWindow();
		}
		Render.close();
	}
	
	public static int WIDTH = 1024;
	public static int HEIGHT = 768;
	public static String TITLE = "LD32";
	
	public static void main(String[] args) {
		ld32 = new LudumDare();
		ld32.begin();
	}
	
}
