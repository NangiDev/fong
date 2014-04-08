package com.me.fong;

import com.badlogic.gdx.Screen;

public class GameOverScreen implements Screen {
	private MyGame game;

	public GameOverScreen(MyGame myGame) {
		this.game = myGame;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
		game.batch.begin();
		game.fontSmall.draw(game.batch, "Game Over", 100, 100);
		game.batch.end();

	}

	public void update(float delta) {

	}

	public void draw(float delta) {
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
