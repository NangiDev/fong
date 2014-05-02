package com.me.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.GdxRuntimeException;

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
		
		header = new Texture(Gdx.files.internal("menu/logotype.png"));
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
		game.drawBackground(delta);
		
		game.batch.draw(header, (MyGame.screenWidth * 0.5f)
				- (header.getWidth() * 0.5f * MyGame.scaleX),
				MyGame.screenHeight * 0.7f, header.getWidth()
						* MyGame.scaleX, header.getHeight()
						* MyGame.scaleY);
		
		signature.draw(game.batch, 1);

		game.table.draw(game.batch, 1);

		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		if(game.gameScreen != null)
			game.gameScreen.dispose();
		game.entityManager = new EntityManager(game);
		game.gameScreen = null;
		game.score = 0;
		createScreen();
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
	
	private void createScreen(){
		signature = new Label("A game made by\nJoel Setterberg &\nJonatan Elsgard", game.smalllabelStyle);
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
		
		System.out.println("new MainMenuScreen created");
	}

	private void setupMenuLayout() {
		game.table.add().row().padBottom(25.0f * MyGame.scaleY);
		game.table.add(startButton).row().padBottom(25.0f * MyGame.scaleY);
		game.table.add(highscoreButton).row().padBottom(25.0f * MyGame.scaleY);
		game.table.add(optionsButton).row().padBottom(25.0f * MyGame.scaleY);
		game.table.add(howToPlayButton).row().padBottom(25.0f * MyGame.scaleY);
		game.table.add(creditsButton);
		
		game.table.padTop(header.getHeight() * 1.5f * MyGame.scaleY);
	}
}
