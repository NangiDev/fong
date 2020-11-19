package se.nangidev.fong;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class WaveManager {
	private int key = 0;
	private int levelCount = 0;
	private int previousLevel = 0;
	private Array<Integer> waveOrder = new Array<Integer>();
	private FongMain game;
	private se.nangidev.fong.Ai ai;
	private float waveStartTimer;
	private float waveStartTimerDiff;
	private int waveNumber;
	private boolean waveStart;
	private int waveDelay;
	private String formation = "";
	private se.nangidev.fong.EnumAiControllers aiC;
	private boolean slutanuro = true;
	private boolean killWaveBonus = false;
	private int powerUpDrop;

	private ArrayList<se.nangidev.fong.DrawComponent> enemies = new ArrayList<se.nangidev.fong.DrawComponent>();
	private ArrayList<se.nangidev.fong.DrawComponent> ticks;

	public WaveManager(FongMain FongMain) {
		this.game = FongMain;
		this.waveStartTimer = 0.0f;
		this.waveStartTimerDiff = 0.0f;
		this.waveNumber = 0;
		this.waveStart = true;
		this.waveDelay = 1500;
		powerUpDrop = MathUtils.random(0,3);
	}

	public void tick(float delta) {
		ticks = new ArrayList<se.nangidev.fong.DrawComponent>(enemies);
		
		for (se.nangidev.fong.DrawComponent e : enemies) {
			if(e.getY() < Player.getPlayerPos().y)
				killWaveBonus = false;
			else 
				killWaveBonus = true;
		}
		
		if (Gdx.input.isKeyPressed(Keys.K)){
			for (se.nangidev.fong.DrawComponent e1 : enemies) {
				game.entityManager.removeEntity(e1);
			}
		}
		
		if (Gdx.input.isKeyPressed(Keys.L) && slutanuro && waveOrder.size >= 5){
			levelCount++;
			FongMain.difficulty += 0.25;
			slutanuro = false;
		}
		if(!Gdx.input.isKeyPressed(Keys.L)){
			slutanuro = true;
		}

		// New Wave
		if (waveStartTimer <= 0 && enemies.size() == 0) {
			if(waveNumber == powerUpDrop){
				se.nangidev.fong.PowerUpPickup p = new se.nangidev.fong.PowerUpPickup(game.batch, se.nangidev.fong.Assets.pill_green, MathUtils.random(se.nangidev.fong.Assets.pill_green.getWidth() * FongMain.scaleX, FongMain.screenWidth - se.nangidev.fong.Assets.pill_green.getWidth() * FongMain.scaleX), FongMain.screenHeight, game.entityManager, false, se.nangidev.fong.EnumPowerUp.FastFire);
			}
			if(waveNumber%2 == 1){
				se.nangidev.fong.PowerUpPickup p = new PowerUpPickup(game.batch, se.nangidev.fong.Assets.pill_green, MathUtils.random(se.nangidev.fong.Assets.pill_green.getWidth() * FongMain.scaleX, FongMain.screenWidth - se.nangidev.fong.Assets.pill_green.getWidth() * FongMain.scaleX), FongMain.screenHeight, game.entityManager, false, EnumPowerUp.Shield);
			}
				
			waveNumber++;
			if(killWaveBonus){
				FongMain.score += 500;
				se.nangidev.fong.MovingLabel points = new se.nangidev.fong.MovingLabel("Wavepoints\n\n500", game.batch, game, se.nangidev.fong.Assets.buttonYellow, 0.0f, FongMain.screenHeight*0.7f, game.entityManager, true, se.nangidev.fong.EnumAiControllers.None);
				points.setLabelStyle(game.smalllabelStyle);
				points.setLifeTime(800.0f);
				game.entityManager.addEntity(points);
			}
			
			waveStart = false;
			waveStartTimer = System.nanoTime();
		} else {
			waveStartTimerDiff = (System.nanoTime() - waveStartTimer) / 1000000;
			if (waveStartTimerDiff > waveDelay * 1.0f / FongMain.difficulty) {
				waveStart = true;
				waveStartTimer = 0;
				waveStartTimerDiff = 0;
			}
		}

		// Create Enemies
		if (waveStart && enemies.size() == 0) {
			createEnemies();
		}
		for (DrawComponent e : ticks) {
			if (!game.entityManager.entityExists(e)) {
				enemies.remove(e);
			}
		}
		// System.out.println("Enemies size: " + enemies.size());
	}

	private void createEnemies() {
		aiC = randomizeBehavior();
		enemies.clear();
		ai = new se.nangidev.fong.Ai(game.batch, se.nangidev.fong.Assets.enemyBlue4, 0, 0, game.entityManager,
				false, aiC);
		ai.setLevel(levelCount);
		ai.randomizePowerUps();
		

		if (levelCount <= previousLevel) {
			for (int y = 0; y < formation.length() / 9.0f; y++) {
				for (int x = 0; x < 9.0f; x++) {
					char a = formation.charAt(x + 9 * y);
					if (a == '1') {
						se.nangidev.fong.Ai temp = new Ai(game.batch, ai.getTexture(), 0, 0,
								game.entityManager, false, aiC);
						temp.setPowerUp(ai.getPowerUp());
						temp.updatePowerUps();
						temp.setX(x * (FongMain.screenWidth / 9.0f));
						temp.setY(FongMain.screenHeight + y
								* (FongMain.screenHeight / 9.0f));
						//temp.updateAiController();
						temp.setHealth(ai.getHealth());
						if (levelCount >= 4) {
							temp.randomizeColor(temp.getPowerUp());
						}

						game.entityManager.addEntity(temp);
						enemies.add(temp);
					}
				}
			}
		} else {
			se.nangidev.fong.MovingLabel invAi = new MovingLabel("Level\n"+ (previousLevel + 1) +"\ncomplete\n\n800 points", game.batch, game, Assets.buttonYellow, 0, FongMain.screenHeight, game.entityManager, true, se.nangidev.fong.EnumAiControllers.None);
			game.entityManager.addEntity(invAi);
			enemies.add(invAi);
			System.out.println("Level "+ previousLevel +" complete!");
		}

		previousLevel = levelCount;
	}

	private se.nangidev.fong.EnumAiControllers randomizeBehavior() {
		se.nangidev.fong.EnumAiControllers e = null;
		key = MathUtils.random(0, 9);
		if (levelCount <= 0) {
			waveOrder.add(key);
		} else {
			key = waveOrder.get(waveNumber - 1);
		}
		if ((waveNumber % 5) == 0) {
			waveNumber = 0;
			levelCount++;
			FongMain.score += 800;
			FongMain.difficulty += 0.25;
			powerUpDrop = MathUtils.random(0,3);
		}

		switch (key) {
		case 0:
			formation = "010000000" + "010000000" + "010000000" + "010000000"
					+ "010000000" + "010000000"; // Formation
			e = se.nangidev.fong.EnumAiControllers.Round;
			break;
		case 1:
			formation = "000000010" + "000000010" + "000000010" + "000000010"
					+ "000000010" + "000000010";
			e = se.nangidev.fong.EnumAiControllers.Round;
			break;
		case 2:
			formation = "100100000" + "100100000" + "000000000" + "000001001"
					+ "000001001";
			e = se.nangidev.fong.EnumAiControllers.ZigZag;
			break;
		case 3:
			formation = "000000010" + "000001001" + "000000100" + "010000000"
					+ "100100000" + "001000000";
			e = se.nangidev.fong.EnumAiControllers.ZigZag;
			break;
		case 4:
			formation = "000010000" + "000010000" + "000010000" + "000010000"
					+ "000010000" + "000010000";
			e = se.nangidev.fong.EnumAiControllers.Snake;
			break;
		case 5:
			formation = "100000000" + "000000001" + "100000000" + "000000001"
					+ "100000000" + "000000001" + "100000000" + "000000001";
			e = se.nangidev.fong.EnumAiControllers.Snake;
			break;
		case 6:
			formation = "100000000" + "000000001" + "010000000" + "000000010"
					+ "001000000" + "000000100" + "000100000" + "000001000"
					+ "000010000";
			e = se.nangidev.fong.EnumAiControllers.None;
			break;
		case 7:
			formation = "000010000" + "000001000" + "000100000" + "000000100"
					+ "001000000" + "000000010" + "010000000" + "000000001"
					+ "100000000";
			e = se.nangidev.fong.EnumAiControllers.None;
			break;
		case 8:
			formation = "000010000" + "000000100" + "001000000" + "000000001"
					+ "100000000";
			e = se.nangidev.fong.EnumAiControllers.homingHunting;
			break;
		case 9:
			formation = "010000000" + "001000001" + "000000010" + "100000100";
			e = EnumAiControllers.homingHunting;
			break;
		}
		return e;
	}
}
