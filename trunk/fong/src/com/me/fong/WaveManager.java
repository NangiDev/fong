package com.me.fong;

import java.util.ArrayList;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class WaveManager {
	private int key = 0;
	private int levelCount = 0;
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

	private ArrayList<Ai> enemies = new ArrayList<Ai>();
	private ArrayList<Ai> ticks;

	public WaveManager(MyGame myGame) {
		this.game = myGame;
		this.waveStartTimer = 0.0f;
		this.waveStartTimerDiff = 0.0f;
		this.waveNumber = 0;
		this.waveStart = true;
		this.waveDelay = 1500;
	}

	public void tick(float delta) {
		ticks = new ArrayList<Ai>(enemies);

		// New Wave
		if (waveStartTimer == 0 && enemies.size() == 0) {
			waveNumber++;
			waveStart = false;
			waveStartTimer = System.nanoTime();
		} else {
			waveStartTimerDiff = (System.nanoTime() - waveStartTimer) / 1000000;
			if (waveStartTimerDiff > waveDelay) {
				waveStart = true;
				waveStartTimer = 0;
				waveStartTimerDiff = 0;
			}
		}

		// Create Enemies
		if (waveStart && enemies.size() == 0) {
			createEnemies();
		}
		for (Ai e : ticks) {
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

		for (int y = 0; y < formation.length() / 5.0f; y++) {
			for (int x = 0; x < 5.0f; x++) {
				char a = formation.charAt(x+5*y);
				if (a == '1') {
					Ai temp = new Ai(game.batch, ai.getTexture(), 0, 0,
							game.entityManager, false, aiC);
					temp.setPowerUp(ai.getPowerUp());
					temp.setX(x * (MyGame.screenWidth / 5.0f) + 10.0f * MyGame.scaleX);
					temp.setY(MyGame.screenHeight + y
							* (MyGame.screenHeight / 5.0f));
					game.entityManager.addEntity(temp);
					enemies.add(temp);
				}
			}
		}
	}

	private EnumAiControllers randomizeBehavior() {
		EnumAiControllers e = null;
		key = MathUtils.random(0, 9);

		
		if(levelCount <= 0){
			waveOrder.add(key);
		}
		else{
			key = waveOrder.get(waveNumber-1);
			System.out.println("current wave: " + waveOrder.get(waveNumber-1));
		}

		System.out.println("Wavenumber: " + waveNumber + " " + "Level: " + levelCount);
		System.out.println("List: " + waveOrder);
		
		if((waveNumber%10) == 0){
			waveNumber = 0;
			levelCount++;
		}
		
		switch (key) {
		case 0:
			formation = "10000" + "01000" + "00100" + "00010" + "00001";  // Formation
			e = EnumAiControllers.None;
			break;
		case 1:
			formation = "00100" + "01010" + "10001";
			e = EnumAiControllers.None;
			break;
		case 2:
			formation = "00100" + "01010" + "00100";
			e = EnumAiControllers.None;
			break;
		case 3:
			formation = "00001" + "00010" + "00100" + "01000" + "10000";
			e = EnumAiControllers.None;
			break;
		case 4:
			formation = "00100" + "01010" + "10001" + "01010" + "10001";
			e = EnumAiControllers.None;
			break;
		case 5:
			formation = "10000" + "10000" + "10000" + "10000" + "10000";
			e = EnumAiControllers.Snake;
			break;
		case 6:
			formation = "10001" + "00100" + "10001";
			e = EnumAiControllers.None;
			break;
		case 7:
			formation = "00100" + "00100" + "00100" + "00100" + "00100";
			e = EnumAiControllers.Snake;
			break;
		case 8:
			formation = "10101" + "00000" + "10101";
			e = EnumAiControllers.None;
			break;
		case 9:
			formation = "00001" + "00001" + "00001" + "00001" + "00001";
			e = EnumAiControllers.Snake;
			break;
		}
		return e;
	}
}
