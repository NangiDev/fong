package com.me.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class GameScreen implements Screen {	
	
	private World world;
	private MyGame game;
	private TextButton pauseButton;
	private Label scoreLabel;

	public GameScreen(MyGame myGame) {
		this.game = myGame;
				
		setupGUI();
		
		System.out.println("new GameScreen created");
	}

	@Override
	public void render(float delta) {
		game.batch.begin();
		draw(delta);
		update(delta);
		game.batch.end();
	}

	public void update(float delta) {
		if (Gdx.input.isKeyPressed(Keys.BACK)){
			game.switchToScreen(GameState.Pause);
		}
		world.tick(delta);
	}

	public void draw(float delta) {

		game.drawBackground();
		
		pauseButton.draw(game.batch, 1);
		scoreLabel.draw(game.batch, 1);
	}
	
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		this.world = new World(game);
		game.stage.addActor(pauseButton);
	}

	@Override
	public void hide() {
		this.world = null;
		game.stage.getRoot().removeActor(pauseButton);
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

	private void setupGUI(){
		pauseButton = new MenuButton("Pause", game.mediumButtonStyle,
				GameState.Pause, game);
		
		pauseButton.setPosition((MyGame.screenWidth) - pauseButton.getWidth(),
				MyGame.screenHeight - pauseButton.getHeight());
		
		scoreLabel = new Label("000000", game.mediumlabelStyle);
		scoreLabel.setPosition(0,
				MyGame.screenHeight - scoreLabel.getHeight());
	}
}
