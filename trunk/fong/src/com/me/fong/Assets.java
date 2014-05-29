package com.me.fong;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
		
	//Colors
	public static final Color myYellow = new Color(208.0f / 255.0f,
			197.0f / 255.0f, 141.0f / 255.0f, 1.0f);
	public static final Color myGreen = new Color(125.0f / 255.0f,
			149.0f / 255.0f, 85.0f / 255.0f, 1.0f);
	
	// Sounds
	public static final Music music = Gdx.audio.newMusic(Gdx.files
			.internal("ReachingNull.mp3"));
	public static final Sound buttonSound = Gdx.audio.newSound(Gdx.files
			.internal("ButtonPress.mp3"));
	public static final Sound pauseSound = Gdx.audio.newSound(Gdx.files
			.internal("pause.mp3"));
	public static final Sound laserSound = Gdx.audio.newSound(Gdx.files
			.internal("Laser1.mp3"));
	public static final Sound explosionSound = Gdx.audio.newSound(Gdx.files
			.internal("Explosion.mp3"));
	public static final Sound powerUpSound = Gdx.audio.newSound(Gdx.files
			.internal("PowerUp.mp3"));
	public static final Sound laserDeflectSound = Gdx.audio.newSound(Gdx.files
			.internal("deflect.mp3"));
	
	// Menu headers
		public static final Texture buttonYellow = new Texture(
				Gdx.files.internal("menu/buttonYellow.png"));
		public static final Texture credits = new Texture(
				Gdx.files.internal("menu/credits.png"));
		public static final Texture cursor = new Texture(
				Gdx.files.internal("menu/cursor.png"));
		public static final Texture gameOver = new Texture(
				Gdx.files.internal("menu/gameOver.png"));
		public static final Texture highscore = new Texture(
				Gdx.files.internal("menu/highscore.png"));
		public static final Texture instructions = new Texture(
				Gdx.files.internal("menu/instructions.png"));
		public static final Texture logotype = new Texture(
				Gdx.files.internal("menu/logotype.png"));
		public static final Texture options = new Texture(
				Gdx.files.internal("menu/options.png"));
		public static final Texture pause = new Texture(
				Gdx.files.internal("menu/pause.png"));

	// Backgrounds
	public static final Texture backgroundBlue = new Texture(
			Gdx.files.internal("Backgrounds/blue.png"));

	// Lasers
	public static final Texture laserGreen = new Texture(
			Gdx.files.internal("Lasers/laserGreen.png"));
	public static final Texture laserRed = new Texture(
			Gdx.files.internal("Lasers/laserRed03.png"));

	// Laser Collision
	public static final Texture laserGreen4 = new Texture(
			Gdx.files.internal("Lasers/Collision/laserGreen04.png"));
	public static final Texture laserRed4 = new Texture(
			Gdx.files.internal("Lasers/Collision/laserRed04.png"));

	// Player
	public static final Texture playerLife = new Texture(
			Gdx.files.internal("menu/playerLife.png"));

	public static final Texture playerShip1_red = new Texture(
			Gdx.files.internal("Player/playerShip1_red.png"));

	// Enemies
	public static final Texture enemyBlack2 = new Texture(
			Gdx.files.internal("Enemies/enemyBlack2.png"));
	public static final Texture enemyBlack3 = new Texture(
			Gdx.files.internal("Enemies/enemyBlack3.png"));
	public static final Texture enemyBlack4 = new Texture(
			Gdx.files.internal("Enemies/enemyBlack4.png"));

	public static final Texture enemyBlue2 = new Texture(
			Gdx.files.internal("Enemies/enemyBlue2.png"));
	public static final Texture enemyBlue3 = new Texture(
			Gdx.files.internal("Enemies/enemyBlue3.png"));
	public static final Texture enemyBlue4 = new Texture(
			Gdx.files.internal("Enemies/enemyBlue4.png"));

	public static final Texture enemyGreen2 = new Texture(
			Gdx.files.internal("Enemies/enemyGreen2.png"));
	public static final Texture enemyGreen3 = new Texture(
			Gdx.files.internal("Enemies/enemyGreen3.png"));
	public static final Texture enemyGreen4 = new Texture(
			Gdx.files.internal("Enemies/enemyGreen4.png"));

	public static final Texture enemyRed2 = new Texture(
			Gdx.files.internal("Enemies/enemyRed2.png"));
	public static final Texture enemyRed3 = new Texture(
			Gdx.files.internal("Enemies/enemyRed3.png"));
	public static final Texture enemyRed4 = new Texture(
			Gdx.files.internal("Enemies/enemyRed4.png"));
	
	//Explosion
	public static final Texture explosion = new Texture(
			Gdx.files.internal("Player/Explosion.png"));

	// Bolt
	public static final Texture bolt_Gold = new Texture(
			Gdx.files.internal("Power-ups/Bolt/bolt_gold.png"));

	// Pill
	public static final Texture pill_green = new Texture(
			Gdx.files.internal("Power-ups/Pill/pill_green.png"));

	//Shield
	public static final Texture shield_Bronze = new Texture(
			Gdx.files.internal("Power-ups/Shield/shield_bronze.png"));
	
	// Normals
	static final Map<String, Texture> NORMALS_BY_NAME;
	static {
		final Map<String, Texture> NormasByName = new HashMap<String, Texture>();
		NormasByName
		.put("defaultNormal",
				new Texture(Gdx.files
						.internal("Normals/defaultNormal.png")));
		
		NormasByName
				.put("playerNormal1",
						new Texture(Gdx.files
								.internal("Normals/playerShip1Normal.png")));
		
		NormasByName.put("enemyNormal2",
				new Texture(Gdx.files.internal("Normals/enemy2Normal.png")));
		NormasByName.put("enemyNormal3",
				new Texture(Gdx.files.internal("Normals/enemy3Normal.png")));
		NormasByName.put("enemyNormal4",
				new Texture(Gdx.files.internal("Normals/enemy4Normal.png")));

		NormasByName.put("meteorNormal",
				new Texture(Gdx.files.internal("Normals/meteorNormal.png")));
		
		NormasByName.put("creditsNormal",
				new Texture(Gdx.files.internal("Normals/creditsNormal.png")));
		NormasByName.put("gameOverNormal",
				new Texture(Gdx.files.internal("Normals/gameOverNormal.png")));
		NormasByName.put("highscoreNormal",
				new Texture(Gdx.files.internal("Normals/highscoreNormal.png")));
		NormasByName.put("instructionsNormal",
				new Texture(Gdx.files.internal("Normals/instructionsNormal.png")));
		NormasByName.put("logotypeNormal",
				new Texture(Gdx.files.internal("Normals/logotypeNormal.png")));
		NormasByName.put("optionsNormal",
				new Texture(Gdx.files.internal("Normals/optionsNormal.png")));
		NormasByName.put("pauseNormal",
				new Texture(Gdx.files.internal("Normals/pauseNormal.png")));
		NormasByName.put("bolt_goldNormal",
				new Texture(Gdx.files.internal("Normals/bolt_goldNormal.png")));
		NormasByName.put("pill_greenNormal",
				new Texture(Gdx.files.internal("Normals/pill_greenNormal.png")));
		NormasByName.put("shield_bronzeNormal",
				new Texture(Gdx.files.internal("Normals/shield_bronzeNormal.png")));
		

		NORMALS_BY_NAME = Collections.unmodifiableMap(NormasByName);
	}
	
	public static void setUpSoundPrios(){
		buttonSound.setPriority(1, 5);
		pauseSound.setPriority(2, 4);
		explosionSound.setPriority(3, 3);
		powerUpSound.setPriority(4, 2);
		laserDeflectSound.setPriority(5, 1);
		laserSound.setPriority(6, 0);
	}
}
