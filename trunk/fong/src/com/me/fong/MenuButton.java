package com.me.fong;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MenuButton extends TextButton {

	private Boolean enable = true;

	public MenuButton(final String text, TextButtonStyle skin, boolean state) {
		super(text, skin);

		this.enable = state;
		
		addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (getText().toString().matches(" True")) {
					setText("False");
					enable = false;
				} else {
					setText(" True");
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
	
	public boolean getBoolean(){
		return this.enable;
	}
}
