package com.me.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Projectile extends CollidableComponent{
	
	private EntityManager entityManager;
	private boolean orientation;
	private int parent;
	
	public Projectile(SpriteBatch batch, Texture texture, float x, float y, EntityManager entityManager, boolean orientation, int parent){
		super(batch, texture, x, y, entityManager, true);
		this.entityManager = entityManager;
		this.orientation = orientation;
		this.parent = parent;
		if(MyGame.soundOn)
			Assets.laserSound.play(0.5f);
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
	}
	
	@Override
	public void onCollision(Object o){
		super.onCollision(o);
		if(o instanceof Projectile || o instanceof PowerUps)
			return;
		if(o instanceof Player && parent == ((Player)o).getID()){
			return;
		}
		if(o instanceof Ai && parent == ((Ai)o).getID()){
			return;
		}
		dispose();
	}
	
	public int getProjectileParent(){
		return this.parent;
	}
}
