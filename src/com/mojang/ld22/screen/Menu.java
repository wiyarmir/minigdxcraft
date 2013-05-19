package com.mojang.ld22.screen;

import java.util.List;

import com.mojang.ld22.gfx.Color;

import es.wiyarmir.minigdxcraft.PortInputHandler;
import es.wiyarmir.minigdxcraft.gfx.PortFont;
import es.wiyarmir.minigdxcraft.gfx.PortScreen;
import es.wiyarmir.minigdxcraft.screen.GameScreen;

public class Menu {
	protected GameScreen game; 
	protected PortInputHandler input;

	public void init(GameScreen game, PortInputHandler input) {
		this.game = game;
		this.input = input;
	}

	public void tick() {
	}

	public void render(PortScreen screen) {
	}

	public void renderItemList(PortScreen screen, int xo, int yo, int x1,
			int y1, List<? extends ListItem> listItems, int selected) {
		boolean renderCursor = true;
		if (selected < 0) {
			selected = -selected - 1;
			renderCursor = false;
		}
		int w = x1 - xo;
		int h = y1 - yo - 1;
		int i0 = 0;
		int i1 = listItems.size();
		if (i1 > h)
			i1 = h;
		int io = selected - h / 2;
		if (io > listItems.size() - h)
			io = listItems.size() - h;
		if (io < 0)
			io = 0;

		for (int i = i0; i < i1; i++) {
			listItems.get(i + io).renderInventory(screen, (1 + xo) * 8,
					(i + 1 + yo) * 8);
		}

		if (renderCursor) {
			int yy = selected + 1 - io + yo;
			PortFont.draw(">", screen, (xo + 0) * 8, yy * 8,
					Color.get(5, 555, 555, 555));
			PortFont.draw("<", screen, (xo + w) * 8, yy * 8,
					Color.get(5, 555, 555, 555));
		}
	}
}
