package com.me.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class PowerUpPickup extends CollidableComponent{
	private ActivePowerUp powerUp; 

	public PowerUpPickup(SpriteBatch batch, Texture texture, float x, float y,
			EntityManager entityManager, boolean ignoreLighting, ActivePowerUp powerUp) {
		super(batch, texture, x, y, entityManager, ignoreLighting);
		this.powerUp = powerUp;
		
		if(powerUp == ActivePowerUp.FastFire)
			setTexture(Assets.pill_green);
		else if(powerUp == ActivePowerUp.FastMovement)
			setTexture(Assets.bolt_Gold);
		else if(powerUp == ActivePowerUp.Shield)
			setTexture(Assets.shield_Bronze);
		getEntityManager().addEntity(this);
	}
	
	public PowerUpPickup(SpriteBatch batch, Texture texture, float x, float y,
			EntityManager entityManager, boolean ignoreLighting) {
		super(batch, texture, x, y, entityManager, ignoreLighting);
		randomizePowerUps();
		getEntityManager().addEntity(this);
	}
	
	public ActivePowerUp getPowerUp(){
		return powerUp;
	}
	
	@Override
	public void onTick(float delta) {
		setY(getY() - 500 * delta * MyGame.scaleY);
		
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
		
		if (o instanceof Player) {
			if(MyGame.soundOn)
				Assets.powerUpSound.play(0.8f);
			dispose();
		}
	}
	
	private void randomizePowerUps() {
		int powerUpId = MathUtils.random(0, 2);
		
		switch(powerUpId){
			case 0: 
				powerUp = ActivePowerUp.FastFire;
				setTexture(Assets.pill_green);
				break;	
			case 1:
				powerUp = ActivePowerUp.FastMovement;
				setTexture(Assets.bolt_Gold);
				break;
			case 2:
				powerUp = ActivePowerUp.Shield;
				setTexture(Assets.shield_Bronze);
				break;
		}
	}

}
