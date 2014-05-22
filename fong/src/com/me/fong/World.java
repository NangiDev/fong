package com.me.fong;

public class World {
	private MyGame game;
	private Player player;
	private WaveManager wm;

	public World(MyGame myGame) {
		this.game = myGame;
		wm = new WaveManager(this.game);
		player = new Player(game.batch, Assets.playerShip2_red,
				MyGame.screenWidth * 0.5f, MyGame.screenHeight * 0.2f,
				game.entityManager, false);
		game.entityManager.addEntity(player);
	}

	public void tick(float delta) {
		wm.tick(delta);
	}
}
