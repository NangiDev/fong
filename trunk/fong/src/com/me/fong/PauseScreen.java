package com.me.fong;

import com.badlogic.gdx.Screen;

public class PauseScreen implements Screen{
	private MyGame game;

	public PauseScreen(MyGame myGame) {
		this.game = myGame;
	}

	@Override
	public void render(float delta) {
		game.batch.begin();
		game.fontSmall.draw(game.batch, "Pause", 100, 100);
		game.batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
