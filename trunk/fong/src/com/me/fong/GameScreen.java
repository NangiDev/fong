package com.me.fong;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class GameScreen implements Screen{
	private World world;
	private MyGame game;
	private TextButton pauseButton;

	public GameScreen(MyGame myGame) {
		this.game = myGame;
		System.out.println("new GameScreen created");
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
		game.fontLarge.draw(game.batch, "GAME!", game.screenWidth*0.2f, game.screenHeight*0.8f);
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
	
	private void setupMenuLayout(){
		pauseButton = new MenuButton("Pause", game.smallButtonStyle, GameState.Pause, game);
		
		game.table.add().row().padBottom(25.0f * game.scaleY);
		game.table.add(pauseButton);
	}
}
