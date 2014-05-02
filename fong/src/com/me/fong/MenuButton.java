package com.me.fong;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MenuButton extends TextButton {

	private Boolean state = true;

	public MenuButton(final String text, TextButtonStyle skin,
			final boolean state) {
		super(text, skin);
		this.state = state;
		updateBooleanButton(state);

		addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				updateBooleanButton(toggleState());
				if (MyGame.soundOn)
					Assets.buttonSound.play();
			}
		});
	}

	private boolean toggleState() {
		this.state = !state;
		return this.state;
	}

	private void updateBooleanButton(boolean state) {
		if (state)
			setText(" True");
		else

			setText("False");
	}

	public MenuButton(String text, TextButtonStyle skin,
			final GameState gameState, final MyGame game) {
		super(text, skin);

		addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.switchToScreen(gameState);
				if (MyGame.soundOn) {
					if (gameState == GameState.Pause)
						Assets.pauseSound.play();
					else
						Assets.buttonSound.play();
				}
			}
		});
	}

	public boolean getBoolean() {
		return this.state;
	}
}
