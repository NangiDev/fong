package com.me.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Projectile extends CollidableComponent{
	
	private EntityManager entityManager;
	private boolean orientation;
	
	public Projectile(SpriteBatch batch, Texture texture, float x, float y, EntityManager entityManager, boolean orientation){
		super(batch, texture, x, y, entityManager, true);
		this.entityManager = entityManager;
		this.orientation = orientation;
	}
	
	@Override
	public void onTick(float delta){
		if(orientation)
			setY(getY() - 1000 * delta * MyGame.scaleY);
		else
			setY(getY() + 1000 * delta * MyGame.scaleY);
		
		if(getY() > MyGame.screenHeight || getY() < 0 || getX() > MyGame.screenWidth || getX() < 0){
			this.dispose();
		}
	}
	
	@Override
	public void dispose(){
		super.dispose();
		entityManager.removeEntity(this);
	}
	
	@Override
	public void onCollision(Object o){
		super.onCollision(o);
		if(o instanceof Projectile)
			return;
		dispose();
	}
}
