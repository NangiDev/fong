package com.me.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class CollidableComponent extends Shadable{
	
	private BoundingBox boundingBox;
	private Vector3 minVec, maxVec;
	
	public CollidableComponent(SpriteBatch batch, Texture texture, float x, float y, EntityManager entityManager, boolean ignoreLighting){
		super(batch, texture, x, y, entityManager, ignoreLighting);
		boundingBox = new BoundingBox();
	}
	
	public boolean intersectsWith(CollidableComponent b){
		this.boundingBox = getBoundingBox();
		if(this.boundingBox.intersects(b.getBoundingBox())){
			return true;
		}
		return false;
	}
	
	public BoundingBox getBoundingBox(){
	
		minVec = new Vector3(getX(), getY(), 0);
		if(orientation)
			maxVec = new Vector3(getX()+getTexture().getWidth()*MyGame.scaleX, getY()+getTexture().getHeight()*MyGame.scaleY, 0.1f);
		else
			maxVec = new Vector3(getX()+getTexture().getWidth()*MyGame.scaleX, getY()-getTexture().getHeight()*MyGame.scaleY, 0.1f);
			
		return boundingBox.set(minVec, maxVec);
	}
	
	public void onCollision(Object o){
	}
}