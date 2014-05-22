package com.me.fong;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class PauseScreen implements Screen{
	private MyGame game;
	private Shadable header;
	private TextButton resumeButton;
	private TextButton exitButton;

	public PauseScreen(MyGame myGame) {
		this.game = myGame;
		System.out.println("new PauseScreen created");
	}

	@Override
	public void render(float delta) {
		game.batch.begin();
		game.drawBackground(0);
		update(delta);
		draw(delta);
		game.batch.end();
	}

	public void update(float delta) {
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
		this.header = new Shadable(game.batch, Assets.pause, (MyGame.screenWidth * 0.5f)
				- (Assets.pause.getWidth() * 0.5f * MyGame.scaleX),
				MyGame.screenHeight * 0.7f, game.entityManager, false);
		game.entityManager.addEntity(header);
		resumeButton = new MenuButton("Resume", game.mediumButtonStyle, GameState.Game, game);
		exitButton = new MenuButton("Exit", game.mediumButtonStyle, GameState.MainMenu, game);
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
	
	private void setupMenuLayout(){
		
		game.table.add().row().padBottom(100.0f * MyGame.scaleY);
		game.table.add(resumeButton).row().padBottom(25.0f * MyGame.scaleY);
		game.table.add(exitButton);

		game.table.padTop(header.getTexture().getHeight() * 1.5f * MyGame.scaleY);
	}

}
