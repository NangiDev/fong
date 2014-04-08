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
	private Stage stage;
	private Skin skin;
	private Table table;
	private Texture logotypeTexture;
	private TextButton backButton;
	private Label HighscoreLabel;
	private Label name1;
	private Label name2;
	private Label name3;
	private Label name4;
	private Label name5;

	public HighscoreScreen(MyGame myGame) {
		this.game = myGame;
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
		table.draw(game.batch, 1);
		game.batch.draw(logotypeTexture, (game.screenWidth * 0.5f)
				- (logotypeTexture.getWidth() * 0.5f * game.scaleX),
				game.screenHeight * 0.75f, logotypeTexture.getWidth()
						* game.scaleX, logotypeTexture.getHeight()
						* game.scaleY);

		//table.drawDebug(stage);
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		setupManuLayout();
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		logotypeTexture.dispose();
		stage.dispose();
		skin.dispose();
	}

	public void setupManuLayout() {
		this.stage = new Stage();
		this.skin = new Skin();
		this.table = new Table(skin);

		Gdx.input.setInputProcessor(stage);
		
		logotypeTexture = new Texture(Gdx.files.internal("menu/highscore.png"));

		skin.add("fontMedium", game.fontMedium);
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = skin.getFont("fontMedium");
		skin.add("default", textButtonStyle);

		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = skin.getFont("fontMedium");
		skin.add("default", labelStyle);

		table.setFillParent(true);
		table.top();
		table.padTop(game.screenHeight*0.3f);
		stage.addActor(table);

		HighscoreLabel = new Label("Highscore", skin);
		name1 = new Label("1, ", skin);
		name2 = new Label("2, ", skin);
		name3 = new Label("3, ", skin);
		name4 = new Label("4, ", skin);
		name5 = new Label("5, ", skin);
		backButton = game.setupButton("Back", skin, GameState.MainMenu);
		
		table.add().row().align(Align.left);
		table.add(name1).row().align(Align.left);
		table.add(name2).row().align(Align.left);
		table.add(name3).row().align(Align.left);
		table.add(name4).row().align(Align.left);
		table.add(name5).row().align(Align.center).expandY();
		table.add(backButton);
		
		table.debug();
	}

}
