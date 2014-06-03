package com.me.fong;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class EntityManager {

	private ArrayList<Entity> entities = new ArrayList<Entity>();
	public ArrayList<Projectile> playerProjectiles = new ArrayList<Projectile>();
	private ArrayList<Entity> ticks;
	public ArrayList<Projectile> tempPlayerProjectiles;
	private Player player;
	private int ID = 0;
	public ShaderManager shaderManager;
	public MyGame game;

	private boolean almighty = false;
	private boolean slutanuro = true;

	public EntityManager(MyGame game) {
		this.game = game;
		this.shaderManager = new ShaderManager();
	}

	public void tick(float delta) {
		// System.out.println("Entities size: " + entities.size());
		ticks = new ArrayList<Entity>(entities);
		if (MyGame.lightOn)
			shaderManager.switchToNormalShader(game.batch);

		if (Gdx.input.isKeyPressed(Keys.J) && slutanuro) {
			almighty = !almighty;
			if (almighty)
				System.out.println("GODMODE ON!!");
			else
				System.out.println("NOBODYMODE ON!!");
			slutanuro = false;
		}

		if (!Gdx.input.isKeyPressed(Keys.J)) {
			slutanuro = true;
		}

		// shaderManager.passLights();

		for (int i = 0; i < ticks.size(); i++) {
			Entity iTick = ticks.get(i);
			/*
			 * if (!almighty && iTick.getY() < MyGame.screenHeight &&
			 * iTick.getY() > 0) { for (int j = i + 1; j < ticks.size(); j++) {
			 * if (iTick instanceof CollidableComponent && ticks.get(j)
			 * instanceof CollidableComponent) {
			 * if(!checkSameInstance(iTick,ticks.get(j)) &&
			 * checkVincinity(iTick, ticks.get(j))){ if (((CollidableComponent)
			 * iTick) .intersectsWith((CollidableComponent) ticks .get(j))) {
			 * CollidableComponent a = (CollidableComponent) iTick;
			 * CollidableComponent b = (CollidableComponent) ticks .get(j);
			 * a.onCollision(b); b.onCollision(a); } } } } }
			 */

			if (iTick instanceof Shadable && MyGame.lightOn
					&& iTick.getY() < MyGame.screenHeight) {
				shaderManager.switchToNormalShader(game.batch);
				shaderManager.sortLightsByDistance(new Vector2(
						((Shadable) iTick).getX(), ((Shadable) iTick).getY()));
				shaderManager.passLights();
				shaderManager.switchToDefaultShader(game.batch);
				((Shadable) iTick).bind();
			}

			if (iTick instanceof DrawComponent
					&& iTick.getY() < MyGame.screenHeight) {
				((DrawComponent) iTick).draw();
			}

			iTick.tick(delta);
		}
		shaderManager.switchToDefaultShader(game.batch);
		shaderManager.updateLightRotation();
	}

	private boolean checkVincinity(Entity a, Entity b) {
		if (a.getY() >= MyGame.screenHeight * 0.5
				&& b.getY() >= MyGame.screenHeight * 0.5)
			return true;
		else if (a.getY() <= MyGame.screenHeight * 0.5
				&& b.getY() <= MyGame.screenHeight * 0.5)
			return true;
		else
			return false;
	}

	private boolean checkSameInstance(Entity a, Entity b) {
		if (a instanceof Ai && b instanceof Ai)
			return true;
		else if (a instanceof Projectile && b instanceof Projectile)
			return true;
		else
			return false;
	}

	public void setDefaultSunPosition() {
		shaderManager.setDefaultSunPosition();
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

	public boolean entityExists(Entity e) {
		if (entities.contains(e)) {
			return true;
		} else {
			return false;
		}
	}

	public void clearEntityList() {
		this.entities.clear();
	}

	public ArrayList<Entity> getCurrentState() {
		return ticks;
	}

	public Player getPlayer() {
		return this.player;
	}

	public int getNewID() {
		this.ID += 1;
		return ID;
	}

	public void checkCollisionWithPlayer(CollidableComponent b) {
		if (!almighty && player.intersectsWith(b)) {
			player.onCollision(b);
			b.onCollision(player);
		}
	}

	public void checkIfHitByPlayerProjectile(Ai ai) {
		if (!almighty) {
			tempPlayerProjectiles = new ArrayList<Projectile>(playerProjectiles);
			for (Projectile p : tempPlayerProjectiles) {
				if (p.intersectsWith(ai)) {
					p.onCollision(ai);
					ai.onCollision(p);
				}
			}
		}
	}
}
