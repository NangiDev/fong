package com.me.fong;

public class World {
	private MyGame game;
	private Player player;
	private Ai ai;
	private EntityManager entityManger;

	public World(MyGame myGame) {
		this.game = myGame;
		this.entityManger = new EntityManager(game);
				
		player = new Player(game.batch,Assets.playerShipTexture, MyGame.screenWidth*0.5f - Assets.playerShipTexture.getWidth()*0.5f*MyGame.scaleX, 100, entityManger);
		entityManger.addEntity(player);
		
		ai = new Ai(game.batch,Assets.playerShipTexture, MyGame.screenWidth*0.5f, MyGame.screenHeight, entityManger);
		entityManger.addEntity(ai);
	}
	
	public void tick(float delta){
		entityManger.tick(delta);
	}
}
