package com.me.fong;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ai extends BaseShip {
	private int projectileInterval = 0;
	private ArrayList<Entity> projectiles = new ArrayList<Entity>();
	private Random rand = new Random();
	private EntityManager entityManager;

	public Ai(SpriteBatch batch, Texture texture, float x, float y,
			EntityManager entityManager) {
		super(entityManager);
		this.entityManager = entityManager;
		setTexture(texture);
		setSpriteBatch(batch);
		setX(x);
		setY(y);
		setOrientation(false);
		setHealth(3);
	}

	@Override
	public void onTick(float delta) {
		setY(getY() - 500 * delta * MyGame.scaleY);

		if (getY() > MyGame.screenHeight || getY() < 0
				|| getX() > MyGame.screenWidth || getX() < 0) {
			int  n = rand.nextInt((int)MyGame.screenWidth) + 1;
			setX(n);
			setY(MyGame.screenHeight);
			// this.dispose();
		}
	}

	@Override
	public void onCollision(Object o) {
		super.onCollision(o);
		if (o instanceof Projectile && projectiles.contains(((Projectile) o))) {
			return;
		}
		else if (o instanceof Projectile){
			setHealth(getHealth()-1);
			if(getHealth() <= 0){
				//dispose();
				setHealth(3);
				int  n = rand.nextInt((int)MyGame.screenWidth) + 1;
				setX(n);
				setY(MyGame.screenHeight);
			}
		}
		if (o instanceof Player) {
			dispose();
		}
	}
	
	@Override
	public void dispose(){
		super.dispose();
		entityManager.removeEntity(this);
	}

	/*
	 * public void fireProjectile() { Projectile projectile = new
	 * Projectile(getSpriteBatch(), Assets.projectileTexture, getOrigiX() -
	 * Assets.projectileTexture.getWidth() * 0.5f MyGame.scaleX, getY() +
	 * getTexture().getHeight() * MyGame.scaleY); projectiles.add(projectile);
	 * entityManager.addEntity(projectile); projectileInterval = 50;
	 * 
	 * //System.out.println(projectiles.toString()); }
	 */
}
