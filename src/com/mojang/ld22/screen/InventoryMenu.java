package com.mojang.ld22.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mojang.ld22.entity.Player;
import com.mojang.ld22.item.Item;

import es.wiyarmir.minigdxcraft.Globals;
import es.wiyarmir.minigdxcraft.gfx.PortFont;
import es.wiyarmir.minigdxcraft.gfx.PortScreen;

public class InventoryMenu extends Menu {
	private Player player;
	private int selected = 0;

	public InventoryMenu(Player player) {
		this.player = player;

		if (player.activeItem != null) {
			player.inventory.items.add(0, player.activeItem);
			player.activeItem = null;
		}
	}

	public void tick() {
		if (Gdx.input.isKeyPressed(Globals.KEY_MENU))
			game.setMenu(null);

		if (Gdx.input.isKeyPressed(Keys.UP))
			selected--;
		if (Gdx.input.isKeyPressed(Keys.UP))
			selected++;

		int len = player.inventory.items.size();
		if (len == 0)
			selected = 0;
		if (selected < 0)
			selected += len;
		if (selected >= len)
			selected -= len;

		if (Gdx.input.isKeyPressed(Globals.KEY_ATTACK) && len > 0) {
			Item item = player.inventory.items.remove(selected);
			player.activeItem = item;
			game.setMenu(null);
		}
	}

	public void render(PortScreen screen) {
		PortFont.renderFrame(screen, "inventory", 1, 1, 12, 11);
		renderItemList(screen, 1, 1, 12, 11, player.inventory.items, selected);
	}
}