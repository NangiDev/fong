package com.me.fong;

import java.util.ArrayList;

public class World {
	private MyGame game;
	private Player player;
	private Ai ai;
	private Meteor ob;
	private float waveStartTimer;
	private float waveStartTimerDiff;
	private int waveNumber;
	private boolean waveStart;
	private int waveDelay;

	private ArrayList<Ai> enemies = new ArrayList<Ai>();
	private ArrayList<Ai> ticks;

	public World(MyGame myGame) {
		this.game = myGame;
		this.waveStartTimer = 0.0f;
		this.waveStartTimerDiff = 0.0f;
		this.waveNumber = 0;
		this.waveStart = true;
		this.waveDelay = 1500;

		player = new Player(game.batch, Assets.playerShip2_red,
				MyGame.screenWidth * 0.5f, MyGame.screenHeight * 0.2f,
				game.entityManager, false);
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
		//System.out.println("Enemies size: " + enemies.size());
	}

	private void createEnemies() {
		enemies.clear();
		ai = new Ai(game.batch, Assets.enemyBlue4, MyGame.screenWidth * 0.5f,
				MyGame.screenHeight, game.entityManager, false);

		for (int i = 0; i < 4 * waveNumber; i++) {
			enemies.add(ai);
		}
	}
}
