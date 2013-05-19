package es.wiyarmir.minigdxcraft;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import es.wiyarmir.minigdxcraft.screen.GameScreen;
import es.wiyarmir.minigdxcraft.screen.TitleScreen;

public class PortedGame extends Game {

	public Texture spriteSheet;
	public TextureRegion[][] spriteMap;
	private int[] colors = new int[256];
	public SpriteBatch batch;
	public PortInputHandler input;

	@Override
	public void create() {
		Gdx.app.log(Globals.TAG, "PortedGame create()");

		input = new PortInputHandler();
		Gdx.input.setInputProcessor(input);

		int pp = 0;
		for (int r = 0; r < 6; r++) {
			for (int g = 0; g < 6; g++) {
				for (int b = 0; b < 6; b++) {
					int rr = (r * 255 / 5);
					int gg = (g * 255 / 5);
					int bb = (b * 255 / 5);
					int mid = (rr * 30 + gg * 59 + bb * 11) / 100;

					int r1 = ((rr + mid * 1) / 2) * 230 / 255 + 10;
					int g1 = ((gg + mid * 1) / 2) * 230 / 255 + 10;
					int b1 = ((bb + mid * 1) / 2) * 230 / 255 + 10;
					colors[pp++] = r1 << 16 | g1 << 8 | b1;
				}
			}
		}

		batch = new SpriteBatch();

		spriteSheet = new Texture(Gdx.files.internal("icons.png"));
		spriteMap = TextureRegion.split(spriteSheet, 8, 8);

		// setMenu(new TitleMenu());
		setScreen(new TitleScreen(this));
	}

	public void won() {
		Gdx.app.log(Globals.TAG, "PortedGame won()");

		throw new NotImplementedException();

	}

	public void changeLevel(int dir) {
		Gdx.app.log(Globals.TAG, "PortedGame changeLevel()");

		throw new NotImplementedException();

	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	public void resetGame() {
		setScreen(new GameScreen(this));
	}

}
