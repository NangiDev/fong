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
		wings = new BoundingBox();
		front = new BoundingBox();
	}
	
	public boolean intersectsWith(CollidableComponent b){		
		this.wings = getWings();
		if(checkIntersection(this, b)){
			return true;
		}
		this.front = getFront();
		if(checkIntersection(this, b)){
			return true;
		}
		return false;
	}
	
	public BoundingBox getWings(){
		minimum = new Vector3(getX(), getY() + getTexture().getHeight() * 0.7f *MyGame.scaleY, 0);
		maximum = new Vector3(getX() + getTexture().getWidth() * MyGame.scaleX, getY() + getTexture().getHeight() * 0.3f * MyGame.scaleY, 0.1f);
		wings.set(minimum, maximum);
		return wings;
	}
	
	public BoundingBox getFront(){
		minimum = new Vector3(getX() + getTexture().getWidth() * 0.4f * MyGame.scaleX, getY(), 0);
		maximum = new Vector3(getX() + getTexture().getWidth() * 0.6f * MyGame.scaleX, getY() + getTexture().getHeight() * MyGame.scaleY, 0.1f);
		front.set(minimum, maximum);
		return front;
	}
	
	private boolean checkIntersection(CollidableComponent a, CollidableComponent b){
		float wx = Math.abs(a.getOrigoX() - b.getOrigoX());
		float sumwx = (a.getTexture().getWidth() * MyGame.scaleX / 2.0f) + (b.getTexture().getWidth() * MyGame.scaleX / 2.0f);

		float wy = Math.abs(a.getOrigoY() - b.getOrigoY());
		float sumwy = (a.getTexture().getHeight() * MyGame.scaleY / 4.0f) + (b.getTexture().getHeight() * MyGame.scaleY / 4.0f);
		
		float fx = Math.abs(a.getOrigoX() - b.getOrigoX());
		float sumfx = (a.getTexture().getWidth() * MyGame.scaleX / 4.0f) + (b.getTexture().getWidth() * MyGame.scaleX / 4.0f);

		float fy = Math.abs(a.getOrigoY() - b.getOrigoY());
		float sumfy = (a.getTexture().getHeight() * MyGame.scaleY / 2.0f) + (b.getTexture().getHeight() * MyGame.scaleY / 2.0f);

		return (wx <= sumwx && wy <= sumwy || fx <= sumfx && fy <= sumfy);
	}
	
	@Override
	public void tick(float delta){
		super.tick(delta);
		if(getY() <= getEntityManager().getPlayer().getY() + getEntityManager().getPlayer().getTexture().getHeight() && getY() > getEntityManager().getPlayer().getY()){
			getEntityManager().checkCollisionWithPlayer(this);
		} 
	}
	
	public void onCollision(Object o){
	}
}