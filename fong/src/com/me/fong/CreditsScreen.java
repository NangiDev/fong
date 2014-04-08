package com.me.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class CreditsScreen implements Screen{
	private MyGame game;
	private String creditsText;
	private Texture header;
	private TextButton backButton;
	private Label thanksLabel;
	private Label textLabel;

	public CreditsScreen(MyGame myGame) {
		this.game = myGame;
		this.header = new Texture(Gdx.files.internal("menu/credits.png"));
		System.out.println("new CreditsScreen created");
		this.creditsText = "Ray Larabie at www.1001fonts.com for fonts\n\n"
				+ "Kenney at www.kenney.nl for graphics\n";
	}

	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
	}
	
	public void update(float delta){
	}
	
	public void draw(float delta){
		game.batch.begin();
		
		game.drawBackground();

		game.batch.draw(header, (game.screenWidth * 0.5f)
				- (header.getWidth() * 0.5f * game.scaleX),
				game.screenHeight * 0.7f, header.getWidth()
						* game.scaleX, header.getHeight()
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

	private void setupMenuLayout() {
		thanksLabel = new Label("Thanks to!", game.mediumlabelStyle);

		textLabel = new Label(creditsText, game.smalllabelStyle);
		textLabel.setAlignment(Align.center);
		textLabel.setWrap(true);
		
		backButton = new MenuButton("Back", game.mediumButtonStyle, GameState.MainMenu, game);
		
		game.table.add().row().padBottom(25.0f * game.scaleY).expandX();
		game.table.add(thanksLabel).row().padBottom(25.0f * game.scaleY).fill(true, false);
		game.table.add(textLabel).row().padBottom(25.0f * game.scaleY);
		game.table.add(backButton).row().padBottom(25.0f * game.scaleY);

		game.table.padTop(header.getHeight() * 1.5f * game.scaleY);
	}
}
