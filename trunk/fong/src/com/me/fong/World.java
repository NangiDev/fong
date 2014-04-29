package com.me.fong;

public class World {
	private MyGame game;
	private Player player;
	private Ai ai;
	private EntityManager entityManager;

	public World(MyGame myGame) {
		this.game = myGame;
		this.entityManager = new EntityManager(game);
				
		player = new Player(game.batch,Assets.playerShip2_red, MyGame.screenWidth*0.5f, MyGame.screenHeight*0.2f, entityManager, false);
		entityManager.addEntity(player);
		
		ai = new Ai(game.batch,Assets.meteorGray1, MyGame.screenWidth*0.5f, MyGame.screenHeight, entityManager, false);
		entityManager.addEntity(ai);
	}
	
	public void tick(float delta){
		entityManager.tick(delta);
	}
}
