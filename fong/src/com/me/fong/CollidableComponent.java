package com.me.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class CollidableComponent extends Shadable{
	
	private Vector3 minimum, maximum;
	private BoundingBox wings;
	private BoundingBox front;
	
	
	public CollidableComponent(SpriteBatch batch, Texture texture, float x, float y, EntityManager entityManager, boolean ignoreLighting){
		super(batch, texture, x, y, entityManager, ignoreLighting);
	}
	
	public boolean intersectsWith(CollidableComponent b){		
		this.wings = getWings();
		if(this.wings.intersects(b.getWings())){
			return true;
		}
		this.front = getFront();
		if(this.front.intersects(b.getFront())){
			return true;
		}
		return false;
	}
	
	public BoundingBox getWings(){
		minimum = new Vector3(getX(), getY() + getTexture().getHeight() * 0.6f *MyGame.scaleY, 0);
		maximum = new Vector3(getX() + getTexture().getWidth() * MyGame.scaleX, getY() + getTexture().getHeight() * 0.4f * MyGame.scaleY, 0.1f);
		return new BoundingBox(minimum, maximum);
	}
	
	public BoundingBox getFront(){
		minimum = new Vector3(getX() + getTexture().getWidth() * 0.4f * MyGame.scaleX, getY(), 0);
		maximum = new Vector3(getX() + getTexture().getWidth() * 0.6f * MyGame.scaleX, getY() + getTexture().getHeight() * MyGame.scaleY, 0.1f);
		return new BoundingBox(minimum, maximum);
	}
	
	public void onCollision(Object o){
	}
}