package se.nangidev.fong;

public class PowerUps {

	public static float getFireBehavior(se.nangidev.fong.EnumPowerUp powerUp){
		
		if(powerUp == se.nangidev.fong.EnumPowerUp.FastFire)
			return 0.8f * (1.0f / FongMain.difficulty) + 0.4f;
		else
			return 1.0f;
	}
	
	public static float getMovementBehavior(se.nangidev.fong.EnumPowerUp powerUp){
		if(powerUp == se.nangidev.fong.EnumPowerUp.FastMovement)
			return 1.2f * FongMain.difficulty;
		else
			return 1.0f * FongMain.difficulty;
	}
	
	public static float getHealthBehavior(se.nangidev.fong.EnumPowerUp powerUp){
		if(powerUp == EnumPowerUp.Shield){
			return 1.2f * FongMain.difficulty;
		}
		else
			return 1.0f * (FongMain.difficulty);
	}
}
