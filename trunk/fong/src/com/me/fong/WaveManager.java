package com.me.fong;

import java.util.ArrayList;

public class WaveManager {
	private int key = 0;
	private MyGame game;
	private Ai ai;
	private Meteor ob;
	private float waveStartTimer;
	private float waveStartTimerDiff;
	private int waveNumber;
	private boolean waveStart;
	private int waveDelay;
	private String formation = "";

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
		randomizeFormation();
		enemies.clear();
		String[] a = formation.split("");
		ai = new Ai(game.batch, Assets.enemyBlue4, 0, 0, game.entityManager,
				false);
		ai.randomizePowerUps();

		for (int y = 0; y < formation.length() / 5.0f; y++) {
			for (int x = 0; x < 5.0f; x++) {
				if (a[y + x + 4 * y].matches("1")) {
					Ai temp = new Ai(game.batch, ai.getTexture(), 0, 0,
							game.entityManager, false);
					temp.setPowerUp(ai.getPowerUp());
					temp.setX(x * (MyGame.screenWidth / 5.0f));
					temp.setY(MyGame.screenHeight + y
							* (MyGame.screenHeight / 5.0f));
					game.entityManager.addEntity(temp);
					enemies.add(temp);
				}
			}
		}
	}

	private void randomizeFormation() {
		switch (key) {
		case 0:
			formation = "10000" + "01000" + "00100" + "00010" + "00001";
			break;
		case 1:
			formation = "00100" + "01010" + "10001";
			break;
		case 2:
			formation = "00100" + "01010" + "00100";
			break;
		case 3:
			formation = "00001" + "00010" + "00100" + "01000" + "10000";
			break;
		case 4:
			formation = "00100" + "01010" + "10001" + "01010" + "10001";
			break;
		case 5:
			formation = "10000" + "10000" + "10000" + "10000" + "10000";
			break;
		case 6:
			formation = "10001" + "00100" + "10001";
			break;
		case 7:
			formation = "00100" + "00100" + "00100" + "00100" + "00100";
			break;
		case 8:
			formation = "10101" + "00000" + "10101";
			break;
		case 9:
			formation = "00001" + "00001" + "00001" + "00001" + "00001";
			break;
		}

		key++;
		if (key > 9)
			key = 0;
	}
}
