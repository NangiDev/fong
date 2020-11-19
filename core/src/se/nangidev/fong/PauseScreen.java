package se.nangidev.fong;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class PauseScreen implements Screen{
	private FongMain game;
	private Shadable header;
	private TextButton resumeButton;
	private TextButton exitButton;

	public PauseScreen(FongMain Fong) {
		this.game = Fong;
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
		this.header = new Shadable(game.batch, Assets.pause, (FongMain.screenWidth * 0.5f)
				- (Assets.pause.getWidth() * 0.5f * FongMain.scaleX),
				FongMain.screenHeight * 0.7f, game.entityManager, false);
		game.entityManager.addEntity(header);
		resumeButton = new se.nangidev.fong.MenuButton("Resume", game.mediumButtonStyle, se.nangidev.fong.GameState.Game, game);
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
		
		game.table.add().padBottom(100.0f * FongMain.scaleY);
		game.table.add(resumeButton).padBottom(25.0f * FongMain.scaleY);
		game.table.add(exitButton);

		game.table.padTop(header.getTexture().getHeight() * 1.5f * FongMain.scaleY);
	}

}
