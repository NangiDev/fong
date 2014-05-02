package com.me.fong;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BaseShip extends CollidableComponent {
	private boolean alive;
	private boolean isFacingDown;
	private float health;
	private float healthModifier;
	private float speed;
	private float speedModifier;
	private float fireRate;
	private float fireRateModifier;
	private float projectileInterval = 0;
	private ArrayList<PowerUps> powerUps;
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
		randomizePowerUps();
	}

	@Override
	public void tick(float delta) {
		super.tick(delta);
		projectileInterval -= delta * 100;
		fireProjectile();
	}
	
	@Override
	public void onCollision(Object o){
		super.onCollision(o);
		if(o instanceof Projectile && ((Projectile)o).getProjectileParent() == this.getID())
			return;

		disposeAnimation = new Animation(getSpriteBatch(), Assets.explosion, getOrigoX(), getY(), 2.5f, 3.0f, getEntityManager(), Assets.explosionSound);
	}

	private void randomizePowerUps() {
		// Här ska powerups skapas och läggas i listan så att modifierarna kan
		// ändras.
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

	public float getHealth() {
		return this.health * healthModifier;
	}

	public float getSpeed() {
		return this.speed * speedModifier;
	}

	public float getFireRate() {
		return this.fireRate * fireRateModifier;
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
