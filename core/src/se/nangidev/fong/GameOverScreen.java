package se.nangidev.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.Input.Keys;

public class GameOverScreen implements Screen {
	private FongMain game;
	private Shadable header;
	private Label signature;
	private TextField nameField;
	private Texture nameFieldTexture;
	private Label textLabel;
	private Label scoreLabel;
	private Label pointLabel;
	private TextButton resumeButton;
	private TextButton backButton;

	public GameOverScreen(FongMain FongMain) {
		this.game = FongMain;
		nameFieldTexture = new Texture(Gdx.files.internal("menu/buttonYellow.png"));
		nameField = new TextField("abcde", game.textFieldStyle);
		nameField.setMaxLength(5);
		nameField.setCursorPosition(5);
		textLabel = new Label("Enter name:", game.smalllabelStyle);
		scoreLabel = new Label("Score:", game.smalllabelStyle);
		pointLabel = new Label("999999", game.mediumlabelStyle);

		resumeButton = new MenuButton("Restart", game.mediumButtonStyle,
				se.nangidev.fong.GameState.Game, game);
		backButton = new MenuButton("Main Menu", game.mediumButtonStyle,
				se.nangidev.fong.GameState.MainMenu, game);

		game.stage.setKeyboardFocus(nameField);
		
		System.out.println("new GameOverScreen created");
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
			game.switchToScreen(GameState.MainMenu);
		}
		game.entityManager.tick(delta);
	} 

	public void draw(float delta) {
		signature.draw(game.batch, 1);
		game.batch.draw(nameFieldTexture, (FongMain.screenWidth * 0.5f) - (nameFieldTexture.getWidth() * 0.5f * FongMain.scaleX), nameField.getY(), nameFieldTexture.getWidth() * FongMain.scaleX,
				nameFieldTexture.getHeight() * FongMain.scaleY);
		game.table.draw(game.batch, 1);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		game.entityManager.shaderManager.clearLightSources();
		game.gameScreen = null;
		this.header = new Shadable(game.batch, Assets.gameOver, (FongMain.screenWidth * 0.5f)
				- (Assets.gameOver.getWidth() * 0.5f * FongMain.scaleX),
				FongMain.screenHeight * 0.7f, game.entityManager, false);
		game.entityManager.addEntity(header);
		pointLabel.setText(("000000" + game.score).substring(("" + game.score).length()));
		setupMenuLayout();
	}

	@Override
	public void hide() {
		game.entityManager.clearEntityList();
		game.highscoreManager.addScore(new Score(nameField.getText().toString(), Integer.parseInt(pointLabel.getText().toString())));
		game.table.clearChildren();
		game.score = 0;
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

	private static GlyphLayout glyphLayout = new GlyphLayout();
	private void setupMenuLayout() {
		signature = new Label(
				"Score automatically saves when you press restart or main menu",
				game.smalllabelStyle);
		signature.setAlignment(Align.center);
		signature.setWidth(FongMain.screenWidth);
		signature.setWrap(true);
		signature.setPosition(0, signature.getHeight() * 3.0f * FongMain.scaleY);

		game.table.add().align(Align.left).padBottom(25.0f * FongMain.scaleY).row();
		game.table.add(textLabel).padBottom(50.0f * FongMain.scaleY).row();

		game.table.add(nameField).align(Align.right).row();
		nameField.setText("Fong");
		nameField.setCursorPosition(4);

		game.table.add(scoreLabel).align(Align.center)
				.padBottom(50.0f * FongMain.scaleY).row();
		game.table.add(pointLabel).padBottom(25.0f * FongMain.scaleY).row();
		game.table.add(resumeButton).row();
		game.table.add(backButton).row();

		game.table.padTop(header.getTexture().getHeight() * 1.2f * FongMain.scaleY);
	}
}
