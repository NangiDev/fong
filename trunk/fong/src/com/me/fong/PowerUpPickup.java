package com.me.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class PowerUpPickup extends CollidableComponent{
	private EnumPowerUp powerUp; 

	public PowerUpPickup(SpriteBatch batch, Texture texture, float x, float y,
			EntityManager entityManager, boolean ignoreLighting, EnumPowerUp powerUp) {
		super(batch, texture, x, y, entityManager, ignoreLighting);
		this.powerUp = powerUp;
		
		if(powerUp == EnumPowerUp.FastFire)
			setTexture(Assets.pill_green);
		else if(powerUp == EnumPowerUp.FastMovement)
			setTexture(Assets.bolt_Gold);
		else if(powerUp == EnumPowerUp.Shield)
			setTexture(Assets.shield_Bronze);
				
		getEntityManager().addEntity(this);
	}
	
	public PowerUpPickup(SpriteBatch batch, Texture texture, float x, float y,
			EntityManager entityManager, boolean ignoreLighting) {
		super(batch, texture, x, y, entityManager, ignoreLighting);
		randomizePowerUps();
		getEntityManager().addEntity(this);
	}
	
	public EnumPowerUp getPowerUp(){
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
		if (o instanceof Meteor || o instanceof Ai || o instanceof Projectile) {
			return;
		}
		
		if (o instanceof Player) {
			if(MyGame.soundOn)
				Assets.powerUpSound.play(0.2f);
			
			MovingLabel points = new MovingLabel("100", getSpriteBatch(), getEntityManager().game, getTexture(), getOrigoX()-MyGame.screenWidth*0.5f, getY(), getEntityManager(), true, EnumAiControllers.None);
			points.setLabelStyle(getEntityManager().game.smalllabelStyle);
			points.setLifeTime(500.0f);
			getEntityManager().addEntity(points);
			
			MyGame.score += 100;
			dispose();
		}
	}
	
	private void randomizePowerUps() {
		int powerUpId = MathUtils.random(0, 2);
		
		switch(powerUpId){
			case 0: 
				powerUp = EnumPowerUp.FastFire;
				setTexture(Assets.pill_green);
				break;	
			case 1:
				powerUp = EnumPowerUp.FastMovement;
				setTexture(Assets.bolt_Gold);
				break;
			case 2:
				powerUp = EnumPowerUp.Shield;
				setTexture(Assets.shield_Bronze);
				break;
		}
	}

}
