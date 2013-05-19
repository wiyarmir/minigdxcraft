package es.wiyarmir.minigdxcraft.gfx;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mojang.ld22.gfx.Color;

import es.wiyarmir.minigdxcraft.Globals;

public class PortFont {
	private static String chars = "" + //
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ      " + //
			"0123456789.,!?'\"-+=/\\%()<>:;     " + //
			"";

	@Deprecated
	public static void draw(String msg, Texture fontTexture, SpriteBatch batch,
			int x, int y, int col) {
		msg = msg.toUpperCase();
		for (int i = 0; i < msg.length(); i++) {
			int ix = chars.indexOf(msg.charAt(i));
			// Gdx.app.log(Globals.TAG, "ix:" + ix);
			if (ix >= 0) {
				batch.draw(fontTexture, (x + i * 8) * Globals.SCALE,
						(Globals.HEIGHT - y) * Globals.SCALE,
						8 * Globals.SCALE, 8 * Globals.SCALE, (ix % 32) * 8,
						240 + (ix / 32) * 8, 8, 8, false, false);
				// screen.render(x + i * 8, y, ix + 30 * 32, col, 0);
			}
		}
	}

	public static void draw(String msg, PortScreen screen, int x, int y, int col) {
		msg = msg.toUpperCase();
		for (int i = 0; i < msg.length(); i++) {
			int ix = chars.indexOf(msg.charAt(i));
			// Gdx.app.log(Globals.TAG, "ix:" + ix);
			if (ix >= 0) {
				screen.render(x + i * 8, y, ix + 30 * 32, col, 0);
			}
		}
	}

	public static void renderFrame(PortScreen screen, String title, int x0,
			int y0, int x1, int y1) {
		for (int y = y0; y <= y1; y++) {
			for (int x = x0; x <= x1; x++) {
				if (x == x0 && y == y0) {
					screen.render(x * 8, y * 8, 0 + 13 * 32,
							Color.get(-1, 1, 5, 445), 0);
				} else if (x == x1 && y == y0) {
					screen.render(x * 8, y * 8, 0 + 13 * 32,
							Color.get(-1, 1, 5, 445), 1);
				} else if (x == x0 && y == y1) {
					screen.render(x * 8, y * 8, 0 + 13 * 32,
							Color.get(-1, 1, 5, 445), 2);
				} else if (x == x1 && y == y1) {
					screen.render(x * 8, y * 8, 0 + 13 * 32,
							Color.get(-1, 1, 5, 445), 3);
				} else if (y == y0) {
					screen.render(x * 8, y * 8, 1 + 13 * 32,
							Color.get(-1, 1, 5, 445), 0);
				} else if (y == y1) {
					screen.render(x * 8, y * 8, 1 + 13 * 32,
							Color.get(-1, 1, 5, 445), 2);
				} else if (x == x0) {
					screen.render(x * 8, y * 8, 2 + 13 * 32,
							Color.get(-1, 1, 5, 445), 0);
				} else if (x == x1) {
					screen.render(x * 8, y * 8, 2 + 13 * 32,
							Color.get(-1, 1, 5, 445), 1);
				} else {
					screen.render(x * 8, y * 8, 3 + 13 * 32,
							Color.get(5, 5, 5, 5), 1);
				}
			}
		}

		draw(title, screen, x0 * 8 + 8, y0 * 8, Color.get(5, 5, 5, 550));

	}

}
