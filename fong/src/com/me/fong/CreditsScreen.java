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

	public CreditsScreen(MyGame myGame) {
		this.game = myGame;
		this.header = new Texture(Gdx.files.internal("menu/credits.png"));
		System.out.println("new CreditsScreen created");
		this.creditsText = "Ray Larabie at www.1001fonts.com for the font\n"
				+ "Kenney at www.kenney.nl for the graphics\n";
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

	public void setupMenuLayout() {
		String temp = "";
		thanksLabel = new Label(creditsText, game.mediumlabelStyle);
		thanksLabel.setFillParent(true);
		thanksLabel.setAlignment(Align.center);
		thanksLabel.setWrap(true);
		backButton = new MenuButton("Back", game.mediumButtonStyle, GameState.MainMenu, game);
		
		game.table.add(thanksLabel);
		
		while(creditsText.length() != 0){
			temp = creditsText.substring(0, creditsText.indexOf("\n")+1);
			creditsText = creditsText.replace(temp.toString(), "");
			game.table.add(new Label(temp.trim(), game.smalllabelStyle)).row();
		}
		
		game.table.add(backButton).row();
		game.table.padTop(header.getHeight() * 1.5f);
	}
}
