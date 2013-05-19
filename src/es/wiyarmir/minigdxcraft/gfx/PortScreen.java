package es.wiyarmir.minigdxcraft.gfx;

import com.badlogic.gdx.graphics.Texture;

import es.wiyarmir.minigdxcraft.Globals;
import es.wiyarmir.minigdxcraft.PortedGame;

public class PortScreen {
	PortedGame game;
	Texture texture;

	public int xOffset;
	public int yOffset;

	public int w;
	public int h;

	public static final int BIT_MIRROR_X = 0x01;
	public static final int BIT_MIRROR_Y = 0x02;

	public PortScreen(int w, int h, Texture texture, PortedGame g) {
		this.texture = texture;
		this.w = w;
		this.h = h;

		this.game = g;

		// pixels = new int[w * h];
	}

	public void render(int x, int y, int tile, int col, int bits) {
		x -= xOffset;
		y -= yOffset;

		int srcX = tile % 32, srcY = tile / 32;
		boolean flipX = (bits & BIT_MIRROR_X) > 0;
		boolean flipY = (bits & BIT_MIRROR_Y) > 0;
		game.batch.draw(texture, x * Globals.SCALE, (Globals.HEIGHT - y)
				* Globals.SCALE, 8 * Globals.SCALE, 8 * Globals.SCALE,
				srcX * 8, srcY * 8, 8, 8, flipX, flipY);
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void renderLight(int x, int y, int r) {
		// Gdx.app.log(Globals.TAG, "renderlight()");

	}

	public void clear(int i) {

	}

}
