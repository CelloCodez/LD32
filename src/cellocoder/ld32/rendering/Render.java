package cellocoder.ld32.rendering;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import cellocoder.ld32.LudumDare;
import cellocoder.ld32.Vec2;

public class Render {
	
	// Texture stuff
	
	public static void tex(int tex) {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, tex);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
	
	public static void noTex() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}
	
	// Rendering stuff
	
	public static void box(Vec2 pos, Vec2 size, boolean swapTex) {
		// Again LAZY but easy
		GL11.glBegin(GL11.GL_QUADS);
		if (swapTex) {
			GL11.glTexCoord2f(1, 0);
			GL11.glVertex2f(pos.x, pos.y);
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(pos.x + size.x, pos.y);
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex2f(pos.x + size.x, pos.y + size.y);
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex2f(pos.x, pos.y + size.y);
		} else {
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(pos.x, pos.y);
			GL11.glTexCoord2f(1, 0);
			GL11.glVertex2f(pos.x + size.x, pos.y);
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex2f(pos.x + size.x, pos.y + size.y);
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex2f(pos.x, pos.y + size.y);
		}
		GL11.glEnd();
	}
	
	public static void box(Vec2 pos, Vec2 size, Coord c) {
		// Again LAZY but easy
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(c.a.x, c.a.y);
		GL11.glVertex2f(pos.x, pos.y);
		GL11.glTexCoord2f(c.b.x, c.a.y);
		GL11.glVertex2f(pos.x + size.x, pos.y);
		GL11.glTexCoord2f(c.b.x, c.b.y);
		GL11.glVertex2f(pos.x + size.x, pos.y + size.y);
		GL11.glTexCoord2f(c.a.x, c.b.y);
		GL11.glVertex2f(pos.x, pos.y + size.y);
		GL11.glEnd();
	}
	
	// Setup/misc stuff
	
	public static void setup(int w, int h, String t) {
		Display.setTitle(t);
		try {
			Display.setDisplayMode(new DisplayMode(w, h));
			Display.setResizable(false);
			Display.setVSyncEnabled(true);
			Display.create();
			Keyboard.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateWindow() {
		Display.update();
		
		if (Display.isCloseRequested())
			LudumDare.ld32.running = false;
		
		// I know, lazy, but lol oh well
		Display.sync(60);
	}
	
	public static void clear() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}
	
	public static void close() {
		Keyboard.destroy();
		Display.destroy();
		AL.destroy();
	}
	
	public static void init(int w, int h) {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, w, h, 0, -1f, 1f);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		GL11.glClearColor(1f, 1f, 1f, 1f);
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	
}
