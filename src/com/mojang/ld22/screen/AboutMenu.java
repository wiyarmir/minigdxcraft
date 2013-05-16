package com.mojang.ld22.screen;

import com.badlogic.gdx.Gdx;
import com.mojang.ld22.gfx.Color;

import es.wiyarmir.minigdxcraft.Globals;
import es.wiyarmir.minigdxcraft.gfx.PortFont;
import es.wiyarmir.minigdxcraft.gfx.PortScreen;

public class AboutMenu extends Menu {
	private Menu parent;

	public AboutMenu(Menu parent) {
		this.parent = parent;
	}

	public void tick() {
		if (Gdx.input.isKeyPressed(Globals.KEY_ATTACK)
				|| Gdx.input.isKeyPressed(Globals.KEY_MENU)) {
			game.setMenu(parent);
		}
	}

	public void render(PortScreen screen) {
		screen.clear(0);

		PortFont.draw("About Minicraft", screen, 2 * 8 + 4, 1 * 8,
				Color.get(0, 555, 555, 555));
		PortFont.draw("Minicraft was made", screen, 0 * 8 + 4, 3 * 8,
				Color.get(0, 333, 333, 333));
		PortFont.draw("by Markus Persson", screen, 0 * 8 + 4, 4 * 8,
				Color.get(0, 333, 333, 333));
		PortFont.draw("For the 22'nd ludum", screen, 0 * 8 + 4, 5 * 8,
				Color.get(0, 333, 333, 333));
		PortFont.draw("dare competition in", screen, 0 * 8 + 4, 6 * 8,
				Color.get(0, 333, 333, 333));
		PortFont.draw("december 2011.", screen, 0 * 8 + 4, 7 * 8,
				Color.get(0, 333, 333, 333));
		PortFont.draw("it is dedicated to", screen, 0 * 8 + 4, 9 * 8,
				Color.get(0, 333, 333, 333));
		PortFont.draw("my father. <3", screen, 0 * 8 + 4, 10 * 8,
				Color.get(0, 333, 333, 333));
	}
}
