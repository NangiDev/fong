package com.me.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends BaseShip {

	private boolean invincible = false;
	private float invincibleTime;
	private static float origoX;
	private static float origoY;

	public Player(SpriteBatch batch, Texture texture, float x, float y,
			EntityManager entityManager, boolean ignoreLighting) {
		super(batch, texture, y, y, entityManager, ignoreLighting, false);
		setSpeed(800);
		setHealth(2);
		setIsPlayer(true);
	}

	@Override
	public void onTick(float delta) {
		float oldPosX = getX();
		origoX = getOrigoX();
		origoY = getOrigoY();

		if (Gdx.input.isTouched()
				&& Gdx.input.getY() > MyGame.screenHeight * 0.1f
						* MyGame.scaleY) {
			if (Gdx.input.getX() > getX() + getTexture().getWidth() * 0.5f
					* MyGame.scaleX + 10.0f * MyGame.scaleX) {
				setX(getX() + getSpeed() * delta * MyGame.scaleX);
			}

			else if (Gdx.input.getX() < getX() + getTexture().getWidth() * 0.5f
					* MyGame.scaleX - 10.0f * MyGame.scaleX) {
				setX(getX() - getSpeed() * delta * MyGame.scaleX);
			}
		} else {

			if (Gdx.input.isKeyPressed(Keys.RIGHT)
					|| Gdx.input.isKeyPressed(Keys.D)) {
				setX(getX() + getSpeed() * delta * MyGame.scaleX);
			}

			if (Gdx.input.isKeyPressed(Keys.LEFT)
					|| Gdx.input.isKeyPressed(Keys.A)) {
				setX(getX() - getSpeed() * delta * MyGame.scaleX);
			}
		}

		MyGame.backgroundStrafe -= (getX() - oldPosX) * 0.5f;

		if (getX() < 0)
			setX(0);
		if (getX() > MyGame.screenWidth - getTexture().getWidth()
				* MyGame.scaleX)
			setX(MyGame.screenWidth - getTexture().getWidth() * MyGame.scaleX);

		if (((System.nanoTime() - invincibleTime) / 1000000) > 1000.0f) {
			invincible = false;
		}

		if (invincible && ((System.nanoTime() / 1000000) % 2) == 0) {
			ignoreDraw = !ignoreDraw;
		} else {
			ignoreDraw = false;
		}

		// System.out.println(getHealth());
	}

	@Override
	public void onCollision(Object o) {
		super.onCollision(o);
		if (o instanceof PowerUpPickup) {
			PowerUpPickup p = (PowerUpPickup) o;
			powerUp = p.getPowerUp();
			updatePowerUps();
			updateTexture();
		}

		if (!invincible) {
			if (o instanceof Projectile
					&& ((Projectile) o).getProjectileParent() != getIsPlayer()) {
				// setHealth(getHealth() - 1);
				invincible = true;
				invincibleTime = System.nanoTime();
				if (getHealth() <= 0)
					dispose();
			}
			if (o instanceof Ai || o instanceof Meteor) {
				// setHealth(getHealth() - 1);
				invincible = true;
				invincibleTime = System.nanoTime();
				if (getHealth() <= 0)
					dispose();
			}
		}
	}

	public void updateTexture() {
		switch (powerUp) {
		case FastFire:
			setTexture(Assets.playerShip1_red);
			break;
		case FastMovement:
			setTexture(Assets.playerShip2_red);
			break;
		case Shield:
			setTexture(Assets.playerShip3_red);
			break;
		}
		setNormal(getTexture());
	}

	public boolean getInvincible() {
		return this.invincible;
	}

	@Override
	public void dispose() {
		super.dispose();
		getEntityManager().game.switchToScreen(GameState.GameOver);
	}

	public static Vector2 getPlayerPos() {
		return new Vector2(origoX, origoY);
	}
}
