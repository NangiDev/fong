package com.me.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class CreditsScreen implements Screen{
	private MyGame game;
	private Texture logotypeTexture;

	public CreditsScreen(MyGame myGame) {
		this.game = myGame;
		this.logotypeTexture = new Texture(Gdx.files.internal("menu/Credits.png"));
	}

	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
	}
	
	public void update(float delta){
		
	}
	
	public void draw(float delta){
		game.batch.begin();
		
		game.drawBackground();
		game.batch.draw(logotypeTexture, (game.screenWidth * 0.5f)
				- (logotypeTexture.getWidth() * 0.5f * game.scaleX),
				game.screenHeight * 0.75f, logotypeTexture.getWidth()
						* game.scaleX, logotypeTexture.getHeight()
						* game.scaleY);
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
