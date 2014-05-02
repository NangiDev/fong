package com.me.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class CollidableComponent extends Shadable{
	
	//private BoundingBox boundingBox;
	private Vector3 minVec, maxVec;
	private Circle circle;
	
	
	public CollidableComponent(SpriteBatch batch, Texture texture, float x, float y, EntityManager entityManager, boolean ignoreLighting){
		super(batch, texture, x, y, entityManager, ignoreLighting);
		//circle = new Circle(getOrigoX(), getOrigoY(), 10);
		//boundingBox = new BoundingBox();
	}
	
	public boolean intersectsWith(CollidableComponent b){
		this.circle = getCircle();
		if(this.circle.overlaps(b.getCircle())){
			return prefectPixelDetection(b);
		}
		return false;
	}
	
	private boolean prefectPixelDetection(CollidableComponent b){
		return true;
	}
	
	public Circle getCircle(){
		return new Circle(getOrigoX(), getOrigoY(), getTexture().getHeight() > getTexture().getWidth() ? getTexture().getWidth()*0.5f*MyGame.scaleX:getTexture().getHeight()*0.5f*MyGame.scaleY);
	}
	
	/*public BoundingBox getBoundingBox(){
	
		minVec = new Vector3(getX(), getY(), 0);
		maxVec = new Vector3(getX()+getTexture().getWidth()*MyGame.scaleX, getY()+getTexture().getHeight()*MyGame.scaleY, 0.1f);
			
		return boundingBox.set(minVec, maxVec);
	}*/
	
	public void onCollision(Object o){
	}
}