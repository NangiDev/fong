package com.me.fong;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Meteor extends CollidableComponent{
	private Random rand = new Random();
	private Animation disposeAnimation;
	
	public Meteor(SpriteBatch batch, Texture texture, float x, float y,
			EntityManager entityManager, boolean ignoreLighting) {
		super(batch, texture, x, y, entityManager, ignoreLighting);
	}
	

	@Override
	public void onTick(float delta) {
		setY(getY() - 300 * delta * MyGame.scaleY);
		
		if (getY() > MyGame.screenHeight
				|| getY() < 0 - (getTexture().getHeight() * MyGame.scaleY)
				|| getX() > MyGame.screenWidth || getX() < 0) {
			int n = rand.nextInt((int) (MyGame.screenWidth - getTexture()
					.getWidth() * MyGame.scaleX)) + 1;
			setX(n);
			setY(MyGame.screenHeight);
			// this.dispose();
		}
	}

	@Override
	public void onCollision(Object o) {
		if(o instanceof Projectile){
			disposeAnimation = new Animation(getSpriteBatch(), Assets.meteorExplosion, getOrigoX(), getY(), 2.0f, 8.0f, getEntityManager(), Assets.explosionSound);
			int n = rand.nextInt((int) MyGame.screenWidth) + 1;
			setX(n);
			setY(MyGame.screenHeight);
			MyGame.score += 100;
			//dispose();
		}
			
		super.onCollision(o);
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
