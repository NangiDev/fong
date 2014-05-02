package com.me.fong;

public class PowerUps {

	public static float getFireBehavior(ActivePowerUp powerUp){
		
		if(powerUp == ActivePowerUp.FastFire)
			return 0.7f;
		else
			return 1.0f;
		
	}
	
	public static float getMovementBehavior(ActivePowerUp powerUp){
		if(powerUp == ActivePowerUp.FastMovement)
			return 1.5f;
		else
			return 1.0f;
	}
	
	public static float getHealthBehavior(ActivePowerUp powerUp){
		if(powerUp == ActivePowerUp.Shield){
			return 2.0f;
		}
		
		else
			return 1.0f;
	}
	
}
