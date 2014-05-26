package com.me.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

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
	protected EnumPowerUp powerUp = EnumPowerUp.FastMovement;
	private Animation disposeAnimation;
	private float playerLeft, playerRight;
	private int spread = 1;

	public BaseShip(SpriteBatch batch, Texture texture, float x, float y,
			EntityManager entityManager, boolean ignoreLighting,
			boolean isFacingDown) {
		super(batch, texture, x, y, entityManager, ignoreLighting);
		this.alive = true;
		this.healthModifier = 1;
		this.fireRate = 40;
		this.fireRateModifier = 1;
		this.speed = 100;
		this.speedModifier = 1;
		this.isFacingDown = isFacingDown;
		this.healthModified = false;
	}

	@Override
	public void tick(float delta) {
		super.tick(delta);
		projectileInterval -= delta * 100;

		if (getHealth() <= 0) {
			disposeAnimation();
		}

		playerLeft = Player.getPlayerPos().x - getTexture().getWidth();
		playerRight = Player.getPlayerPos().x + getTexture().getWidth();

		if (getOrigoX() >= playerLeft && getOrigoX() <= playerRight) {
			fireProjectile();
		}
	}

	@Override
	public void onCollision(Object o) {
		if (o instanceof Projectile
				&& ((Projectile) o).getProjectileParent() == isPlayer)
			return;
		super.onCollision(o);
	}

	protected void updatePowerUps() {
		fireRateModifier = PowerUps.getFireBehavior(powerUp);
		speedModifier = PowerUps.getMovementBehavior(powerUp);
		health = PowerUps.getHealthBehavior(powerUp);

		// System.out.println("Firerate: " + getFireRate());
		// System.out.println("Speed: " + getSpeed());
		// System.out.println("Health: " + getHealth());
	}
	
	public void setSpread(int spread){
		this.spread = spread;
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

	public void setFireRate(float fireRate) {
		this.fireRate = fireRate;
	}

	public int getSpread(){
		return this.spread;
	}
	
	public float getHealth() {
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
			if(spread > 1){
				for (int i = 0; i < spread; i++) {
					Projectile projectile = new Projectile(getSpriteBatch(),
							Assets.laserGreen, getOrigoX()
									- Assets.laserGreen.getWidth() * 0.5f
									* MyGame.scaleX, getOrigoY()
									- Assets.laserGreen.getHeight() * 0.5f
									* MyGame.scaleY, this.getEntityManager(),
							isFacingDown, isPlayer, new Vector2(-0.2f + i
									* (0.4f / (float) (spread - 1)), 1));
				}
			}
			else{
				Projectile projectile = new Projectile(getSpriteBatch(),
						Assets.laserGreen, getOrigoX()
								- Assets.laserGreen.getWidth() * 0.5f
								* MyGame.scaleX, getOrigoY()
								- Assets.laserGreen.getHeight() * 0.5f
								* MyGame.scaleY, this.getEntityManager(),
						isFacingDown, isPlayer, new Vector2(0, 1));
			}
			if (MyGame.soundOn)
				Assets.laserSound.play(0.2f);
			
			/*Projectile projectile = new Projectile(getSpriteBatch(),
					Assets.laserGreen, getOrigoX()
							- Assets.laserGreen.getWidth() * 0.5f
							* MyGame.scaleX, getOrigoY()
							- Assets.laserGreen.getHeight() * 0.5f
							* MyGame.scaleY, this.getEntityManager(),
					isFacingDown, isPlayer, new Vector2(-0.2f,1));*/
			/*Projectile projectile2 = new Projectile(getSpriteBatch(),
					Assets.laserGreen, getOrigoX()
							- Assets.laserGreen.getWidth() * 0.5f
							* MyGame.scaleX, getOrigoY()
							- Assets.laserGreen.getHeight() * 0.5f
							* MyGame.scaleY, this.getEntityManager(),
					isFacingDown, isPlayer, new Vector2(0,1));*/
			/*Projectile projectile3 = new Projectile(getSpriteBatch(),
					Assets.laserGreen, getOrigoX()
							- Assets.laserGreen.getWidth() * 0.5f
							* MyGame.scaleX, getOrigoY()
							- Assets.laserGreen.getHeight() * 0.5f
							* MyGame.scaleY, this.getEntityManager(),
					isFacingDown, isPlayer, new Vector2(0.2f,1));*/
			projectileInterval = getFireRate();
		}
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public void disposeAnimation() {
		disposeAnimation = new Animation(getSpriteBatch(), Assets.explosion,
				getOrigoX(), getY(), 2.5f, 7.0f, getEntityManager(),
				Assets.explosionSound);
		getEntityManager().addEntity(disposeAnimation);
	}
}
