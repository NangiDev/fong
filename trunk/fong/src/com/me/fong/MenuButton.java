package com.me.fong;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MenuButton extends TextButton {

	public Boolean enable;

	public MenuButton(final String text, TextButtonStyle skin) {
		super(text, skin);

		addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (getText().toString().matches(" ON")) {
					setText("OFF");
					enable = false;
				} else {
					setText(" ON");
					enable = true;
				}
			}
		});
	}

	public MenuButton(String text, TextButtonStyle skin,
			final GameState gameState, final MyGame game) {
		super(text, skin);

		addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.switchToScreen(gameState);
			}
		});
	}
}
