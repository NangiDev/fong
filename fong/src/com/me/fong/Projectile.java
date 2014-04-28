package com.me.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Projectile extends CollidableComponent{
	
	private EntityManager entityManager;
	
	public Projectile(SpriteBatch batch, Texture texture, float x, float y, EntityManager entityManager){
		setTexture(texture);
		setSpriteBatch(batch);
		setX(x);
		setY(y);
		this.entityManager = entityManager;
	}
	
	@Override
	public void onTick(float delta){
		setY(getY() + 500 * delta * MyGame.scaleY);
		
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
		
		if(o instanceof Projectile || o instanceof Player)
			return;
		
		dispose();
	}
}
