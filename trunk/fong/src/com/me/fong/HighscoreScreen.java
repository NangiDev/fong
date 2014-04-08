package com.me.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class HighscoreScreen implements Screen {
	private MyGame game;
	private Texture header;
	private TextButton backButton;
	private Label name1;
	private Label name2;
	private Label name3;
	private Label name4;
	private Label name5;

	public HighscoreScreen(MyGame myGame) {
		this.game = myGame;
		System.out.println("new HighscoreScreen created");
		header = new Texture(Gdx.files.internal("menu/highscore.png"));
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
		name1 = new Label("1, ", game.mediumlabelStyle);
		name2 = new Label("2, ", game.mediumlabelStyle);
		name3 = new Label("3, ", game.mediumlabelStyle);
		name4 = new Label("4, ", game.mediumlabelStyle);
		name5 = new Label("5, ", game.mediumlabelStyle);
		backButton = new MenuButton("Back", game.mediumButtonStyle, GameState.MainMenu, game);
		
		game.table.add().row().align(Align.left).padBottom(25.0f * game.scaleY);
		game.table.add(name1).row().align(Align.left).padBottom(25.0f * game.scaleY);
		game.table.add(name2).row().align(Align.left).padBottom(25.0f * game.scaleY);
		game.table.add(name3).row().align(Align.left).padBottom(25.0f * game.scaleY);
		game.table.add(name4).row().align(Align.left).padBottom(25.0f * game.scaleY);
		game.table.add(name5).row().align(Align.left).padBottom(25.0f * game.scaleY);
		game.table.add(backButton).row().align(Align.center);

		game.table.padTop(header.getHeight() * 1.5f * game.scaleY);
	}
}
