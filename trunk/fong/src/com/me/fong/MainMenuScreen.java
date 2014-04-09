package com.me.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class MainMenuScreen implements Screen {

	private MyGame game;
	private Texture header;
	private Label signature;
	private TextButton startButton;
	private TextButton highscoreButton;
	private TextButton howToPlayButton;
	private TextButton optionsButton;
	private TextButton creditsButton;

	public MainMenuScreen(MyGame myGame) {
		this.game = myGame;
		this.header = new Texture(Gdx.files.internal("menu/logotype.png"));
		signature = new Label("A game made by\nJoel Setterberg &\nJonatan Elsgard", game.smalllabelStyle);
		signature.setAlignment(Align.center);
		signature.setWidth(game.screenWidth);
		signature.setWrap(true);
		signature.setPosition(0, signature.getHeight() * 0.5f * game.scaleY);
		System.out.println("new MainMenuScreen created");
	}

	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
	}

	public void update(float delta) {
		if (Gdx.input.isKeyPressed(Keys.BACK)){
			Gdx.app.exit();
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
		
		signature.draw(game.batch, 1);

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

	private void setupMenuLayout() {
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

		game.table.add().row().padBottom(25.0f * game.scaleY);
		game.table.add(startButton).row().padBottom(25.0f * game.scaleY);
		game.table.add(highscoreButton).row().padBottom(25.0f * game.scaleY);
		game.table.add(optionsButton).row().padBottom(25.0f * game.scaleY);
		game.table.add(howToPlayButton).row().padBottom(25.0f * game.scaleY);
		game.table.add(creditsButton);
		
		game.table.padTop(header.getHeight() * 1.5f * game.scaleY);
	}
}
