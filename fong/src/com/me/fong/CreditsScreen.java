package com.me.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class CreditsScreen implements Screen {
	private MyGame game;
	private String creditsText;
	private Shadable header;
	// private Texture header;
	private TextButton backButton;
	private Label thanksLabel;
	private Label textLabel;

	public CreditsScreen(MyGame myGame) {
		this.game = myGame;

		this.creditsText = "Our tutor Mathias Broxwall at Örebro University\n\n"
				+ "Ray Larabie at www.1001fonts.com for fonts\n\n"
				+ "Kenney at www.kenney.nl for graphics\n\n"
				+ "ShawnDaley on SoundCloud  for music";

		thanksLabel = new Label("Thanks!", game.largelabelStyle);
		textLabel = new Label(creditsText, game.smalllabelStyle);
		backButton = new MenuButton("Back", game.mediumButtonStyle,
				GameState.MainMenu, game);

		System.out.println("new CreditsScreen created");
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
		game.table.draw(game.batch, 1);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		this.header = new Shadable(game.batch, Assets.credits,
				(MyGame.screenWidth * 0.5f)
						- (Assets.credits.getWidth() * 0.5f * MyGame.scaleX),
				MyGame.screenHeight * 0.7f, game.entityManager, false);
		game.entityManager.addEntity(header);
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

	private void setupMenuLayout() {
		textLabel.setAlignment(Align.center);
		textLabel.setWrap(true);

		game.table.add().row().padBottom(50.0f * MyGame.scaleY).expandX();
		game.table.add(thanksLabel).row().padBottom(50.0f * MyGame.scaleY)
				.fill(true, false);
		game.table.add(textLabel).row();
		game.table.add(backButton).row();

		game.table.padTop(header.getTexture().getHeight() * 1.2f
				* MyGame.scaleY);
	}
}
