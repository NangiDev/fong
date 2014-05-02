package com.me.fong;

public class World {
	private MyGame game;
	private Player player;
	private Ai ai;

	public World(MyGame myGame) {
		this.game = myGame;
				
		player = new Player(game.batch,Assets.playerShip2_red, MyGame.screenWidth*0.5f, MyGame.screenHeight*0.2f, game.entityManager, false);
		ai = new Ai(game.batch,Assets.enemyBlue4, MyGame.screenWidth*0.5f, MyGame.screenHeight, game.entityManager, false);
	}
}
