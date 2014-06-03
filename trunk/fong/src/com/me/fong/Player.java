package com.me.fong;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends BaseShip {

	private boolean invincible = false;
	private float invincibleTime;
	public boolean lifeHasChanged = false;
	private static float origoX;
	private static float origoY;
	public static int fireLevel = 0;
	public static int healthBars = 0;
	private ArrayList<Shadable> lifebar = new ArrayList<Shadable>();

	public Player(SpriteBatch batch, Texture texture, float x, float y,
			EntityManager entityManager, boolean ignoreLighting) {
		super(batch, texture, y, y, entityManager, ignoreLighting, false);
		setSpeed(800);
		setHealth(1.0f);
		setIsPlayer(true);
		setSpread(1);
		//updateFireLevel();
	}

	@Override
	public void onTick(float delta) {
		float oldPosX = getX();

		origoX = getOrigoX();
		origoY = getOrigoY();

		if (lifeHasChanged) {
			for (Shadable e : lifebar) {
				if(getEntityManager().entityExists(e)){
					getEntityManager().removeEntity(e);
				}
			}
			lifebar.clear();
			
			for (int i = 0; i < Player.healthBars; i++) {
				Shadable lifeBar = new Shadable(getSpriteBatch(), Assets.playerLife,
						0, MyGame.screenHeight - Assets.playerLife.getHeight()
								* 1.1f * MyGame.scaleY, getEntityManager(), true);
				lifeBar.setX(MyGame.screenWidth * 0.5f
						- lifeBar.getTexture().getWidth() * MyGame.scaleX + i
						* lifeBar.getTexture().getWidth() * MyGame.scaleX);
				lifebar.add(lifeBar);
				getEntityManager().addEntity(lifeBar);
			}
			lifeHasChanged = false;
		}

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
			if (powerUp == EnumPowerUp.FastFire) {
				fireLevel++;
				updateFireLevel();
			} else if (powerUp == EnumPowerUp.Shield) {
				if (healthBars < 3) {
					healthBars += 1;
					lifeHasChanged = true;
				}else{
					MovingLabel points = new MovingLabel("+ 100", getSpriteBatch(), getEntityManager().game, getTexture(), getOrigoX()-MyGame.screenWidth*0.5f, getY(), getEntityManager(), true, EnumAiControllers.None);
					points.setLabelStyle(getEntityManager().game.smalllabelStyle);
					points.setLifeTime(500.0f);
					getEntityManager().addEntity(points);
					MyGame.score += 100;
				}
			}
			// updatePowerUps();
			// updateTexture();
		}

		if (!invincible) {
			if (o instanceof Projectile
					&& ((Projectile) o).getProjectileParent() != getIsPlayer()) {
				healthBars -= 1;
				lifeHasChanged = true;
				invincible = true;
				invincibleTime = System.nanoTime();
				if (healthBars < 0){
					disposeAnimation();
					dispose();
				}
				if (fireLevel > 1) {
					fireLevel = fireLevel - 1;
				} else {
					fireLevel = 0;
				}
				updateFireLevel();
			}
			if (o instanceof Ai) {
				healthBars -= 1;
				lifeHasChanged = true;
				invincible = true;
				invincibleTime = System.nanoTime();
				if (healthBars < 0){
					disposeAnimation();
					dispose();
				}
				if (fireLevel > 1) {
					fireLevel = fireLevel - 1;
				} else {
					fireLevel = 0;
				}
				updateFireLevel();
			}
		}
	}

	public void updateFireLevel() {
		if (fireLevel % 2 == 0) {
			setSpread(fireLevel/2 + 1);
			//System.out.println("Spread: " + getSpread());
		} else {
			int tempFireLevel;
			if(fireLevel == 1)
				tempFireLevel = 1;
			else
				tempFireLevel = fireLevel - 1;
			setFireRate(15 + 25 * (1.0f/((float)tempFireLevel * 1.2f)));
		}
	}

	public void updateTexture() {
		switch (powerUp) {
		case FastFire:
			setTexture(Assets.playerShip1_red);
			break;
		case FastMovement:
			setTexture(Assets.playerShip1_red);
			break;
		case Shield:
			setTexture(Assets.playerShip1_red);
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
		MyGame.difficulty = 1.0f;
		fireLevel = 0;
		healthBars = 0;
		getEntityManager().game.switchToScreen(GameState.GameOver);
	}

	public static Vector2 getPlayerPos() {
		return new Vector2(origoX, origoY);
	}
}
