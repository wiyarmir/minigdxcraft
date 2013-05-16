package com.mojang.ld22.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mojang.ld22.gfx.Color;
import com.mojang.ld22.sound.Sound;

import es.wiyarmir.minigdxcraft.Globals;
import es.wiyarmir.minigdxcraft.gfx.PortFont;
import es.wiyarmir.minigdxcraft.gfx.PortScreen;

public class TitleMenu extends Menu {
	private int selected = 0;

	private static final String[] options = { "Start game", "How to play",
			"About" };

	public TitleMenu() {
	}

	@Override
	public void tick() {
		if (Gdx.input.isKeyPressed(Keys.UP))
			selected--;
		if (Gdx.input.isKeyPressed(Keys.DOWN))
			selected++;

		int len = options.length;
		if (selected < 0)
			selected += len;
		if (selected >= len)
			selected -= len;

		if (Gdx.input.isKeyPressed(Globals.KEY_ATTACK)
				|| Gdx.input.isKeyPressed(Globals.KEY_MENU)) {
			if (selected == 0) {
				Sound.test.play();
				game.resetGame();
				game.setMenu(null);
			}
			if (selected == 1)
				game.setMenu(new InstructionsMenu(this));
			if (selected == 2)
				game.setMenu(new AboutMenu(this));
		}
	}

	public void render(PortScreen screen) {
		screen.clear(0);

		int h = 2;
		int w = 13;
		int titleColor = Color.get(0, 010, 131, 551);
		int xo = (screen.w - w * 8) / 2;
		int yo = 24;
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				screen.render(xo + x * 8, yo + y * 8, x + (y + 6) * 32,
						titleColor, 0);
			}
		}

		for (int i = 0; i < 3; i++) {
			String msg = options[i];
			int col = Color.get(0, 222, 222, 222);
			if (i == selected) {
				msg = "> " + msg + " <";
				col = Color.get(0, 555, 555, 555);
			}
			PortFont.draw(msg, screen, (screen.w - msg.length() * 8) / 2,
					(8 + i) * 8, col);
		}

		PortFont.draw("(Arrow keys,X and C)", screen, 0, screen.h - 8,
				Color.get(0, 111, 111, 111));
	}
}