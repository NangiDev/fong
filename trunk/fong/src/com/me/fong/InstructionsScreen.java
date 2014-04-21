package com.me.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class InstructionsScreen implements Screen {
	private MyGame game;
	private Texture header;
	private TextButton backButton;
	private String instructionsText;
	private String controlText;
	private Label textLabel;
	private Label controlLabel;
	private Texture tutorialPlayer;
	private float playerX;

	public InstructionsScreen(MyGame myGame) {
		this.game = myGame;
		this.header = new Texture(Gdx.files.internal("menu/instructions.png"));
		
		tutorialPlayer = new Texture(Gdx.files.internal("playerShip2_blue.png"));
		playerX = (MyGame.screenWidth * 0.5f) - (tutorialPlayer.getWidth() * 0.5f * MyGame.scaleX);
				
		this.instructionsText = "Your goal is to stay alive!\n\n"
				+ "It is easier if you pick up power-ups\n\n"
				+ "Gain points by surviving and kill as many foes as possible!";
		this.controlText = "Touch the Screen to control the ship!";
		
		textLabel = new Label(instructionsText, game.smalllabelStyle);
		controlLabel = new Label(controlText, game.smalllabelStyle);
		backButton = new MenuButton("Back", game.mediumButtonStyle,
				GameState.MainMenu, game);

		System.out.println("new InstructionsScreen created");
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
		
		if (Gdx.input.isTouched()) {
			if (Gdx.input.getX() > playerX + tutorialPlayer.getWidth() * 0.5f
					* MyGame.scaleX + 10.0f * MyGame.scaleX)
				playerX += 800 * delta * MyGame.scaleX;

			else if (Gdx.input.getX() < playerX + tutorialPlayer.getWidth() * 0.5f
					* MyGame.scaleX - 10.0f * MyGame.scaleX)
				playerX -= 800 * delta * MyGame.scaleX;
		}

		if (playerX < 0)
			playerX = 0;
		if (playerX > MyGame.screenWidth - tutorialPlayer.getWidth()
				* MyGame.scaleX)
			playerX = MyGame.screenWidth - tutorialPlayer.getWidth() * MyGame.scaleX;
	}

	public void draw(float delta) {
		game.batch.begin();

		game.drawBackground();

		game.batch.draw(header, (MyGame.screenWidth * 0.5f)
				- (header.getWidth() * 0.5f * MyGame.scaleX),
				MyGame.screenHeight * 0.7f, header.getWidth() * MyGame.scaleX,
				header.getHeight() * MyGame.scaleY);
		
		game.batch.draw(tutorialPlayer, playerX, MyGame.screenHeight * 0.5f,tutorialPlayer.getWidth() * MyGame.scaleX, tutorialPlayer.getHeight() * MyGame.scaleY);
		
		//tutorialPlayer.draw(game.batch, tutorialPlayer.getX(), tutorialPlayer.getY(), tutorialPlayer.getWidth(), tutorialPlayer.getHeight());

		game.table.draw(game.batch, 1);

		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		//tutorialPlayer = new Player();
		//tutorialPlayer.setPosition((MyGame.screenWidth * 0.5f) - (tutorialPlayer.getTexture().getWidth() * 0.5f * MyGame.scaleX), MyGame.screenHeight * 0.5f);
		//tutorialPlayer.setWidth(tutorialPlayer.getTexture().getWidth() * MyGame.scaleX);
		//tutorialPlayer.setHeight(tutorialPlayer.getTexture().getHeight() * MyGame.scaleY);
		setupMenuLayout();
	}

	@Override
	public void hide() {
		game.table.clearChildren();
		//tutorialPlayer.dispose();
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

	public void setupMenuLayout() {

		textLabel.setAlignment(Align.center);
		textLabel.setWrap(true);
		
		controlLabel.setAlignment(Align.center);
		controlLabel.setWrap(true);

		game.table.add().row().fill(true, false).expandX()
				.padBottom(50.0f * MyGame.scaleY).padBottom(200.0f * MyGame.scaleY);
		game.table.add(controlLabel).row().fill(true, false).padBottom(25.0f * MyGame.scaleY);
		game.table.add(textLabel).row();
		game.table.add(backButton);

		game.table.padTop(header.getHeight() * 1.2f * MyGame.scaleY);
	}
}
