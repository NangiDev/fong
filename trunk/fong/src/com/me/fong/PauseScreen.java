package com.me.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class PauseScreen implements Screen{
	private MyGame game;
	private Texture header;
	private TextButton resumeButton;
	private TextButton exitButton;

	public PauseScreen(MyGame myGame) {
		this.game = myGame;
		this.header = new Texture(Gdx.files.internal("menu/pause.png"));
		resumeButton = new MenuButton("Resume", game.mediumButtonStyle, GameState.Game, game);
		exitButton = new MenuButton("Exit", game.mediumButtonStyle, GameState.MainMenu, game);
		
		System.out.println("new PauseScreen created");
	}

	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
	}

	public void update(float delta) {
		if (Gdx.input.isKeyPressed(Keys.BACK)){
			//game.switchToScreen(GameState.Game);
		}
	}

	public void draw(float delta) {
		game.batch.begin();

		game.drawBackground();
		
		game.batch.draw(header, (game.screenWidth * 0.5f)
				- (header.getWidth() * 0.5f * game.scaleX),
				game.screenHeight * 0.7f, header.getWidth()
						* game.scaleX, header.getHeight()
						* game.scaleY);
		
		game.table.draw(game.batch, 1);
		
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		setupMenuLayout();
	}

	@Override
	public void hide() {
		game.table.clearChildren();
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
	
	private void setupMenuLayout(){
		
		game.table.add().row().padBottom(100.0f * game.scaleY);
		game.table.add(resumeButton).row().padBottom(25.0f * game.scaleY);
		game.table.add(exitButton);

		game.table.padTop(header.getHeight() * 1.5f * game.scaleY);
	}

}
