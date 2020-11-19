package se.nangidev.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

public class HighscoreScreen implements Screen {
	private FongMain game;
	private Shadable header;
	private TextButton backButton;
	private Label name1;
	private Label name2;
	private Label name3;
	private Label name4;
	private Label name5;

	public HighscoreScreen(FongMain FongMain) {
		this.game = FongMain;

		backButton = new MenuButton("Back", game.mediumButtonStyle,
				GameState.MainMenu, game);

		System.out.println("new HighscoreScreen created");
	}

	@Override
	public void render(float delta) {
		game.batch.begin();
		game.drawBackground(delta);
		update(delta);
		draw(delta);
		game.batch.end();
	}

	public void update(float delta) {
		if (Gdx.input.isKeyPressed(Keys.BACK)) {
			game.switchToScreen(GameState.MainMenu);
		}
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
		this.header = new Shadable(game.batch, Assets.highscore, (FongMain.screenWidth * 0.5f)
				- (Assets.highscore.getWidth() * 0.5f * FongMain.scaleX),
				FongMain.screenHeight * 0.7f, game.entityManager, false);
		game.entityManager.addEntity(header);
		updateHighscore();
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

	private void updateHighscore() {
		name1 = new Label(game.highscoreManager.printScore(0),
				game.mediumlabelStyle);
		name2 = new Label(game.highscoreManager.printScore(1),
				game.mediumlabelStyle);
		name3 = new Label(game.highscoreManager.printScore(2),
				game.mediumlabelStyle);
		name4 = new Label(game.highscoreManager.printScore(3),
				game.mediumlabelStyle);
		name5 = new Label(game.highscoreManager.printScore(4),
				game.mediumlabelStyle);
	}

	private void setupMenuLayout() {

		game.table.add().align(Align.left).padBottom(25.0f * FongMain.scaleY).row();
		game.table.add(name1).align(Align.left)
				.padBottom(25.0f * FongMain.scaleY).row();
		game.table.add(name2).align(Align.left)
				.padBottom(25.0f * FongMain.scaleY).row();
		game.table.add(name3).align(Align.left)
				.padBottom(25.0f * FongMain.scaleY).row();
		game.table.add(name4).align(Align.left)
				.padBottom(25.0f * FongMain.scaleY).row();
		game.table.add(name5).align(Align.center)
				.padBottom(25.0f * FongMain.scaleY).row();
		game.table.add(backButton).align(Align.center).row();

		game.table.padTop(header.getTexture().getHeight() * 1.5f * FongMain.scaleY);
	}
}
