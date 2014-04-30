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
	private boolean ignoreTicks = false;

	public GameScreen(MyGame myGame) {
		this.game = myGame;

		setupGUI();
		world = new World(game);
		System.out.println("new GameScreen created");
	}

	@Override
	public void render(float delta) {
		game.batch.begin();
		game.drawBackground(delta);
		update(delta);
		draw(delta);
		game.batch.end();
	}

	public void update(float delta) {
		if (Gdx.input.isKeyPressed(Keys.BACK)) {
			game.switchToScreen(GameState.Pause);
		}
		if (!ignoreTicks)
			world.tick(delta);
	}

	public void draw(float delta) {
		pauseButton.draw(game.batch, 1);
		scoreLabel.setText(("000000" + game.score).substring(("" + game.score)
				.length()));
		scoreLabel.draw(game.batch, 1);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		this.ignoreTicks = false;
		scoreLabel.setText(("000000" + game.score).substring(("" + game.score)
				.length()));
		game.stage.addActor(pauseButton);
	}

	@Override
	public void hide() {
		game.score = Integer.parseInt(scoreLabel.getText().toString());
		game.stage.getRoot().removeActor(pauseButton);
	}

	@Override
	public void pause() {
		this.ignoreTicks = true;
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		world = null;
	}

	private void setupGUI() {
		pauseButton = new MenuButton("Pause", game.mediumButtonStyle,
				GameState.Pause, game);

		pauseButton.setPosition((MyGame.screenWidth) - pauseButton.getWidth(),
				MyGame.screenHeight - pauseButton.getHeight());

		scoreLabel = new Label("000000", game.mediumlabelStyle);
		scoreLabel.setPosition(0, MyGame.screenHeight - scoreLabel.getHeight());
	}
}
