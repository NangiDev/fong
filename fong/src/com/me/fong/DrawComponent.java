package com.me.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DrawComponent extends Entity {

	private Texture texture;
	private SpriteBatch batch;
	protected boolean ignoreDraw = false;

	public DrawComponent(SpriteBatch batch, Texture texture, float x, float y,
			EntityManager entityManager) {
		super(x, y, entityManager);
		this.texture = texture;
		this.batch = batch;
	}

	public void draw() {
		if (!ignoreDraw) {
			batch.draw(texture, getX(), getY(), texture.getWidth()
					* MyGame.scaleX, texture.getHeight() * MyGame.scaleY);
			
		}
	}

	public float getOrigoX() {
		return getX() + getTexture().getWidth() * 0.5f * MyGame.scaleX;
	}

	public float getOrigoY() {
		return getY() + getTexture().getHeight() * 0.5f * MyGame.scaleY;
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
}
