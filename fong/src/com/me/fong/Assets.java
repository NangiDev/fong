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
	public static final Color myDarkGreen = new Color(71.0f / 255.0f,
			97.0f / 255.0f, 28.0f / 255.0f, 1.0f);
	public static final Color myGreen = new Color(125.0f / 255.0f,
			149.0f / 255.0f, 85.0f / 255.0f, 1.0f);
	public static final Color myYellow = new Color(208.0f / 255.0f,
			197.0f / 255.0f, 141.0f / 255.0f, 1.0f);
	
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
	public static final Texture backgroundBlack = new Texture(
			Gdx.files.internal("Backgrounds/black.png"));
	public static final Texture backgroundBlue = new Texture(
			Gdx.files.internal("Backgrounds/blue.png"));
	public static final Texture backgroundDarkPurple = new Texture(
			Gdx.files.internal("Backgrounds/darkPurple.png"));
	public static final Texture backgroundPurple = new Texture(
			Gdx.files.internal("Backgrounds/purple.png"));

	// Lasers
	public static final Texture laserBlue = new Texture(
			Gdx.files.internal("Lasers/laserBlue.png"));
	public static final Texture laserGreen = new Texture(
			Gdx.files.internal("Lasers/laserGreen.png"));
	public static final Texture laserRed = new Texture(
			Gdx.files.internal("Lasers/laserRed.png"));

	// Laser Collision
	public static final Texture laserBlue1 = new Texture(
			Gdx.files.internal("Lasers/Collision/laserBlue01.png"));
	public static final Texture laserBlue2 = new Texture(
			Gdx.files.internal("Lasers/Collision/laserBlue02.png"));
	public static final Texture laserBlue3 = new Texture(
			Gdx.files.internal("Lasers/Collision/laserBlue03.png"));
	public static final Texture laserBlue4 = new Texture(
			Gdx.files.internal("Lasers/Collision/laserBlue04.png"));

	public static final Texture laserGreen1 = new Texture(
			Gdx.files.internal("Lasers/Collision/laserGreen01.png"));
	public static final Texture laserGreen2 = new Texture(
			Gdx.files.internal("Lasers/Collision/laserGreen02.png"));
	public static final Texture laserGreen3 = new Texture(
			Gdx.files.internal("Lasers/Collision/laserGreen03.png"));
	public static final Texture laserGreen4 = new Texture(
			Gdx.files.internal("Lasers/Collision/laserGreen04.png"));

	public static final Texture laserRed1 = new Texture(
			Gdx.files.internal("Lasers/Collision/laserRed01.png"));
	public static final Texture laserRed2 = new Texture(
			Gdx.files.internal("Lasers/Collision/laserRed02.png"));
	public static final Texture laserRed3 = new Texture(
			Gdx.files.internal("Lasers/Collision/laserRed03.png"));
	public static final Texture laserRed4 = new Texture(
			Gdx.files.internal("Lasers/Collision/laserRed04.png"));

	// Meteors
	public static final Texture meteorBrown = new Texture(
			Gdx.files.internal("Meteors/meteorBrown_big1.png"));
	public static final Texture meteorGray = new Texture(
			Gdx.files.internal("Meteors/meteorGrey_big1.png"));
	
	public static final Texture meteorExplosion = new Texture(
			Gdx.files.internal("Meteors/meteorExplosion.png"));

	// Player
	public static final Texture playerShip1_blue = new Texture(
			Gdx.files.internal("Player/playerShip1_blue.png"));
	public static final Texture playerShip2_blue = new Texture(
			Gdx.files.internal("Player/playerShip2_blue.png"));
	public static final Texture playerShip3_blue = new Texture(
			Gdx.files.internal("Player/playerShip3_blue.png"));
	public static final Texture ufoBlue = new Texture(
			Gdx.files.internal("Player/ufoBlue.png"));

	public static final Texture playerShip1_green = new Texture(
			Gdx.files.internal("Player/playerShip1_green.png"));
	public static final Texture playerShip2_green = new Texture(
			Gdx.files.internal("Player/playerShip2_green.png"));
	public static final Texture playerShip3_green = new Texture(
			Gdx.files.internal("Player/playerShip3_green.png"));
	public static final Texture ufoGreen = new Texture(
			Gdx.files.internal("Player/ufoGreen.png"));

	public static final Texture playerShip1_red = new Texture(
			Gdx.files.internal("Player/playerShip1_red.png"));
	public static final Texture playerShip2_red = new Texture(
			Gdx.files.internal("Player/playerShip2_red.png"));
	public static final Texture playerShip3_red = new Texture(
			Gdx.files.internal("Player/playerShip3_red.png"));
	public static final Texture ufoRed = new Texture(
			Gdx.files.internal("Player/ufoRed.png"));

	public static final Texture playerShip1_orange = new Texture(
			Gdx.files.internal("Player/playerShip1_orange.png"));
	public static final Texture playerShip2_orange = new Texture(
			Gdx.files.internal("Player/playerShip2_orange.png"));
	public static final Texture playerShip3_orange = new Texture(
			Gdx.files.internal("Player/playerShip3_orange.png"));
	public static final Texture ufoYellow = new Texture(
			Gdx.files.internal("Player/ufoYellow.png"));

	// Enemies
	public static final Texture enemyBlack1 = new Texture(
			Gdx.files.internal("Enemies/enemyBlack1.png"));
	public static final Texture enemyBlack2 = new Texture(
			Gdx.files.internal("Enemies/enemyBlack2.png"));
	public static final Texture enemyBlack3 = new Texture(
			Gdx.files.internal("Enemies/enemyBlack3.png"));
	public static final Texture enemyBlack4 = new Texture(
			Gdx.files.internal("Enemies/enemyBlack4.png"));

	public static final Texture enemyBlue1 = new Texture(
			Gdx.files.internal("Enemies/enemyBlue1.png"));
	public static final Texture enemyBlue2 = new Texture(
			Gdx.files.internal("Enemies/enemyBlue2.png"));
	public static final Texture enemyBlue3 = new Texture(
			Gdx.files.internal("Enemies/enemyBlue3.png"));
	public static final Texture enemyBlue4 = new Texture(
			Gdx.files.internal("Enemies/enemyBlue4.png"));

	public static final Texture enemyGreen1 = new Texture(
			Gdx.files.internal("Enemies/enemyGreen1.png"));
	public static final Texture enemyGreen2 = new Texture(
			Gdx.files.internal("Enemies/enemyGreen2.png"));
	public static final Texture enemyGreen3 = new Texture(
			Gdx.files.internal("Enemies/enemyGreen3.png"));
	public static final Texture enemyGreen4 = new Texture(
			Gdx.files.internal("Enemies/enemyGreen4.png"));

	public static final Texture enemyRed1 = new Texture(
			Gdx.files.internal("Enemies/enemyRed1.png"));
	public static final Texture enemyRed2 = new Texture(
			Gdx.files.internal("Enemies/enemyRed2.png"));
	public static final Texture enemyRed3 = new Texture(
			Gdx.files.internal("Enemies/enemyRed3.png"));
	public static final Texture enemyRed4 = new Texture(
			Gdx.files.internal("Enemies/enemyRed4.png"));
	
	//Explosion
	public static final Texture explosion = new Texture(
			Gdx.files.internal("Player/Explosion.png"));

	// Bolts
	public static final Texture bolt_Bronze = new Texture(
			Gdx.files.internal("Power-ups/Bolt/bolt_bronze.png"));
	public static final Texture bolt_Silver = new Texture(
			Gdx.files.internal("Power-ups/Bolt/bolt_silver.png"));
	public static final Texture bolt_Gold = new Texture(
			Gdx.files.internal("Power-ups/Bolt/bolt_gold.png"));

	// Pill
	public static final Texture pill_blue = new Texture(
			Gdx.files.internal("Power-ups/Pill/pill_blue.png"));
	public static final Texture pill_green = new Texture(
			Gdx.files.internal("Power-ups/Pill/pill_green.png"));
	public static final Texture pill_red = new Texture(
			Gdx.files.internal("Power-ups/Pill/pill_red.png"));
	public static final Texture pill_yellow = new Texture(
			Gdx.files.internal("Power-ups/Pill/pill_yellow.png"));

	// Stars
	public static final Texture star_Bronze = new Texture(
			Gdx.files.internal("Power-ups/Star/star_bronze.png"));
	public static final Texture star_Silver = new Texture(
			Gdx.files.internal("Power-ups/Star/star_silver.png"));
	public static final Texture star_Gold = new Texture(
			Gdx.files.internal("Power-ups/Star/star_gold.png"));
	
	//Shield
	public static final Texture shield_Bronze = new Texture(
			Gdx.files.internal("Power-ups/Shield/shield_bronze.png"));
	
	// Things
	public static final Texture things_Bronze = new Texture(
			Gdx.files.internal("Power-ups/Things/things_bronze.png"));
	public static final Texture things_Silver = new Texture(
			Gdx.files.internal("Power-ups/Things/things_silver.png"));
	public static final Texture things_Gold = new Texture(
			Gdx.files.internal("Power-ups/Things/things_gold.png"));

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
		NormasByName
				.put("playerNormal2",
						new Texture(Gdx.files
								.internal("Normals/playerShip2Normal.png")));
		NormasByName
				.put("playerNormal3",
						new Texture(Gdx.files
								.internal("Normals/playerShip3Normal.png")));
		NormasByName.put("ufoNormal",
				new Texture(Gdx.files.internal("Normals/ufoNormal.png")));

		NormasByName.put("enemyNormal1",
				new Texture(Gdx.files.internal("Normals/enemy1Normal.png")));
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
}
