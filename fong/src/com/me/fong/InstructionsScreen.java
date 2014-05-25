package com.me.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class InstructionsScreen implements Screen {
	private MyGame game;
	private Shadable header;
	private TextButton backButton;
	private String instructionsText;
	private String controlText;
	private Label textLabel;
	private Label controlLabel;
	private float playerX;
	private Shadable player;

	public InstructionsScreen(MyGame myGame) {
		this.game = myGame;

		this.instructionsText = "Your goal is to stay alive!\n\n"
				+ "It is easier if you pick up power-ups\n\n"
				+ "Gain points by surviving, collect power-ups and kill as many foes as possible!\n\n"
				+ "Exterminate whole waves of foes gives extra credits!";
		this.controlText = "Touch the Screen to control the ship!";

		textLabel = new Label(instructionsText, game.smalllabelStyle);
		controlLabel = new Label(controlText, game.smalllabelStyle);
		backButton = new MenuButton("Back", game.mediumButtonStyle,
				GameState.MainMenu, game);

		System.out.println("new InstructionsScreen created");
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

		if (Gdx.input.isTouched()
				&& Gdx.input.getY() > MyGame.screenHeight * 0.1f
						* MyGame.scaleY) {
			if (Gdx.input.getX() > player.getX()
					+ player.getTexture().getWidth() * 0.5f * MyGame.scaleX
					+ 10.0f * MyGame.scaleX)
				player.setX(player.getX() + 800 * delta * MyGame.scaleX);

			else if (Gdx.input.getX() < player.getX()
					+ player.getTexture().getWidth() * 0.5f * MyGame.scaleX
					- 10.0f * MyGame.scaleX)
				player.setX(player.getX() - 800 * delta * MyGame.scaleX);
		}

		if (player.getX() < 0)
			player.setX(0);
		if (player.getX() > MyGame.screenWidth - player.getTexture().getWidth()
				* MyGame.scaleX)
			player.setX(MyGame.screenWidth - player.getTexture().getWidth()
					* MyGame.scaleX);

		game.entityManager.tick(delta);
	}

	public void draw(float delta) {

		game.table.draw(game.batch, 1);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		this.header = new Shadable(
				game.batch,
				Assets.instructions,
				(MyGame.screenWidth * 0.5f)
						- (Assets.instructions.getWidth() * 0.5f * MyGame.scaleX),
				MyGame.screenHeight * 0.7f, game.entityManager, false);
		game.entityManager.addEntity(header);
		playerX = MyGame.screenWidth * 0.5f
				- Assets.playerShip1_orange.getWidth() * 0.5f * MyGame.scaleX;
		player = new Shadable(game.batch, Assets.playerShip1_orange, playerX,
				MyGame.screenHeight * 0.5f, game.entityManager, false);
		game.entityManager.addEntity(player);
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

	public void setupMenuLayout() {

		textLabel.setAlignment(Align.center);
		textLabel.setWrap(true);

		controlLabel.setAlignment(Align.center);
		controlLabel.setWrap(true);

		game.table.add().row().fill(true, false).expandX()
				.padBottom(50.0f * MyGame.scaleY)
				.padBottom(180.0f * MyGame.scaleY);
		game.table.add(controlLabel).row().fill(true, false)
				.padBottom(25.0f * MyGame.scaleY);
		game.table.add(textLabel).row();
		game.table.add(backButton);

		game.table.padTop(header.getTexture().getHeight() * 1.2f * MyGame.scaleY);
	}
}
