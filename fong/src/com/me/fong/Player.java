package com.me.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends BaseShip {

	public Player(SpriteBatch batch, Texture texture, float x, float y, EntityManager entityManager, boolean ignoreLighting) {
		super(batch, texture, y, y, entityManager, ignoreLighting, false);
		setSpeed(800);
		super.setPowerUp(ActivePowerUp.None);
	}

	@Override
	public void onTick(float delta) {
		if (Gdx.input.isTouched() && Gdx.input.getY() > MyGame.screenHeight * 0.1f * MyGame.scaleY) {
			if (Gdx.input.getX() > getX() + getTexture().getWidth() * 0.5f
					* MyGame.scaleX + 10.0f * MyGame.scaleX)
				setX(getX() + getSpeed() * delta * MyGame.scaleX);

			else if (Gdx.input.getX() < getX() + getTexture().getWidth() * 0.5f
					* MyGame.scaleX - 10.0f * MyGame.scaleX)
				setX(getX() - getSpeed() * delta * MyGame.scaleX);
		}

		if (getX() < 0)
			setX(0);
		if (getX() > MyGame.screenWidth - getTexture().getWidth()
				* MyGame.scaleX)
			setX(MyGame.screenWidth - getTexture().getWidth() * MyGame.scaleX);
	}

	@Override
	public void onCollision(Object o) {
		super.onCollision(o);
		if (o instanceof Projectile && ((Projectile)o).getProjectileParent() != this.getID()) {
			setAlive(false);
			dispose();
		}
		if (o instanceof PowerUps){
			//Pickup
		}
		if (o instanceof Ai || o instanceof Meteor){
			dispose();
		}

	}
	
	@Override
	public void dispose(){
		super.dispose();
		getEntityManager().game.switchToScreen(GameState.GameOver);
	}
}
