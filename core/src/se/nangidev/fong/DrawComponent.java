package se.nangidev.fong;

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
		if (!ignoreDraw && getY() < FongMain.screenHeight) {
			batch.draw(texture, getX(), getY(), texture.getWidth()
					* FongMain.scaleX, texture.getHeight() * FongMain.scaleY);
			
		}
	}

	public float getOrigoX() {
		return getX() + getTexture().getWidth() * 0.5f * FongMain.scaleX;
	}

	public float getOrigoY() {
		return getY() + getTexture().getHeight() * 0.5f * FongMain.scaleY;
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
