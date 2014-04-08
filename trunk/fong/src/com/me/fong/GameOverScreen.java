package com.me.fong;

import com.badlogic.gdx.Screen;

public class GameOverScreen implements Screen {
	private MyGame game;

	public GameOverScreen(MyGame myGame) {
		this.game = myGame;
		System.out.println("new GameOverScreen created");
	}

	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
	}

	public void update(float delta) {

	}

	public void draw(float delta) {
		game.batch.begin();
		
		game.drawBackground();
		
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}
