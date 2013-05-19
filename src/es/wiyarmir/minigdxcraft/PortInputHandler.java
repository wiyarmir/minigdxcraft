package es.wiyarmir.minigdxcraft;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class PortInputHandler implements InputProcessor {
	public class Key {
		public int presses, absorbs;
		public boolean down, clicked;

		public Key() {
			keys.add(this);
		}

		public void toggle(boolean pressed) {
			if (pressed != down) {
				down = pressed;
			}
			if (pressed) {
				presses++;
			}

		}

		public void tick() {
			if (absorbs < presses) {
				absorbs++;
				clicked = true;
			} else {
				clicked = false;
			}
		}
	}

	public List<Key> keys = new ArrayList<Key>();

	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key attack = new Key();
	public Key menu = new Key();

	public void releaseAll() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).down = false;
		}
	}

	public void tick() {

		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).tick();

		}
	}

	public PortInputHandler() {
		Gdx.app.log("input", "new PortInputHandler");
	}

	public boolean keyDown(int ke) {
		// if (inputblocked != 0)
		// return false;
		toggle(ke, true);
		return true;
	}

	public boolean keyUp(int ke) {
		toggle(ke, false);
		return true;
	}

	private void toggle(int ke, boolean pressed) {
		if (ke == Keys.NUM_8)
			up.toggle(pressed);
		if (ke == Keys.NUM_2)
			down.toggle(pressed);
		if (ke == Keys.NUM_4)
			left.toggle(pressed);
		if (ke == Keys.NUM_6)
			right.toggle(pressed);
		if (ke == Keys.W)
			up.toggle(pressed);
		if (ke == Keys.S)
			down.toggle(pressed);
		if (ke == Keys.A)
			left.toggle(pressed);
		if (ke == Keys.D)
			right.toggle(pressed);
		if (ke == Keys.UP)
			up.toggle(pressed);
		if (ke == Keys.DOWN) {
			down.toggle(pressed);
			Gdx.app.log("input", "down abs:" + down.absorbs + " pr:"
					+ down.presses);
		}
		if (ke == Keys.LEFT)
			left.toggle(pressed);
		if (ke == Keys.RIGHT)
			right.toggle(pressed);
		if (ke == Keys.TAB)
			menu.toggle(pressed);
		if (ke == Keys.ALT_LEFT)
			menu.toggle(pressed);
		if (ke == Keys.ALT_RIGHT)
			menu.toggle(pressed);
		if (ke == Keys.SPACE)
			attack.toggle(pressed);
		if (ke == Keys.CONTROL_LEFT)
			attack.toggle(pressed);
		if (ke == Keys.NUM_0)
			attack.toggle(pressed);
		if (ke == Keys.INSERT)
			attack.toggle(pressed);
		if (ke == Keys.ENTER)
			menu.toggle(pressed);

		if (ke == Keys.X)
			menu.toggle(pressed);
		if (ke == Keys.C)
			attack.toggle(pressed);
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
