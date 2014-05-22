package com.me.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Ai extends BaseShip {

	private AiControllers aiC;
	private EnumAiControllers controllerType;

	public Ai(SpriteBatch batch, Texture texture, float x, float y,
			EntityManager entityManager, boolean ignoreLighting, EnumAiControllers controllerType) {
		super(batch, texture, x, y, entityManager, ignoreLighting, true);
		setHealth(1);
		setIsPlayer(false);
		setFireRate(100);
		aiC = new AiControllers(this);
		this.controllerType = controllerType;
	}

	@Override
	public void onTick(float delta) {
		aiC.controller(controllerType, delta);
		
		if (getY() < 0 - (getTexture().getHeight() * MyGame.scaleY)
				|| getX() > MyGame.screenWidth || getX() < 0) {
			this.dispose();
		}
	}

	@Override
	public void onCollision(Object o) {
		if (o instanceof Meteor || o instanceof Ai) {
			return;
		}
		super.onCollision(o);
		if (o instanceof Projectile && ((Projectile)o).getProjectileParent() != getIsPlayer()) {
			setHealth(getHealth() - 1);
			if (getHealth() <= 0) {
				PowerUpPickup p = new PowerUpPickup(getSpriteBatch(), Assets.pill_green, getOrigoX(), getOrigoY(), getEntityManager(), false);
				dispose();
				MyGame.score += 100;
			}
		}
		if (o instanceof Player) {
			
			dispose();
		}
	}
	
	public void randomizePowerUps() {
		int powerUpId = MathUtils.random(0, 3);
		
		switch(powerUpId){
			case 0: 
				powerUp = EnumPowerUp.None;
				setTexture(Assets.enemyBlack1);
				break;
			case 1: 
				powerUp = EnumPowerUp.FastFire;
				setTexture(Assets.enemyBlack3);
				break;	
			case 2:
				powerUp = EnumPowerUp.FastMovement;
				setTexture(Assets.enemyBlack2);
				break;
			case 3:
				powerUp = EnumPowerUp.Shield;
				setTexture(Assets.enemyBlack4);
				break;
		}
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
