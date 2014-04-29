package com.me.fong;

public class World {
	private MyGame game;
	private Player player;
	private Ai ai;
	private EntityManager entityManager;
	private LightSource sun;

	public World(MyGame myGame) {
		this.game = myGame;
		this.entityManager = new EntityManager(game);
				
		player = new Player(game.batch,Assets.cockpitBlue_2, MyGame.screenWidth*0.5f, MyGame.screenHeight*0.2f, entityManager);
		entityManager.addEntity(player);
		
		ai = new Ai(game.batch,Assets.cockpitBlue_0, MyGame.screenWidth*0.5f, MyGame.screenHeight, entityManager);
		entityManager.addEntity(ai);
		
		sun = new LightSource(MyGame.screenWidth + 500 , MyGame.screenHeight / 2, game.batch);
		sun.setSunLight();
		
		entityManager.addEntity(sun);
	}
	
	public void tick(float delta){
		entityManager.tick(delta);
	}
}
