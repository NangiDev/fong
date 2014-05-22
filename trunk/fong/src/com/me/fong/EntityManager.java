package com.me.fong;

import java.util.ArrayList;

public class EntityManager {

	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private Player player;
	private int ID = 0;
	public ShaderManager shaderManager;
	public MyGame game;
	private ArrayList<Entity> ticks;

	public EntityManager(MyGame game) {
		this.game = game;
		this.shaderManager = new ShaderManager();
	}

	public void tick(float delta) {
		//System.out.println("Entities size: " + entities.size());
		ticks = new ArrayList<Entity>(entities);
		if(MyGame.lightOn)
			shaderManager.switchToNormalShader(game.batch);
		
		//ShaderManager.tick(delta);
		shaderManager.passLights();

		for (int i = 0; i < ticks.size(); i++) {
			for (int j = i + 1; j < ticks.size(); j++) {
				if (ticks.get(i) instanceof CollidableComponent
						&& ticks.get(j) instanceof CollidableComponent) {
					if (((CollidableComponent) ticks.get(i))
							.intersectsWith((CollidableComponent) ticks.get(j))) {
						CollidableComponent a = (CollidableComponent) ticks
								.get(i);
						CollidableComponent b = (CollidableComponent) ticks
								.get(j);
						a.onCollision(b);
						b.onCollision(a);
					}
				}
			}
			if(ticks.get(i) instanceof Shadable && MyGame.lightOn){
				((Shadable) ticks.get(i)).bind();
			}
			
			if (ticks.get(i) instanceof DrawComponent) {
				((DrawComponent) ticks.get(i)).draw();
			}

			ticks.get(i).tick(delta);
		}
		shaderManager.switchToDefaultShader(game.batch);
	}

	public void addEntity(Entity e) {
		try {
			entities.add(0, e);
			if (e instanceof Player)
				this.player = (Player) e;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void addToEndEntity(Entity e) {
		try {
			entities.add(e);
			if (e instanceof Player)
				this.player = (Player) e;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void removeEntity(Entity e) {
		try {
			entities.remove(e);
			e = null;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public boolean entityExists(Entity e){
		if(entities.contains(e)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void clearEntityList(){
		this.entities.clear();
	}
	
	public ArrayList<Entity> getCurrentState(){
		return ticks;
	}

	public Player getPlayer() {
		return this.player;
	}

	public int getNewID() {
		this.ID += 1;
		return ID;
	}
}