package com.me.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class MainMenuScreen implements Screen {

	private MyGame game;
	private Stage stage;
	private Skin skin;
	private Table table;
	private Texture logotypeTexture;
	private TextButton startButton;
	private TextButton highscoreButton;
	private TextButton howToPlayButton;
	private TextButton optionsButton;
	private TextButton creditsButton;

	public MainMenuScreen(MyGame myGame) {
		this.game = myGame;
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
		table.draw(game.batch, 1);
		game.batch.draw(logotypeTexture, (game.screenWidth * 0.5f)
				- (logotypeTexture.getWidth() * 0.5f * game.scaleX),
				game.screenHeight * 0.75f, logotypeTexture.getWidth()
						* game.scaleX, logotypeTexture.getHeight()
						* game.scaleY);
		
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
		setupManuLayout();
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
		logotypeTexture.dispose();
		stage.dispose();
		skin.dispose();
	}

	public void setupManuLayout() {
		this.stage = new Stage();
		this.skin = new Skin();
		this.table = new Table(skin);

		Gdx.input.setInputProcessor(stage);

		logotypeTexture = new Texture(Gdx.files.internal("menu/logotype.png"));

		skin.add("fontMedium", game.fontMedium);
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = skin.getFont("fontMedium");
		skin.add("default", textButtonStyle);
		table.setFillParent(true);
		table.top();
		table.padTop(game.screenHeight*0.4f);
		stage.addActor(table);

		startButton = game.setupButton("Start", skin, GameState.Game);
		highscoreButton = game.setupButton("Highscore", skin,
				GameState.Highscore);
		howToPlayButton = game.setupButton("How To Play", skin,
				GameState.Instructions);
		optionsButton = game.setupButton("Options", skin, GameState.Options);
		creditsButton = game.setupButton("Credits", skin, GameState.Credits);

		table.add().row().padBottom(25.0f);
		table.add(startButton).row().padBottom(25.0f);
		table.add(highscoreButton).row().padBottom(25.0f);
		table.add(optionsButton).row().padBottom(25.0f);
		table.add(howToPlayButton).row().padBottom(25.0f);
		table.add(creditsButton);
		table.debug();
	}
}
