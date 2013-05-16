package com.mojang.ld22.screen;

import com.badlogic.gdx.Gdx;
import com.mojang.ld22.gfx.Color;

import es.wiyarmir.minigdxcraft.Globals;
import es.wiyarmir.minigdxcraft.gfx.PortFont;
import es.wiyarmir.minigdxcraft.gfx.PortScreen;

public class InstructionsMenu extends Menu {
	private Menu parent;

	public InstructionsMenu(Menu parent) {
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

		PortFont.draw("HOW TO PLAY", screen, 4 * 8 + 4, 1 * 8,
				Color.get(0, 555, 555, 555));
		PortFont.draw("Move your character", screen, 0 * 8 + 4, 3 * 8,
				Color.get(0, 333, 333, 333));
		PortFont.draw("with the arrow keys", screen, 0 * 8 + 4, 4 * 8,
				Color.get(0, 333, 333, 333));
		PortFont.draw("press C to attack", screen, 0 * 8 + 4, 5 * 8,
				Color.get(0, 333, 333, 333));
		PortFont.draw("and X to open the", screen, 0 * 8 + 4, 6 * 8,
				Color.get(0, 333, 333, 333));
		PortFont.draw("inventory and to", screen, 0 * 8 + 4, 7 * 8,
				Color.get(0, 333, 333, 333));
		PortFont.draw("use items.", screen, 0 * 8 + 4, 8 * 8,
				Color.get(0, 333, 333, 333));
		PortFont.draw("Select an item in", screen, 0 * 8 + 4, 9 * 8,
				Color.get(0, 333, 333, 333));
		PortFont.draw("the inventory to", screen, 0 * 8 + 4, 10 * 8,
				Color.get(0, 333, 333, 333));
		PortFont.draw("equip it.", screen, 0 * 8 + 4, 11 * 8,
				Color.get(0, 333, 333, 333));
		PortFont.draw("Kill the air wizard", screen, 0 * 8 + 4, 12 * 8,
				Color.get(0, 333, 333, 333));
		PortFont.draw("to win the game!", screen, 0 * 8 + 4, 13 * 8,
				Color.get(0, 333, 333, 333));
	}
}
