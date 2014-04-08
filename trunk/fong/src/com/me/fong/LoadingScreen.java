package com.me.fong;

import com.badlogic.gdx.Screen;

public class LoadingScreen implements Screen{
	private MyGame game;
	
	public LoadingScreen(MyGame myGame){
		this.game = myGame;
		System.out.println("new LoadingScreen created");
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
