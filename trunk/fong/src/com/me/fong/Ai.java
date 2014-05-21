package com.me.fong;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Ai extends BaseShip {
	private Random rand = new Random();
	private Animation disposeAnimation;

	public Ai(SpriteBatch batch, Texture texture, float x, float y,
			EntityManager entityManager, boolean ignoreLighting) {
		super(batch, texture, x, y, entityManager, ignoreLighting, true);
		randomizePowerUps();
		setHealth(1);
		setIsPlayer(false);
	}

	@Override
	public void onTick(float delta) {
		setY(getY() - getSpeed() * delta * MyGame.scaleY);
		
		if (getY() > MyGame.screenHeight
				|| getY() < 0 - (getTexture().getHeight() * MyGame.scaleY)
				|| getX() > MyGame.screenWidth || getX() < 0) {
			/*int n = rand.nextInt((int) (MyGame.screenWidth - getTexture()
					.getWidth() * MyGame.scaleX)) + 1;
			setX(n);
			setY(MyGame.screenHeight);*/
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
				dispose();
				/*setHealth(1);
				int n = rand.nextInt((int) MyGame.screenWidth) + 1;
				setX(n);
				setY(MyGame.screenHeight);
				randomizePowerUps();*/
				MyGame.score += 100;
			}
		}
		if (o instanceof Player) {
			dispose();
		}
	}
	
	private void randomizePowerUps() {
		int powerUpId = MathUtils.random(0, 3);
		
		switch(powerUpId){
			case 0: 
				powerUp = ActivePowerUp.None;
				setTexture(Assets.enemyBlack1);
				break;
			case 1: 
				powerUp = ActivePowerUp.FastFire;
				setTexture(Assets.enemyBlack3);
				break;	
			case 2:
				powerUp = ActivePowerUp.FastMovement;
				setTexture(Assets.enemyBlack2);
				break;
			case 3:
				powerUp = ActivePowerUp.Shield;
				setTexture(Assets.enemyBlack4);
				break;
		}
		
		//System.out.println(powerUp);
		
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
