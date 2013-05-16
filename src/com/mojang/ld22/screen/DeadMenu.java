package com.mojang.ld22.screen;

import com.badlogic.gdx.Gdx;
import com.mojang.ld22.gfx.Color;

import es.wiyarmir.minigdxcraft.Globals;
import es.wiyarmir.minigdxcraft.gfx.PortFont;
import es.wiyarmir.minigdxcraft.gfx.PortScreen;

public class DeadMenu extends Menu {
	private int inputDelay = 60;

	public DeadMenu() {
	}

	public void tick() {
		if (inputDelay > 0)
			inputDelay--;
		else if (Gdx.input.isKeyPressed(Globals.KEY_ATTACK)
				|| Gdx.input.isKeyPressed(Globals.KEY_MENU)) {
			game.setMenu(new TitleMenu());
		}
	}

	public void render(PortScreen screen) {
		PortFont.renderFrame(screen, "", 1, 3, 18, 9);
		PortFont.draw("You died! Aww!", screen, 2 * 8, 4 * 8,
				Color.get(-1, 555, 555, 555));

		// int seconds = game.gameTime / 60;
		// int minutes = seconds / 60;
		// int hours = minutes / 60;
		// minutes %= 60;
		// seconds %= 60;
		//
		// String timeString = "";
		// if (hours > 0) {
		// timeString = hours + "h" + (minutes < 10 ? "0" : "") + minutes + "m";
		// } else {
		// timeString = minutes + "m " + (seconds < 10 ? "0" : "") + seconds +
		// "s";
		// }
		// Font.draw("Time:", screen, 2 * 8, 5 * 8, Color.get(-1, 555, 555,
		// 555));
		// Font.draw(timeString, screen, (2 + 5) * 8, 5 * 8, Color.get(-1, 550,
		// 550, 550));
		PortFont.draw("Score:", screen, 2 * 8, 6 * 8, Color.get(-1, 555, 555, 555));
		// PortFont.draw("" + game.player.score, screen, (2 + 6) * 8, 6 * 8,
		//			Color.get(-1, 550, 550, 550));
		PortFont.draw("Press C to lose", screen, 2 * 8, 8 * 8,
				Color.get(-1, 333, 333, 333));
	}
}
