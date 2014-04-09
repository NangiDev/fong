package com.me.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class OptionScreen implements Screen{
	private MyGame game;
	private Texture header;
	private TextButton backButton;
	private TextButton toggleMusicButton;
	private TextButton toggleSoundFxButton;
	private TextButton toggleLightFxButton;
	private Table innerTable;
	private Label music;
	private Label soundFx;
	private Label lightFx;
	
	public OptionScreen(MyGame myGame) {
		this.game = myGame;
		this.header = new Texture(Gdx.files.internal("menu/options.png"));
		this.toggleMusicButton = new MenuButton(" ON", game.mediumButtonStyle, true);
		this.toggleSoundFxButton = new MenuButton(" ON", game.mediumButtonStyle, true);
		this.toggleLightFxButton = new MenuButton(" ON", game.mediumButtonStyle, true);
		this.innerTable = new Table(game.skin);
		music = new Label("Music", game.mediumlabelStyle);
		soundFx = new Label("Sound", game.mediumlabelStyle);
		lightFx = new Label("Light", game.mediumlabelStyle);
		backButton = new MenuButton("Back", game.mediumButtonStyle, GameState.MainMenu, game);
		
		System.out.println("new OptionScreen created");
	}

	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
	}
	
	public void update(float delta){
		if (Gdx.input.isKeyPressed(Keys.BACK)){
			game.switchToScreen(GameState.MainMenu);
		}
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
		innerTable.clearChildren();
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
				
		innerTable.add().row().padBottom(25.0f * game.scaleY);
		innerTable.add(music).align(Align.left).padRight(100.0f * game.scaleX);
		innerTable.add(toggleMusicButton).align(Align.right).row().padBottom(25.0f * game.scaleY);
		
		innerTable.add(soundFx).align(Align.left).padRight(100.0f * game.scaleX);
		innerTable.add(toggleSoundFxButton).align(Align.right).row().padBottom(25.0f * game.scaleY);
		
		innerTable.add(lightFx).align(Align.left).padRight(100.0f * game.scaleX);
		innerTable.add(toggleLightFxButton).align(Align.center).row().padBottom(25.0f * game.scaleY);

		game.table.add(innerTable).row();
		game.table.add(backButton);

		game.table.padTop(header.getHeight() * 1.5f * game.scaleY);
	}
}
