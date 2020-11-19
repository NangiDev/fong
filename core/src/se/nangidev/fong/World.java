package se.nangidev.fong;

public class World {
	private FongMain game;
	private se.nangidev.fong.Player player;
	private WaveManager wm;

	public World(FongMain FongMain) {
		this.game = FongMain;
		wm = new WaveManager(this.game);
		
		player = new Player(game.batch, Assets.playerShip1_red,
				FongMain.screenWidth * 0.5f, FongMain.screenHeight * 0.2f,
				game.entityManager, false);
		game.entityManager.addEntity(player);
	}

	public void tick(float delta) {
		wm.tick(delta);
	}
}
