package com.me.fong;

public class World {
	private MyGame game;
	private Player player;
	private Ai ai;
	private Meteor ob;

	public World(MyGame myGame) {
		this.game = myGame;
				
		player = new Player(game.batch,Assets.playerShip2_red, MyGame.screenWidth*0.5f, MyGame.screenHeight*0.2f, game.entityManager, false);
		ai = new Ai(game.batch,Assets.enemyBlue4, MyGame.screenWidth*0.5f, MyGame.screenHeight, game.entityManager, false);
		ob = new Meteor(game.batch, Assets.meteorGray, MyGame.screenWidth*0.8f, MyGame.screenHeight, game.entityManager, false);
	}
}
