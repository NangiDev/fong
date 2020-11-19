package se.nangidev.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Ai extends BaseShip {

	private se.nangidev.fong.AiControllers aiC;
	private se.nangidev.fong.EnumAiControllers controllerType;
	private int level = 0;

	public Ai(SpriteBatch batch, Texture texture, float x, float y,
			  EntityManager entityManager, boolean ignoreLighting,
			  se.nangidev.fong.EnumAiControllers controllerType) {
		super(batch, texture, x, y, entityManager, ignoreLighting, true);
		setHealth(1.0f);
		setIsPlayer(false);
		setFireRate(100);
		
		this.controllerType = controllerType;
		
	}

	public void updateAiController() {
		aiC.updateAiC(this);
	}

	@Override
	public void onTick(float delta) {
		if(aiC == null)
			aiC = new AiControllers(this);
		aiC.controller(controllerType, delta);
		
		if (getY() + getTexture().getHeight() < 0) {
			this.dispose();
		}
		
		getEntityManager().checkIfHitByPlayerProjectile(this);
	}


	@Override
	public void onCollision(Object o) {
		
		if (o instanceof Ai || o instanceof PowerUpPickup) {
			return;
		}
		super.onCollision(o);
		if (o instanceof se.nangidev.fong.Projectile
				&& ((Projectile) o).getProjectileParent() != getIsPlayer()) {
			setHealth(getHealth() - 1.0f);
			
			if (getHealth() <= 0) {
				int rand = MathUtils.random(0, 9);
				se.nangidev.fong.MovingLabel points = new MovingLabel("200", getSpriteBatch(), getEntityManager().game, getTexture(), getOrigoX()- FongMain.screenWidth*0.5f, getY(), getEntityManager(), true, EnumAiControllers.None);
				points.setLabelStyle(getEntityManager().game.smalllabelStyle);
				points.setLifeTime(500.0f);
				getEntityManager().addEntity(points);
				disposeAnimation();
				dispose();
				FongMain.score += 200;
			}
			else{
				if(FongMain.soundOn)
					se.nangidev.fong.Assets.laserDeflectSound.play(0.4f);
				
			}
		}
		if (o instanceof se.nangidev.fong.Player && !((Player) o).getInvincible()) {
			disposeAnimation();
			dispose();
		}
	}

	public void randomizePowerUps() {
		int powerUpId = MathUtils.random(1, 3);
		switch (powerUpId) {
		case 1:
			powerUp = se.nangidev.fong.EnumPowerUp.FastFire;
			if (level == 0) {
				setTexture(se.nangidev.fong.Assets.enemyBlue3);
			}
			if (level == 1) {
				setTexture(se.nangidev.fong.Assets.enemyGreen3);
			}
			if (level == 2) {
				setTexture(se.nangidev.fong.Assets.enemyRed3);
			}
			if (level == 3) {
				setTexture(se.nangidev.fong.Assets.enemyBlack3);
			}
			break;
		case 2:
			powerUp = se.nangidev.fong.EnumPowerUp.FastMovement;
			if (level == 0) {
				setTexture(se.nangidev.fong.Assets.enemyBlue2);
			}
			if (level == 1) {
				setTexture(se.nangidev.fong.Assets.enemyGreen2);
			}
			if (level == 2) {
				setTexture(se.nangidev.fong.Assets.enemyRed2);
			}
			if (level == 3) {
				setTexture(se.nangidev.fong.Assets.enemyBlack2);
			}
			break;
		case 3:
			powerUp = se.nangidev.fong.EnumPowerUp.Shield;
			if (level == 0) {
				setTexture(se.nangidev.fong.Assets.enemyBlue4);
			}
			if (level == 1) {
				setTexture(se.nangidev.fong.Assets.enemyGreen4);
			}
			if (level == 2) {
				setTexture(se.nangidev.fong.Assets.enemyRed4);
			}
			if (level == 3) {
				setTexture(se.nangidev.fong.Assets.enemyBlack4);
			}
			break;
			
		}
		updatePowerUps();
	}

	public void randomizeColor(EnumPowerUp powerup) {
		int colorInt = MathUtils.random(0, 3);
		switch (powerup) {
		case FastFire:
			if (colorInt == 0) {
				setTexture(se.nangidev.fong.Assets.enemyBlack3);
			}
			if (colorInt == 1) {
				setTexture(se.nangidev.fong.Assets.enemyBlue3);
			}
			if (colorInt == 2) {
				setTexture(se.nangidev.fong.Assets.enemyRed3);
			}
			if (colorInt == 3) {
				setTexture(se.nangidev.fong.Assets.enemyGreen3);
			}
			break;
		case FastMovement:
			if (colorInt == 0) {
				setTexture(se.nangidev.fong.Assets.enemyBlack2);
			}
			if (colorInt == 1) {
				setTexture(se.nangidev.fong.Assets.enemyBlue2);
			}
			if (colorInt == 2) {
				setTexture(se.nangidev.fong.Assets.enemyRed2);
			}
			if (colorInt == 3) {
				setTexture(se.nangidev.fong.Assets.enemyGreen2);
			}
			break;
		case Shield:
			if (colorInt == 0) {
				setTexture(se.nangidev.fong.Assets.enemyBlack4);
			}
			if (colorInt == 1) {
				setTexture(se.nangidev.fong.Assets.enemyBlue4);
			}
			if (colorInt == 2) {
				setTexture(se.nangidev.fong.Assets.enemyRed4);
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
