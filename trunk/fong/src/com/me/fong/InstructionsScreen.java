package com.me.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class InstructionsScreen implements Screen{
	private MyGame game;
	private Texture header;
	private TextButton backButton;

	public InstructionsScreen(MyGame myGame) {
		this.game = myGame;
		this.header = new Texture(Gdx.files.internal("menu/instructions.png"));
		System.out.println("new InstructionsScreen created");
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

		backButton = new MenuButton("Back", game.mediumButtonStyle, GameState.MainMenu, game);
		game.table.add(backButton);

		game.table.padTop(header.getHeight() * 1.5f * game.scaleY);
	}
}
