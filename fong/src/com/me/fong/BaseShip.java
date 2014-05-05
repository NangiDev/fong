package com.me.fong;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class BaseShip extends CollidableComponent {
	private boolean alive;
	private boolean isFacingDown;
	private float health;
	private float healthModifier;
	private boolean healthModified;
	private float speed;
	private float speedModifier;
	private float fireRate;
	private float fireRateModifier;
	private float projectileInterval = 0;
	private ArrayList<PowerUps> powerUps;
	protected ActivePowerUp powerUp = ActivePowerUp.None;
	private Animation disposeAnimation;
	public BaseShip(SpriteBatch batch, Texture texture, float x, float y,
			EntityManager entityManager, boolean ignoreLighting, boolean isFacingDown) {
		super(batch, texture, x, y, entityManager, ignoreLighting);
		this.alive = true;
		this.powerUps = new ArrayList<PowerUps>();
		this.healthModifier = 1;
		this.fireRate = 50;
		this.fireRateModifier = 1;
		this.speed = 500;
		this.speedModifier = 1;
		this.isFacingDown = isFacingDown;
		healthModified = false;
		//randomizePowerUps();
	}

	@Override
	public void tick(float delta) {
		super.tick(delta);
		projectileInterval -= delta * 100;
		updatePowerUps();
		fireProjectile();
	}
	
	
	@Override
	public void onCollision(Object o){
		if(o instanceof Projectile && ((Projectile)o).getProjectileParent() == this.getID())
			return;

		disposeAnimation = new Animation(getSpriteBatch(), Assets.explosion, getOrigoX(), getY(), 2.5f, 3.0f, getEntityManager(), Assets.explosionSound);
		super.onCollision(o);
	}
	
	/*private void randomizePowerUps() {
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
		
		System.out.println(powerUp);
		
	}*/
	
	private void updatePowerUps(){
		fireRateModifier = PowerUps.getFireBehavior(powerUp);
		speedModifier = PowerUps.getMovementBehavior(powerUp);
		healthModifier = PowerUps.getHealthBehavior(powerUp);
	}

	public void setAlive(Boolean alive) {
		this.alive = alive;
	}

	public boolean getAlive() {
		return this.alive;
	}

	public void addPowerUp(PowerUps pwu) {
		this.powerUps.add(pwu);
	}

	public ArrayList<PowerUps> getPowerUpList() {
		return this.powerUps;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setPowerUp(ActivePowerUp powerUp){
		this.powerUp = powerUp;
	}
	
	public float getHealth() {
		if(!healthModified){
			healthModified = true;
			return this.health * healthModifier;
		}			
		else
			return this.health;
	}

	public float getSpeed() {
		return this.speed * speedModifier;
	}

	public float getFireRate() {
		return this.fireRate * fireRateModifier;
	}

	public ActivePowerUp getPowerUp(){
		return this.powerUp;
	}
	
	public void fireProjectile() {
		
		if (projectileInterval < 0) {
			Projectile projectile = new Projectile(getSpriteBatch(),
					Assets.laserRed, getOrigoX() - Assets.laserRed.getWidth()
							* 0.5f * MyGame.scaleX, getOrigoY() - Assets.laserRed.getHeight()
							* 0.5f * MyGame.scaleY, this.getEntityManager(), isFacingDown, this.getID());
			projectileInterval = getFireRate();
		}
	}
	
	@Override
	public void dispose(){
		super.dispose();
	}
}
