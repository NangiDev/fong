package com.me.fong;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class World {
	private MyGame game;
	private Player player;
	private Ai ai;
	private EntityManager entityManager;
	private LightSource sun;

	public World(MyGame myGame) {
		SpriteBatch newBatch = new SpriteBatch();
		this.game = myGame;
		this.entityManager = new EntityManager(game);
				
		player = new Player(game.batch,Assets.enemyBlue1, MyGame.screenWidth*0.5f, MyGame.screenHeight*0.2f, entityManager, false);
		entityManager.addEntity(player);
		
		//ai = new Ai(game.batch,Assets.enemyBlue3, MyGame.screenWidth*0.5f, MyGame.screenHeight, entityManager);
		//entityManager.addEntity(ai);
		
		//sun = new LightSource(MyGame.screenWidth + 500 , MyGame.screenHeight / 2, game.batch);
		//sun.setSunLight();
		
		//entityManager.addEntity(sun);
	}
	
	public void tick(float delta){
		entityManager.tick(delta);
	}
}
