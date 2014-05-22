package com.me.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class BaseShip extends CollidableComponent {
	private boolean alive;
	private boolean isFacingDown;
	private boolean isPlayer;
	private float health;
	private float healthModifier;
	private boolean healthModified;
	private float speed;
	private float speedModifier;
	private float fireRate;
	private float fireRateModifier;
	private float projectileInterval = 0;
	protected EnumPowerUp powerUp = EnumPowerUp.None;
	private Animation disposeAnimation;

	public BaseShip(SpriteBatch batch, Texture texture, float x, float y,
			EntityManager entityManager, boolean ignoreLighting,
			boolean isFacingDown) {
		super(batch, texture, x, y, entityManager, ignoreLighting);
		this.alive = true;
		this.healthModifier = 1;
		this.fireRate = 50;
		this.fireRateModifier = 1;
		this.speed = 300;
		this.speedModifier = 1;
		this.isFacingDown = isFacingDown;
		healthModified = false;
	}

	@Override
	public void tick(float delta) {
		super.tick(delta);
		projectileInterval -= delta * 100;
		updatePowerUps();
		//fireProjectile();
	}

	@Override
	public void onCollision(Object o) {
		if (o instanceof Projectile
				&& ((Projectile) o).getProjectileParent() == isPlayer)
			return;
		if(!(o instanceof PowerUpPickup)){
			disposeAnimation = new Animation(getSpriteBatch(), Assets.explosion,
					getOrigoX(), getY(), 2.5f, 3.0f, getEntityManager(),
					Assets.explosionSound);
			getEntityManager().addEntity(disposeAnimation);
		}
		super.onCollision(o);
	}

	protected void updatePowerUps() {
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

	public void setHealth(float health) {
		this.health = health;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setPowerUp(EnumPowerUp powerUp) {
		this.powerUp = powerUp;
	}

	public float getHealth() {
		if (!healthModified) {
			healthModified = true;
			return this.health * healthModifier;
		} else
			return this.health;
	}

	public float getSpeed() {
		return this.speed * speedModifier;
	}

	public float getFireRate() {
		return this.fireRate * fireRateModifier;
	}

	public EnumPowerUp getPowerUp() {
		return this.powerUp;
	}

	public void setIsPlayer(boolean isPlayer) {
		this.isPlayer = isPlayer;
	}

	public boolean getIsPlayer() {
		return this.isPlayer;
	}

	public void fireProjectile() {

		if (projectileInterval < 0) {
			Projectile projectile = new Projectile(getSpriteBatch(),
					Assets.laserRed, getOrigoX() - Assets.laserRed.getWidth()
							* 0.5f * MyGame.scaleX, getOrigoY()
							- Assets.laserRed.getHeight() * 0.5f
							* MyGame.scaleY, this.getEntityManager(),
					isFacingDown, isPlayer);
			projectileInterval = getFireRate();
		}
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
