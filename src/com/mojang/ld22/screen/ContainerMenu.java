package com.mojang.ld22.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mojang.ld22.entity.Inventory;
import com.mojang.ld22.entity.Player;

import es.wiyarmir.minigdxcraft.Globals;
import es.wiyarmir.minigdxcraft.gfx.PortFont;
import es.wiyarmir.minigdxcraft.gfx.PortScreen;

public class ContainerMenu extends Menu {
	private Player player;
	private Inventory container;
	private int selected = 0;
	private String title;
	private int oSelected;
	private int window = 0;

	public ContainerMenu(Player player, String title, Inventory container) {
		this.player = player;
		this.title = title;
		this.container = container;
	}

	public void tick() {
		if (Gdx.input.isKeyPressed(Globals.KEY_MENU))
			game.setMenu(null);

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			window = 0;
			int tmp = selected;
			selected = oSelected;
			oSelected = tmp;
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			window = 1;
			int tmp = selected;
			selected = oSelected;
			oSelected = tmp;
		}

		Inventory i = window == 1 ? player.inventory : container;
		Inventory i2 = window == 0 ? player.inventory : container;

		int len = i.items.size();
		if (selected < 0)
			selected = 0;
		if (selected >= len)
			selected = len - 1;

		if (Gdx.input.isKeyPressed(Keys.UP))
			selected--;
		if (Gdx.input.isKeyPressed(Keys.DOWN))
			selected++;

		if (len == 0)
			selected = 0;
		if (selected < 0)
			selected += len;
		if (selected >= len)
			selected -= len;

		if (Gdx.input.isKeyPressed(Globals.KEY_ATTACK) && len > 0) {
			i2.add(oSelected, i.items.remove(selected));
			if (selected >= i.items.size())
				selected = i.items.size() - 1;
		}
	}

	public void render(PortScreen screen) {
		if (window == 1)
			screen.setOffset(6 * 8, 0);
		PortFont.renderFrame(screen, title, 1, 1, 12, 11);
		renderItemList(screen, 1, 1, 12, 11, container.items,
				window == 0 ? selected : -oSelected - 1);

		PortFont.renderFrame(screen, "inventory", 13, 1, 13 + 11, 11);
		renderItemList(screen, 13, 1, 13 + 11, 11, player.inventory.items,
				window == 1 ? selected : -oSelected - 1);
		screen.setOffset(0, 0);
	}
}