package cellocoder.ld32;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Utils {
	
	public static void playAudio(Audio audio) {
		audio.playAsSoundEffect(1.0f, 1.0f, false);
	}
	
	public static Audio loadSound(String fileName) {
		Audio result = null;
		try {
			result = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static int loadTex(String fileName) {
		int result = 0;
		try {
			result = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(fileName), GL11.GL_NEAREST).getTextureID();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
