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
	private Texture tutorialShip;
	private TextButton backButton;
	private String instructionsText;
	private String controlText;
	private Label textLabel;
	private Label controlLabel;
	private float shipXpos;

	public InstructionsScreen(MyGame myGame) {
		this.game = myGame;
		this.header = new Texture(Gdx.files.internal("menu/instructions.png"));
		this.tutorialShip = new Texture(
				Gdx.files.internal("playerShip2_blue.png"));
		this.shipXpos = game.screenWidth * 0.5f - tutorialShip.getWidth()
				* 0.5f * game.scaleX;
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
		if (Gdx.input.isTouched()) {
			if (Gdx.input.getX() >  shipXpos + tutorialShip.getWidth() * 0.5f * game.scaleX + 5)
				shipXpos += 500 * delta;
			else if(Gdx.input.getX() <  shipXpos + tutorialShip.getWidth() * 0.5f * game.scaleX - 5)
				shipXpos -= 500 * delta;
		}

		if (Gdx.input.isKeyPressed(Keys.BACK)) {
			game.switchToScreen(GameState.MainMenu);
		}

		if (shipXpos < 0)
			shipXpos = 0;
		if (shipXpos > game.screenWidth - tutorialShip.getWidth() * game.scaleX)
			shipXpos = game.screenWidth - tutorialShip.getWidth() * game.scaleX;
	}

	public void draw(float delta) {
		game.batch.begin();

		game.drawBackground();

		game.batch.draw(header, (game.screenWidth * 0.5f)
				- (header.getWidth() * 0.5f * game.scaleX),
				game.screenHeight * 0.7f, header.getWidth() * game.scaleX,
				header.getHeight() * game.scaleY);

		game.batch.draw(tutorialShip, shipXpos, game.screenHeight * 0.5f,
				tutorialShip.getWidth() * game.scaleX, tutorialShip.getHeight()
						* game.scaleY);

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

	public void setupMenuLayout() {

		textLabel.setAlignment(Align.center);
		textLabel.setWrap(true);
		
		controlLabel.setAlignment(Align.center);
		controlLabel.setWrap(true);

		game.table.add().row().fill(true, false).expandX()
				.padBottom(50.0f * game.scaleY).padBottom(200.0f * game.scaleY);
		game.table.add(controlLabel).row().fill(true, false).padBottom(25.0f * game.scaleY);
		game.table.add(textLabel).row();
		game.table.add(backButton);

		game.table.padTop(header.getHeight() * 1.2f * game.scaleY);
	}
}
