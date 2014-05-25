package com.me.fong;

public class PowerUps {

	public static float getFireBehavior(EnumPowerUp powerUp){
		
		if(powerUp == EnumPowerUp.FastFire)
			return 0.8f * (1.0f / MyGame.difficulty); 
		else
			return 1.0f;
	}
	
	public static float getMovementBehavior(EnumPowerUp powerUp){
		if(powerUp == EnumPowerUp.FastMovement)
			return 1.3f * MyGame.difficulty;
		else
			return 1.0f;
	}
	
	public static float getHealthBehavior(EnumPowerUp powerUp){
		if(powerUp == EnumPowerUp.Shield){
			return 1.0f * MyGame.difficulty;
		}
		else
			return 0.0f;
	}
}
