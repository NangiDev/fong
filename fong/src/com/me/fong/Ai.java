package com.me.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Ai extends BaseShip {

	private AiControllers aiC;
	private EnumAiControllers controllerType;
	private int level = 0;

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
				PowerUpPickup p = new PowerUpPickup(getSpriteBatch(), Assets.pill_green, getOrigoX(), getOrigoY(), getEntityManager(), false, getPowerUp());
				dispose();
				MyGame.score += 100;
			}
		}
		if (o instanceof Player && !((Player) o).getInvincible()) {
			disposeAnimation();
			dispose();
		}
	}
	
	public void randomizePowerUps() {
		int powerUpId = MathUtils.random(1, 3);
		switch(powerUpId){
			/*case 0: 
				powerUp = EnumPowerUp.None;
				setTexture(Assets.enemyBlack1);
				break;*/
			case 1: 
				powerUp = EnumPowerUp.FastFire;
				if(level == 0)
					setTexture(Assets.enemyBlue3);
				if(level == 1)
					setTexture(Assets.enemyGreen3);
				if(level == 2)
					setTexture(Assets.enemyRed3);
				if(level == 3)
					setTexture(Assets.enemyBlack3);
				break;	
			case 2:
				powerUp = EnumPowerUp.FastMovement;
				if(level == 0)
					setTexture(Assets.enemyBlue2);
				if(level == 1)
					setTexture(Assets.enemyGreen2);
				if(level == 2)
					setTexture(Assets.enemyRed2);
				if(level == 3)
					setTexture(Assets.enemyBlack2);
				break;
			case 3:
				powerUp = EnumPowerUp.Shield;
				if(level == 0)
					setTexture(Assets.enemyBlue4);
				if(level == 1)
					setTexture(Assets.enemyGreen4);
				if(level == 2)
					setTexture(Assets.enemyRed4);
				if(level == 3)
					setTexture(Assets.enemyBlack4);
				break;
		}
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public void setLevel(int levelCount) {
		this.level  = levelCount;
	}
}
