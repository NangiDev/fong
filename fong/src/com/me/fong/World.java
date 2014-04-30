package com.me.fong;

public class World {
	private MyGame game;
	private Player player;
	private Ai ai;
	//private EntityManager entityManager;

	public World(MyGame myGame) {
		this.game = myGame;
		//this.entityManager = new EntityManager(game);
				
		player = new Player(game.batch,Assets.playerShip2_red, MyGame.screenWidth*0.5f, MyGame.screenHeight*0.2f, game.entityManager, false);
		game.entityManager.addEntity(player);
		
		ai = new Ai(game.batch,Assets.enemyBlue4, MyGame.screenWidth*0.5f, MyGame.screenHeight, game.entityManager, false);
		game.entityManager.addEntity(ai);
	}
	
	public void tick(float delta){
		game.entityManager.tick(delta);
		game.score += (int)(1 * delta * 100);
	}
}
