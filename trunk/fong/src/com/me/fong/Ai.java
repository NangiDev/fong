package com.me.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Ai extends BaseShip {

	private AiControllers aiC;
	private EnumAiControllers controllerType;
	private int level = 0;

	public Ai(SpriteBatch batch, Texture texture, float x, float y,
			EntityManager entityManager, boolean ignoreLighting,
			EnumAiControllers controllerType) {
		super(batch, texture, x, y, entityManager, ignoreLighting, true);
		setHealth(1.0f);
		setIsPlayer(false);
		setFireRate(100);
		aiC = new AiControllers(this);
		this.controllerType = controllerType;
	}

	public void updateAiController() {
		aiC.updateAiC();
	}

	@Override
	public void onTick(float delta) {
		aiC.controller(controllerType, delta);
		
		if (getY() + getTexture().getHeight() < 0) {
			this.dispose();
		}
	}

	@Override
	public void onCollision(Object o) {
		
		if (o instanceof Meteor || o instanceof Ai) {
			return;
		}
		super.onCollision(o);
		if (o instanceof Projectile
				&& ((Projectile) o).getProjectileParent() != getIsPlayer()) {
			setHealth(getHealth() - 1.0f);
			
			if (getHealth() <= 0) {
				int rand = MathUtils.random(0, 9);
				if (rand == 0) {
					PowerUpPickup p = new PowerUpPickup(getSpriteBatch(),
							Assets.pill_green, getOrigoX(), getOrigoY(),
							getEntityManager(), false, getPowerUp());
				}
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
		//powerUpId = 3;
		// System.out.println("Level: " + level);
		switch (powerUpId) {
		case 1:
			powerUp = EnumPowerUp.FastFire;
			if (level == 0) {
				setTexture(Assets.enemyBlue3);
			}
			if (level == 1) {
				setTexture(Assets.enemyGreen3);
			}
			if (level == 2) {
				setTexture(Assets.enemyRed3);
			}
			if (level == 3) {
				setTexture(Assets.enemyBlack3);
			}
			/*
			 * if (level == 4) { randomizeColor(powerUp); }
			 */
			break;
		case 2:
			powerUp = EnumPowerUp.FastMovement;
			if (level == 0) {
				setTexture(Assets.enemyBlue2);
			}
			if (level == 1) {
				setTexture(Assets.enemyGreen2);
			}
			if (level == 2) {
				setTexture(Assets.enemyRed2);
			}
			if (level == 3) {
				setTexture(Assets.enemyBlack2);
			}
			/*
			 * if (level == 4) { randomizeColor(powerUp); }
			 */
			break;
		case 3:
			powerUp = EnumPowerUp.Shield;
			if (level == 0) {
				setTexture(Assets.enemyBlue4);
			}
			if (level == 1) {
				setTexture(Assets.enemyGreen4);
			}
			if (level == 2) {
				setTexture(Assets.enemyRed4);
			}
			if (level == 3) {
				setTexture(Assets.enemyBlack4);
			}
			/*
			 * if (level == 4) { randomizeColor(powerUp); }
			 */
			break;
			
		}
		updatePowerUps();
		
	}

	public void randomizeColor(EnumPowerUp powerup) {
		int colorInt = MathUtils.random(0, 3);
		// System.out.println("Randomize color");
		switch (powerup) {
		case FastFire:
			if (colorInt == 0) {
				setTexture(Assets.enemyBlack3);
			}
			if (colorInt == 1) {
				setTexture(Assets.enemyBlue3);
			}
			if (colorInt == 2) {
				setTexture(Assets.enemyRed3);
			}
			if (colorInt == 3) {
				setTexture(Assets.enemyGreen3);
			}
			break;
		case FastMovement:
			if (colorInt == 0) {
				setTexture(Assets.enemyBlack2);
			}
			if (colorInt == 1) {
				setTexture(Assets.enemyBlue2);
			}
			if (colorInt == 2) {
				setTexture(Assets.enemyRed2);
			}
			if (colorInt == 3) {
				setTexture(Assets.enemyGreen2);
			}
			break;
		case Shield:
			if (colorInt == 0) {
				setTexture(Assets.enemyBlack4);
			}
			if (colorInt == 1) {
				setTexture(Assets.enemyBlue4);
			}
			if (colorInt == 2) {
				setTexture(Assets.enemyRed4);
			}
			if (colorInt == 3) {
				setTexture(Assets.enemyGreen4);
			}
			break;
		}

	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public void setLevel(int levelCount) {
		this.level = levelCount;
	}
}
