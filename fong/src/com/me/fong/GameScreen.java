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

	//private TextButton gameOverButton;

	private Label scoreLabel;


	public GameScreen(MyGame myGame) {
		this.game = myGame;
		pauseButton = new MenuButton("Pause", game.mediumButtonStyle,
				GameState.Pause, game);

		pauseButton.setPosition((game.screenWidth) - pauseButton.getWidth(),
				game.screenHeight - pauseButton.getHeight());
		
		/*gameOverButton = new MenuButton("Game Over", game.smallButtonStyle,
				GameState.GameOver, game);
		
		gameOverButton.setPosition(100, 100);*/

		scoreLabel = new Label("000000", game.mediumlabelStyle);
		scoreLabel.setPosition(0,
				game.screenHeight - scoreLabel.getHeight());

		System.out.println("new GameScreen created");
	}

	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
	}

	public void update(float delta) {
		if (Gdx.input.isKeyPressed(Keys.BACK)){
			game.switchToScreen(GameState.Pause);
		}
	}

	public void draw(float delta) {
		game.batch.begin();

		game.drawBackground();

		pauseButton.draw(game.batch, 1);
		scoreLabel.draw(game.batch, 1);
		//gameOverButton.draw(game.batch, 1);

		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		game.stage.addActor(pauseButton);
		//game.stage.addActor(gameOverButton);
	}

	@Override
	public void hide() {
		game.stage.getRoot().removeActor(pauseButton);
		//game.stage.getRoot().removeActor(gameOverButton);
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
