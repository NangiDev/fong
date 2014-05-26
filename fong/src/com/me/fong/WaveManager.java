package com.me.fong;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;

public class WaveManager {
	private int key = 0;
	private int levelCount = 0;
	private int previousLevel = 0;
	private Array<Integer> waveOrder = new Array<Integer>();
	private MyGame game;
	private Ai ai;
	private Meteor ob;
	private float waveStartTimer;
	private float waveStartTimerDiff;
	private int waveNumber;
	private boolean waveStart;
	private int waveDelay;
	private String formation = "";
	private EnumAiControllers aiC;
	private boolean slutanuro = true;
	private boolean killWaveBonus = false;
	private int powerUpDrop;

	private ArrayList<DrawComponent> enemies = new ArrayList<DrawComponent>();
	private ArrayList<DrawComponent> ticks;

	public WaveManager(MyGame myGame) {
		this.game = myGame;
		this.waveStartTimer = 0.0f;
		this.waveStartTimerDiff = 0.0f;
		this.waveNumber = 0;
		this.waveStart = true;
		this.waveDelay = 1500;
		powerUpDrop = MathUtils.random(0,3);
	}

	public void tick(float delta) {
		ticks = new ArrayList<DrawComponent>(enemies);
		
		for (DrawComponent e : enemies) {
			if(e.getY() < Player.getPlayerPos().y)
				killWaveBonus = false;
			else 
				killWaveBonus = true;
		}
		
		if (Gdx.input.isKeyPressed(Keys.K)){
			for (DrawComponent e1 : enemies) {
				game.entityManager.removeEntity(e1);
			}
		}
		
		if (Gdx.input.isKeyPressed(Keys.L) && slutanuro && waveOrder.size >= 5){
			levelCount++;
			MyGame.difficulty += 0.25;
			slutanuro = false;
		}
		if(!Gdx.input.isKeyPressed(Keys.L)){
			slutanuro = true;
		}

		// New Wave
		if (waveStartTimer <= 0 && enemies.size() == 0) {
			if(waveNumber == powerUpDrop){
				PowerUpPickup p = new PowerUpPickup(game.batch, Assets.pill_green, MathUtils.random(Assets.pill_green.getWidth() * MyGame.scaleX, MyGame.screenWidth - Assets.pill_green.getWidth() * MyGame.scaleX), MyGame.screenHeight, game.entityManager, false, EnumPowerUp.FastFire);
			}
			if(waveNumber%2 == 1){
				PowerUpPickup p = new PowerUpPickup(game.batch, Assets.pill_green, MathUtils.random(Assets.pill_green.getWidth() * MyGame.scaleX, MyGame.screenWidth - Assets.pill_green.getWidth() * MyGame.scaleX), MyGame.screenHeight, game.entityManager, false, EnumPowerUp.Shield);
			}
				
			waveNumber++;
			if(killWaveBonus){
				MyGame.score += 500;
				MovingLabel points = new MovingLabel("Wavepoints\n\n500", game.batch, game, Assets.buttonYellow, 0.0f, MyGame.screenHeight*0.7f, game.entityManager, true, EnumAiControllers.None);
				points.setLabelStyle(game.smalllabelStyle);
				points.setLifeTime(800.0f);
				game.entityManager.addEntity(points);
			}
			
			waveStart = false;
			waveStartTimer = System.nanoTime();
		} else {
			waveStartTimerDiff = (System.nanoTime() - waveStartTimer) / 1000000;
			if (waveStartTimerDiff > waveDelay * 1.0f / MyGame.difficulty) {
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
		ai = new Ai(game.batch, Assets.enemyBlue4, 0, 0, game.entityManager,
				false, aiC);
		ai.setLevel(levelCount);
		ai.randomizePowerUps();
		

		if (levelCount <= previousLevel) {
			for (int y = 0; y < formation.length() / 9.0f; y++) {
				for (int x = 0; x < 9.0f; x++) {
					char a = formation.charAt(x + 9 * y);
					if (a == '1') {
						Ai temp = new Ai(game.batch, ai.getTexture(), 0, 0,
								game.entityManager, false, aiC);
						temp.setPowerUp(ai.getPowerUp());
						temp.updatePowerUps();
						temp.setX(x * (MyGame.screenWidth / 9.0f));
						temp.setY(MyGame.screenHeight + y
								* (MyGame.screenHeight / 9.0f));
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
			MovingLabel invAi = new MovingLabel("Level\n"+ (previousLevel + 1) +"\ncomplete\n\n800 points", game.batch, game, Assets.buttonYellow, 0, MyGame.screenHeight, game.entityManager, true, EnumAiControllers.None);
			game.entityManager.addEntity(invAi);
			enemies.add(invAi);
			System.out.println("Level "+ previousLevel +" complete!");
		}

		previousLevel = levelCount;
	}

	private EnumAiControllers randomizeBehavior() {
		EnumAiControllers e = null;
		key = MathUtils.random(0, 9);
		if (levelCount <= 0) {
			waveOrder.add(key);
		} else {
			key = waveOrder.get(waveNumber - 1);
		}
		if ((waveNumber % 5) == 0) {
			waveNumber = 0;
			levelCount++;
			MyGame.score += 800;
			MyGame.difficulty += 0.25;
			powerUpDrop = MathUtils.random(0,3);
		}

		switch (key) {
		case 0:
			formation = "010000000" + "010000000" + "010000000" + "010000000"
					+ "010000000" + "010000000"; // Formation
			e = EnumAiControllers.Round;
			break;
		case 1:
			formation = "000000010" + "000000010" + "000000010" + "000000010"
					+ "000000010" + "000000010";
			e = EnumAiControllers.Round;
			break;
		case 2:
			formation = "100100000" + "100100000" + "000000000" + "000001001"
					+ "000001001";
			e = EnumAiControllers.ZigZag;
			break;
		case 3:
			formation = "000000010" + "000001001" + "000000100" + "010000000"
					+ "100100000" + "001000000";
			e = EnumAiControllers.ZigZag;
			break;
		case 4:
			formation = "000010000" + "000010000" + "000010000" + "000010000"
					+ "000010000" + "000010000";
			e = EnumAiControllers.Snake;
			break;
		case 5:
			formation = "100000000" + "000000001" + "100000000" + "000000001"
					+ "100000000" + "000000001" + "100000000" + "000000001";
			e = EnumAiControllers.Snake;
			break;
		case 6:
			formation = "100000000" + "000000001" + "010000000" + "000000010"
					+ "001000000" + "000000100" + "000100000" + "000001000"
					+ "000010000";
			e = EnumAiControllers.None;
			break;
		case 7:
			formation = "000010000" + "000001000" + "000100000" + "000000100"
					+ "001000000" + "000000010" + "010000000" + "000000001"
					+ "100000000";
			e = EnumAiControllers.None;
			break;
		case 8:
			formation = "000010000" + "000000100" + "001000000" + "000000001"
					+ "100000000";
			e = EnumAiControllers.homingHunting;
			break;
		case 9:
			formation = "010000000" + "001000001" + "000000010" + "100000100";
			e = EnumAiControllers.homingHunting;
			break;
		}
		return e;
	}
}
