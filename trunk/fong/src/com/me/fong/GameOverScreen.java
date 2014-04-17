package com.me.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.Input.Keys;

public class GameOverScreen implements Screen {
	private MyGame game;
	private Texture header;
	//private Texture texture;
	private TextField nameField;
	private Label textLabel;
	private Label scoreLabel;
	private Label pointLabel;
	private TextButton resumeButton;
	private TextButton backButton;

	public GameOverScreen(MyGame myGame) {
		this.game = myGame;
		this.header = new Texture(Gdx.files.internal("menu/gameOver.png"));
		//texture = new Texture(Gdx.files.internal("menu/buttonYellow.png"));
		nameField = new TextField("F�ng", game.textFieldStyle);
		nameField.setMaxLength(4);
		nameField.setCursorPosition(4);
		textLabel = new Label("Enter name:", game.smalllabelStyle);
		scoreLabel = new Label("Score:", game.smalllabelStyle);
		pointLabel = new Label("999999", game.mediumlabelStyle);

		resumeButton = new MenuButton("Restart", game.mediumButtonStyle,
				GameState.Game, game);
		backButton = new MenuButton("Main Menu", game.mediumButtonStyle,
				GameState.MainMenu, game);

		game.stage.setKeyboardFocus(nameField);
		
		System.out.println("new GameOverScreen created");
	}

	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
	}

	public void update(float delta) {
		if (Gdx.input.isKeyPressed(Keys.BACK)) {
			game.switchToScreen(GameState.MainMenu);
		}
	}

	public void draw(float delta) {
		game.batch.begin();

		game.drawBackground();

		game.batch.draw(header, (game.screenWidth * 0.5f)
				- (header.getWidth() * 0.5f * game.scaleX),
				game.screenHeight * 0.7f, header.getWidth() * game.scaleX,
				header.getHeight() * game.scaleY);

		//game.batch.draw(texture, nameField.getX() - (texture.getHeight() * 0.48f * game.scaleX), nameField.getY() - (texture.getWidth() * 0.02f * game.scaleY), texture.getWidth() * game.scaleX, texture.getHeight() * game.scaleY);

		//game.table.drawDebug(game.stage);
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
		nameField.invalidate();
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
		game.table.add().row().align(Align.left).padBottom(25.0f * game.scaleY);
		game.table.add(textLabel).row().align(Align.center).padBottom(50.0f * game.scaleY);
		game.table.add(nameField).row().align(Align.left);
		game.table.add(scoreLabel).row().align(Align.center)
				.padBottom(50.0f * game.scaleY);
		game.table.add(pointLabel).row().padBottom(25.0f * game.scaleY);
		game.table.add(resumeButton).row();
		game.table.add(backButton).row();
		game.table.debug();

		game.table.padTop(header.getHeight() * 1.2f * game.scaleY);
	}
}
