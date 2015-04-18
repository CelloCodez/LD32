package cellocoder.ld32.rendering;

import java.util.HashMap;

import cellocoder.ld32.Utils;
import cellocoder.ld32.Vec2;

public class Font {
	
	private static HashMap<Character, Coord> font = new HashMap<Character, Coord>();
	
	private static int tex;
	
	public static void setup() {
		tex = Utils.loadTex("./res/tex/font.png");
		
		font.put('0', new Coord(new Vec2(0, 0), new Vec2(1f / 8f, 1f / 8f)));
		font.put('1', new Coord(new Vec2(1f / 8f, 0), new Vec2(2f / 8f, 1f / 8f)));
		font.put('2', new Coord(new Vec2(2f / 8f, 0), new Vec2(3f / 8f, 1f / 8f)));
		font.put('3', new Coord(new Vec2(3f / 8f, 0), new Vec2(4f / 8f, 1f / 8f)));
		font.put('4', new Coord(new Vec2(4f / 8f, 0), new Vec2(5f / 8f, 1f / 8f)));
		font.put('5', new Coord(new Vec2(5f / 8f, 0), new Vec2(6f / 8f, 1f / 8f)));
		font.put('6', new Coord(new Vec2(6f / 8f, 0), new Vec2(7f / 8f, 1f / 8f)));
		font.put('7', new Coord(new Vec2(7f / 8f, 0), new Vec2(8f / 8f, 1f / 8f)));
		
		font.put('8', new Coord(new Vec2(0, 1f / 8f), new Vec2(1f / 8f, 2f / 8f)));
		font.put('9', new Coord(new Vec2(1f / 8f, 1f / 8f), new Vec2(2f / 8f, 2f / 8f)));
		font.put('A', new Coord(new Vec2(2f / 8f, 1f / 8f), new Vec2(3f / 8f, 2f / 8f)));
		font.put('B', new Coord(new Vec2(3f / 8f, 1f / 8f), new Vec2(4f / 8f, 2f / 8f)));
		font.put('C', new Coord(new Vec2(4f / 8f, 1f / 8f), new Vec2(5f / 8f, 2f / 8f)));
		font.put('D', new Coord(new Vec2(5f / 8f, 1f / 8f), new Vec2(6f / 8f, 2f / 8f)));
		font.put('E', new Coord(new Vec2(6f / 8f, 1f / 8f), new Vec2(7f / 8f, 2f / 8f)));
		font.put('F', new Coord(new Vec2(7f / 8f, 1f / 8f), new Vec2(8f / 8f, 2f / 8f)));
		
		font.put('G', new Coord(new Vec2(0, 2f / 8f), new Vec2(1f / 8f, 3f / 8f)));
		font.put('H', new Coord(new Vec2(1f / 8f, 2f / 8f), new Vec2(2f / 8f, 3f / 8f)));
		font.put('I', new Coord(new Vec2(2f / 8f, 2f / 8f), new Vec2(3f / 8f, 3f / 8f)));
		font.put('J', new Coord(new Vec2(3f / 8f, 2f / 8f), new Vec2(4f / 8f, 3f / 8f)));
		font.put('K', new Coord(new Vec2(4f / 8f, 2f / 8f), new Vec2(5f / 8f, 3f / 8f)));
		font.put('L', new Coord(new Vec2(5f / 8f, 2f / 8f), new Vec2(6f / 8f, 3f / 8f)));
		font.put('M', new Coord(new Vec2(6f / 8f, 2f / 8f), new Vec2(7f / 8f, 3f / 8f)));
		font.put('N', new Coord(new Vec2(7f / 8f, 2f / 8f), new Vec2(8f / 8f, 3f / 8f)));
		
		font.put('O', new Coord(new Vec2(0, 3f / 8f), new Vec2(1f / 8f, 4f / 8f)));
		font.put('P', new Coord(new Vec2(1f / 8f, 3f / 8f), new Vec2(2f / 8f, 4f / 8f)));
		font.put('Q', new Coord(new Vec2(2f / 8f, 3f / 8f), new Vec2(3f / 8f, 4f / 8f)));
		font.put('R', new Coord(new Vec2(3f / 8f, 3f / 8f), new Vec2(4f / 8f, 4f / 8f)));
		font.put('S', new Coord(new Vec2(4f / 8f, 3f / 8f), new Vec2(5f / 8f, 4f / 8f)));
		font.put('T', new Coord(new Vec2(5f / 8f, 3f / 8f), new Vec2(6f / 8f, 4f / 8f)));
		font.put('U', new Coord(new Vec2(6f / 8f, 3f / 8f), new Vec2(7f / 8f, 4f / 8f)));
		font.put('V', new Coord(new Vec2(7f / 8f, 3f / 8f), new Vec2(8f / 8f, 4f / 8f)));
		
		font.put('W', new Coord(new Vec2(0, 4f / 8f), new Vec2(1f / 8f, 5f / 8f)));
		font.put('X', new Coord(new Vec2(1f / 8f, 4f / 8f), new Vec2(2f / 8f, 5f / 8f)));
		font.put('Y', new Coord(new Vec2(2f / 8f, 4f / 8f), new Vec2(3f / 8f, 5f / 8f)));
		font.put('Z', new Coord(new Vec2(3f / 8f, 4f / 8f), new Vec2(4f / 8f, 5f / 8f)));
	}
	
	public static void letter(char letter, Vec2 pos) {
		Render.tex(tex);
		Render.box(pos, new Vec2(16, 16), font.get(letter));
		Render.noTex();
	}
	
	public static void text(String text, Vec2 pos) {
		Vec2 offs = new Vec2(0, 0);
		for (char c : text.toUpperCase().toCharArray()) {
			if (c == ' ') {
				// space, do nothing
			} else if (c == '\n') {
				offs = offs.add(new Vec2(0, 16));
			} else {
				letter(c, pos.add(offs));
			}
			offs = offs.add(new Vec2(16, 0));
		}
	}
	
}
