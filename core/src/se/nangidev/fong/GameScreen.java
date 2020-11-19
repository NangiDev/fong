package se.nangidev.fong;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class GameScreen implements Screen {

	private FongMain game;
	private TextButton pauseButton;
	private Label scoreLabel;
	private boolean ignoreTicks = false;
	private World world;
	private ArrayList<Entity> pauseGameState;

	public GameScreen(FongMain FongMain) {
		this.game = FongMain;
		setupGUI();
		System.out.println("new GameScreen created");
		pauseGameState = new ArrayList<Entity>();
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
			game.switchToScreen(GameState.Pause);
		}
		if (!ignoreTicks) {
			game.entityManager.tick(delta);
			world.tick(delta);
			game.score += (int) (1 * delta * 100);
		}
	}

	public void draw(float delta) {
		pauseButton.draw(game.batch, 1);
		scoreLabel.setText(("000000" + game.score).substring(("" + game.score)
				.length()));
		scoreLabel.draw(game.batch, 1);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		if (pauseGameState.size() <= 0)
			this.world = new World(game);
		else {
			for (Entity e : pauseGameState) {
				game.entityManager.addEntity(e);
			}
		}
		this.ignoreTicks = false;
		scoreLabel.setText(("000000" + game.score).substring(("" + game.score)
				.length()));
		game.stage.addActor(pauseButton);
	}

	@Override
	public void hide() {
		pauseGameState = game.entityManager.getCurrentState();
		game.entityManager.clearEntityList();
		game.score = Integer.parseInt(scoreLabel.getText().toString());
		game.stage.getRoot().removeActor(pauseButton);
	}

	@Override
	public void pause() {
		this.ignoreTicks = true;
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

	private void setupGUI() {
		pauseButton = new MenuButton("Pause", game.mediumButtonStyle,
				GameState.Pause, game);

		pauseButton.setPosition((FongMain.screenWidth) - pauseButton.getWidth(),
				FongMain.screenHeight - pauseButton.getHeight());

		scoreLabel = new Label("000000", game.mediumlabelStyle);
		scoreLabel.setPosition(0, FongMain.screenHeight - scoreLabel.getHeight());
	}
}
