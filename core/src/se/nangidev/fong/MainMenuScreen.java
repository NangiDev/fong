package se.nangidev.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

public class MainMenuScreen implements Screen {

	private FongMain game;
	private Label signature;
	private TextButton startButton;
	private TextButton highscoreButton;
	private TextButton howToPlayButton;
	private TextButton optionsButton;
	private TextButton creditsButton;
	private TextButton exitButton;
	private Shadable header;
	
	public MainMenuScreen(FongMain FongMain) {
		this.game = FongMain;
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
				FongMain.screenWidth * 0.5f - Assets.logotype.getWidth() * 0.5f
						* FongMain.scaleX, FongMain.screenHeight * 0.7f,
				game.entityManager, false);
		game.entityManager.shaderManager.clearLightSources();
		game.entityManager.addEntity(header);
		

		FongMain.difficulty = 1.0f;
		FongMain.score = 0;
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
		signature.setWidth(FongMain.screenWidth);
		signature.setWrap(true);
		signature.setPosition(0, signature.getHeight() * 0.5f * FongMain.scaleY);

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

		switch (Gdx.app.getType()) {
			case WebGL:
				break;
			default:
				exitButton = new MenuButton("Exit", game.mediumButtonStyle,
						GameState.Exit, game);
				break;
		}

		System.out.println("new MainMenuScreen created");
	}

	private void setupMenuLayout() {
		game.table.add().padBottom(25.0f * FongMain.scaleY).row();
		game.table.add(startButton).padBottom(25.0f * FongMain.scaleY).row();
		game.table.add(highscoreButton).padBottom(25.0f * FongMain.scaleY).row();
		game.table.add(optionsButton).padBottom(25.0f * FongMain.scaleY).row();
		game.table.add(howToPlayButton).padBottom(25.0f * FongMain.scaleY).row();
		game.table.add(creditsButton).padBottom(25.0f * FongMain.scaleY).row();
		game.table.add(exitButton).row();
		game.table.padTop(header.getTexture().getHeight() * 1.2f
				* FongMain.scaleY);
	}
}
