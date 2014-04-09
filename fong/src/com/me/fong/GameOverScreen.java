package com.me.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.Input.Keys;


public class GameOverScreen implements Screen {
	private MyGame game;
	private Texture header;
	private TextField nameField;
	private TextFieldStyle tFStyle;
	
	public GameOverScreen(MyGame myGame) {
		this.game = myGame;
		this.header = new Texture(Gdx.files.internal("menu/GameOver.png"));
		System.out.println("new GameOverScreen created");
	}

	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
	}

	public void update(float delta) {
		if (Gdx.input.isKeyPressed(Keys.BACK)) {
			game.switchToScreen(GameState.MainMenu);
		}
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
	
	private void setupMenuLayout(){
		TextFieldStyle tFStyle = new TextFieldStyle();
		tFStyle.fontColor = game.myDarkGreen;
		tFStyle.font = game.fontMedium;
		
		
		nameField = new TextField("Name", tFStyle); 
	
		nameField.setColor(game.myYellow);
		game.table.add().row().padBottom(25.0f * game.scaleY);
		game.table.add(nameField);
		
		game.table.padTop(header.getHeight() * 1.5f);
	}
}
