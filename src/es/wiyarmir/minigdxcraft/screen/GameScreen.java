package es.wiyarmir.minigdxcraft.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.mojang.ld22.entity.Player;
import com.mojang.ld22.gfx.Color;
import com.mojang.ld22.level.Level;
import com.mojang.ld22.level.tile.Tile;
import com.mojang.ld22.screen.Menu;

import es.wiyarmir.minigdxcraft.Globals;
import es.wiyarmir.minigdxcraft.PortInputHandler;
import es.wiyarmir.minigdxcraft.PortedGame;
import es.wiyarmir.minigdxcraft.gfx.PortScreen;

public class GameScreen implements Screen {

	private int playerDeadTime;
	private int pendingLevelChange;
	private int wonTimer = 0;
	public boolean hasWon = false;

	private int[] colors = new int[256];
	private int tickCount = 0;
	public int gameTime = 0;
	public PortedGame game;

	private int currentLevel;
	private Level[] levels;
	public Level level;
	public Player player;
	private PortScreen screen;
	private FPSLogger fpsl;
	private float accumulator;
	private Menu menu = null;

	public GameScreen(PortedGame g) {
		Gdx.app.log(Globals.TAG, "GameScreen constructor()");
		game = g;
		accumulator = 0;

		fpsl = new FPSLogger();

		screen = new PortScreen(Globals.WIDTH, Globals.HEIGHT,
				game.spriteSheet, game);

		resetGame();
	}

	public void resetGame() {
		Gdx.app.log(Globals.TAG, "GameScreen resetGame()");
		playerDeadTime = 0;
		wonTimer = 0;
		gameTime = 0;
		hasWon = false;

		levels = new Level[5];
		currentLevel = 3;

		// levels[4] = new Level(128, 128, 1, null);
		levels[3] = new Level(128, 128, 0, null /* levels[4] */);
		// levels[2] = new Level(128, 128, -1, levels[3]);
		// levels[1] = new Level(128, 128, -2, levels[2]);
		// levels[0] = new Level(128, 128, -3, levels[1]);

		level = levels[currentLevel];
		player = new Player(this);
		player.findStartPos(level);
		level.add(player);

		// for (int i = 0; i < 5; i++) {
		// levels[i].trySpawn(5000);
		levels[3].trySpawn(5000);
		// }

	}

	@Override
	public void render(float delta) {
		fpsl.log();

		accumulator += delta;
		if (accumulator > Globals.TICK_TIME) {
			accumulator = 0;
			tick();
		}

		game.batch.begin();

		int xScroll = player.x - screen.w / 2;
		int yScroll = player.y - (screen.h - 8) / 2;
		if (xScroll < 16)
			xScroll = 16;
		if (yScroll < 16)
			yScroll = 16;
		if (xScroll > level.w * 16 - screen.w - 16)
			xScroll = level.w * 16 - screen.w - 16;
		if (yScroll > level.h * 16 - screen.h - 16)
			yScroll = level.h * 16 - screen.h - 16;

		// if (currentLevel > 3) {
		// int col = Color.get(20, 20, 121, 121);
		// for (int y = 0; y < 14; y++)
		// for (int x = 0; x < 24; x++) {
		// screen.render(x * 8 - ((xScroll / 4) & 7), y * 8
		// - ((yScroll / 4) & 7), 0, col, 0);
		// }
		// }

		level.renderBackground(screen, xScroll, yScroll);

		level.renderSprites(screen, xScroll, yScroll);

		// if (currentLevel < 3) {
		// lightScreen.clear(0);
		// level.renderLight(lightScreen, xScroll, yScroll);
		// screen.overlay(lightScreen, xScroll, yScroll);
		// }

		renderGui();

		game.batch.end();

	}

	private void renderGui() {

		// BG
		for (int y = 0; y < 2; y++) {
			for (int x = 0; x < 20; x++) {
				screen.render(x * 8, screen.h - 8 + y * 8, 21,
						Color.get(000, 000, 000, 000), 0);
			}
		}

		for (int i = 0; i < 10; i++) {
			if (i < player.health)
				screen.render(i * 8, screen.h - 8, 0 + 12 * 32,
						Color.get(000, 200, 500, 533), 0);
			else
				screen.render(i * 8, screen.h - 8, 0 + 12 * 32,
						Color.get(000, 100, 000, 000), 0);

			if (player.staminaRechargeDelay > 0) {
				if (player.staminaRechargeDelay / 4 % 2 == 0)
					screen.render(i * 8, screen.h - 8, 1 + 12 * 32,
							Color.get(000, 555, 000, 000), 0);
				else
					screen.render(i * 8, screen.h, 1 + 12 * 32,
							Color.get(000, 110, 000, 000), 0);
			} else {
				if (i < player.stamina)
					screen.render(i * 8, screen.h, 1 + 12 * 32,
							Color.get(000, 220, 550, 553), 0);
				else
					screen.render(i * 8, screen.h, 1 + 12 * 32,
							Color.get(000, 110, 000, 000), 0);
			}
		}
		if (player.activeItem != null) {
			player.activeItem.renderInventory(screen, 10 * 8, screen.h - 16);
		}

		if (menu != null) {
			menu.render(screen);
		}
	}

	public void changeLevel(int dir) {
		level.remove(player);
		currentLevel += dir;
		level = levels[currentLevel];
		player.x = (player.x >> 4) * 16 + 8;
		player.y = (player.y >> 4) * 16 + 8;
		level.add(player);

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		Gdx.app.log(Globals.TAG, "GameScreen show()");

	}

	@Override
	public void hide() {
		Gdx.app.log(Globals.TAG, "GameScreen hide()");
	}

	@Override
	public void pause() {
		Gdx.app.log(Globals.TAG, "GameScreen pause()");

	}

	@Override
	public void resume() {
		Gdx.app.log(Globals.TAG, "GameScreen resume()");

	}

	@Override
	public void dispose() {

	}

	public void tick() {

		tickCount++;
		if (!player.removed && !hasWon)
			gameTime++;
		if (menu != null) {
			menu.tick();
		} else {
			if (player.removed) {
				playerDeadTime++;
				if (playerDeadTime > 60) {
					// FIXME open dead menu
				}
			} else {
				if (pendingLevelChange != 0) {
					// FIXME level transition
					pendingLevelChange = 0;
				}
			}
			if (wonTimer > 0) {
				if (--wonTimer == 0) {
					// FIXME won
				}
			}

			level.tick();
			Tile.tickCount++;
		}
	}

	public void setMenu(Menu menu) {
		Gdx.app.log(Globals.TAG, "GameScreen setMenu():" + menu);
		this.menu = menu;
	}

}
