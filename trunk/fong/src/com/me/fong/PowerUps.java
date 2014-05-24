package com.me.fong;

public class PowerUps {

	public static float getFireBehavior(EnumPowerUp powerUp){
		
		if(powerUp == EnumPowerUp.FastFire)
			return 0.6f;
		else
			return 1.0f;
	}
	
	public static float getMovementBehavior(EnumPowerUp powerUp){
		if(powerUp == EnumPowerUp.FastMovement)
			return 1.5f;
		else
			return 1.0f;
	}
	
	public static float getHealthBehavior(EnumPowerUp powerUp){
		if(powerUp == EnumPowerUp.Shield){
			return 2.0f;
		}
		else
			return 1.0f;
	}
}
