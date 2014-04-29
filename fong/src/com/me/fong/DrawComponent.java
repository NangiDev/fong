package com.me.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DrawComponent extends Entity {

	private Texture texture;
	private SpriteBatch batch;
	public boolean orientation = false;
	private float origoX, origoY;
	
	public DrawComponent(SpriteBatch batch, Texture texture, float x, float y){
		super(x, y);
		this.texture = texture;
		this.batch = batch;
	}

	public void draw() {
		if (orientation)
			batch.draw(texture, getX(), getY(), texture.getWidth()
					* MyGame.scaleX, texture.getHeight() * MyGame.scaleY);
		else
			batch.draw(texture, getX(), getY(), texture.getWidth()
					* MyGame.scaleX, -texture.getHeight() * MyGame.scaleY);
		
		this.origoX = getX() + getTexture().getWidth() * 0.5f * MyGame.scaleX;
		this.origoY = getY() + getTexture().getHeight() * 0.5f * MyGame.scaleY;
	}

	public float getOrigiX() {
		return this.origoX;
	}

	public float getOrigoY() {
		return this.origoY;
	}

	public void setSpriteBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public SpriteBatch getSpriteBatch() {
		return this.batch;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Texture getTexture() {
		return this.texture;
	}

	public void setOrientation(boolean orientation) {
		this.orientation = orientation;
	}
}
