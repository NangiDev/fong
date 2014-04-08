package com.me.fong;

import com.badlogic.gdx.Screen;

public class GameScreen implements Screen{
	private World world;
	private MyGame game;

	public GameScreen(MyGame myGame) {
		this.game = myGame;
		System.out.println("new GameScreen created");
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
		game.fontLarge.draw(game.batch, "GAME!", game.screenWidth*0.2f, game.screenHeight*0.8f);

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
