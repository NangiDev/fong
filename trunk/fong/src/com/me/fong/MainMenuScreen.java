package com.me.fong;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class MainMenuScreen implements Screen {

	private MyGame game;
	private Label signature;
	private TextButton startButton;
	private TextButton highscoreButton;
	private TextButton howToPlayButton;
	private TextButton optionsButton;
	private TextButton creditsButton;
	private TextButton exitButton;
	private Shadable header;
	
	public MainMenuScreen(MyGame myGame) {
		this.game = myGame;
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
		game.entityManager.tick(delta);
	}

	public void draw(float delta) {
		signature.draw(game.batch, 1);
		game.table.draw(game.batch, 1);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		game.gameScreen = null;
		this.header = new Shadable(game.batch, Assets.logotype,
				MyGame.screenWidth * 0.5f - Assets.logotype.getWidth() * 0.5f
						* MyGame.scaleX, MyGame.screenHeight * 0.7f,
				game.entityManager, false);
		game.entityManager.shaderManager.clearLightSources();
		game.entityManager.addEntity(header);
		

		MyGame.difficulty = 1.0f;
		Player.fireLevel = 0;
		Player.healthBars = 0;
		
		createScreen();
		setupMenuLayout();
	}

	@Override
	public void hide() {
		game.entityManager.clearEntityList();
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

	private void createScreen() {
		signature = new Label(
				"A game made by\nJoel Setterberg &\nJonatan Elsgard",
				game.smalllabelStyle);
		signature.setAlignment(Align.center);
		signature.setWidth(MyGame.screenWidth);
		signature.setWrap(true);
		signature.setPosition(0, signature.getHeight() * 0.5f * MyGame.scaleY);

		startButton = new MenuButton("Start", game.mediumButtonStyle,
				GameState.Game, game);
		highscoreButton = new MenuButton("Highscore", game.mediumButtonStyle,
				GameState.Highscore, game);
		howToPlayButton = new MenuButton("How To Play", game.mediumButtonStyle,
				GameState.Instructions, game);
		optionsButton = new MenuButton("Options", game.mediumButtonStyle,
				GameState.Options, game);
		creditsButton = new MenuButton("Credits", game.mediumButtonStyle,
				GameState.Credits, game);
		exitButton = new MenuButton("Exit", game.mediumButtonStyle,
				GameState.Exit, game);

		System.out.println("new MainMenuScreen created");
	}

	private void setupMenuLayout() {
		game.table.add().row().padBottom(25.0f * MyGame.scaleY);
		game.table.add(startButton).row().padBottom(25.0f * MyGame.scaleY);
		game.table.add(highscoreButton).row().padBottom(25.0f * MyGame.scaleY);
		game.table.add(optionsButton).row().padBottom(25.0f * MyGame.scaleY);
		game.table.add(howToPlayButton).row().padBottom(25.0f * MyGame.scaleY);
		game.table.add(creditsButton).row().padBottom(25.0f * MyGame.scaleY);
		game.table.add(exitButton);
		game.table.padTop(header.getTexture().getHeight() * 1.2f
				* MyGame.scaleY);
	}
}
