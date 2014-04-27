package com.me.fong;

public class World {
	private MyGame game;
	private Player player;
	private EntityManager entityManger;

	public World(MyGame myGame) {
		this.game = myGame;
		this.entityManger = new EntityManager();
		
		player = new Player(game.batch,Assets.playerShipTexture, 100, 100);
		entityManger.addEntity(player);
	}
	
	public void tick(float delta){
		entityManger.tick(delta);
	}
}
