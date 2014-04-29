package com.me.fong;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

	static final Map<String, Texture> NORMALS_BY_NAME;
	
	//Backgrounds
	public static final Texture backgroundBlack = new Texture(Gdx.files.internal("Backgrounds/black.png"));
	public static final Texture backgroundBlue = new Texture(Gdx.files.internal("Backgrounds/blue.png"));
	public static final Texture backgroundDarkPurple = new Texture(Gdx.files.internal("Backgrounds/darkPurple.png"));
	public static final Texture backgroundPurple = new Texture(Gdx.files.internal("Backgrounds/purple.png"));
	
	//Lasers
	public static final Texture laserBlue = new Texture(Gdx.files.internal("Lasers/laserBlue.png"));
	public static final Texture laserGreen = new Texture(Gdx.files.internal("Lasers/laserGreen.png"));
	public static final Texture laserRed = new Texture(Gdx.files.internal("Lasers/laserRed.png"));
	
	//Laser Collision
	public static final Texture laserBlue1 = new Texture(Gdx.files.internal("Lasers/Collision/laserBlue01.png"));
	public static final Texture laserBlue2 = new Texture(Gdx.files.internal("Lasers/Collision/laserBlue02.png"));
	public static final Texture laserBlue3 = new Texture(Gdx.files.internal("Lasers/Collision/laserBlue03.png"));
	public static final Texture laserBlue4 = new Texture(Gdx.files.internal("Lasers/Collision/laserBlue04.png"));

	public static final Texture laserGreen1 = new Texture(Gdx.files.internal("Lasers/Collision/laserGreen01.png"));
	public static final Texture laserGreen2 = new Texture(Gdx.files.internal("Lasers/Collision/laserGreen02.png"));
	public static final Texture laserGreen3 = new Texture(Gdx.files.internal("Lasers/Collision/laserGreen03.png"));
	public static final Texture laserGreen4 = new Texture(Gdx.files.internal("Lasers/Collision/laserGreen04.png"));

	public static final Texture laserRed1 = new Texture(Gdx.files.internal("Lasers/Collision/laserRed01.png"));
	public static final Texture laserRed2 = new Texture(Gdx.files.internal("Lasers/Collision/laserRed02.png"));
	public static final Texture laserRed3 = new Texture(Gdx.files.internal("Lasers/Collision/laserRed03.png"));
	public static final Texture laserRed4 = new Texture(Gdx.files.internal("Lasers/Collision/laserRed04.png"));
	
	//Meteors
	public static final Texture meteorBrown1 = new Texture(Gdx.files.internal("Meteors/meteorBrown_big1.png"));
	public static final Texture meteorGray1 = new Texture(Gdx.files.internal("Meteors/meteorGrey_big1.png"));
	
	//Player
	public static final Texture playerShip1_blue = new Texture(Gdx.files.internal("Player/playerShip1_blue.png"));
	public static final Texture playerShip2_blue = new Texture(Gdx.files.internal("Player/playerShip2_blue.png"));
	public static final Texture playerShip3_blue = new Texture(Gdx.files.internal("Player/playerShip3_blue.png"));
	public static final Texture ufoBlue = new Texture(Gdx.files.internal("Player/ufoBlue.png"));
	
	public static final Texture playerShip1_green = new Texture(Gdx.files.internal("Player/playerShip1_green.png"));
	public static final Texture playerShip2_green = new Texture(Gdx.files.internal("Player/playerShip2_green.png"));
	public static final Texture playerShip3_green = new Texture(Gdx.files.internal("Player/playerShip3_green.png"));
	public static final Texture ufoGreen = new Texture(Gdx.files.internal("Player/ufoGreen.png"));
	
	public static final Texture playerShip1_red = new Texture(Gdx.files.internal("Player/playerShip1_red.png"));
	public static final Texture playerShip2_red = new Texture(Gdx.files.internal("Player/playerShip2_red.png"));
	public static final Texture playerShip3_red = new Texture(Gdx.files.internal("Player/playerShip3_red.png"));
	public static final Texture ufoRed = new Texture(Gdx.files.internal("Player/ufoRed.png"));
	
	public static final Texture playerShip1_orange = new Texture(Gdx.files.internal("Player/playerShip1_orange.png"));
	public static final Texture playerShip2_orange = new Texture(Gdx.files.internal("Player/playerShip2_orange.png"));
	public static final Texture playerShip3_orange = new Texture(Gdx.files.internal("Player/playerShip3_orange.png"));
	public static final Texture ufoYellow = new Texture(Gdx.files.internal("Player/ufoYellow.png"));
		
	//Enemies
	public static final Texture enemyBlack1 = new Texture(Gdx.files.internal("Enemies/enemyBlack1.png"));
	public static final Texture enemyBlack2 = new Texture(Gdx.files.internal("Enemies/enemyBlack2.png"));
	public static final Texture enemyBlack3 = new Texture(Gdx.files.internal("Enemies/enemyBlack3.png"));
	public static final Texture enemyBlack4 = new Texture(Gdx.files.internal("Enemies/enemyBlack4.png"));
	
	public static final Texture enemyBlue1 = new Texture(Gdx.files.internal("Enemies/enemyBlue1.png"));
	public static final Texture enemyBlue1Normal = new Texture(Gdx.files.internal("enemyBlue1Normal.png"));
	public static final Texture enemyBlue2 = new Texture(Gdx.files.internal("Enemies/enemyBlue2.png"));
	public static final Texture enemyBlue3 = new Texture(Gdx.files.internal("Enemies/enemyBlue3.png"));
	public static final Texture enemyBlue4 = new Texture(Gdx.files.internal("Enemies/enemyBlue4.png"));
	
	public static final Texture enemyGreen1 = new Texture(Gdx.files.internal("Enemies/enemyGreen1.png"));
	public static final Texture enemyGreen2 = new Texture(Gdx.files.internal("Enemies/enemyGreen2.png"));
	public static final Texture enemyGreen3 = new Texture(Gdx.files.internal("Enemies/enemyGreen3.png"));
	public static final Texture enemyGreen4 = new Texture(Gdx.files.internal("Enemies/enemyGreen4.png"));
	
	public static final Texture enemyRed1 = new Texture(Gdx.files.internal("Enemies/enemyRed1.png"));
	public static final Texture enemyRed2 = new Texture(Gdx.files.internal("Enemies/enemyRed2.png"));
	public static final Texture enemyRed3 = new Texture(Gdx.files.internal("Enemies/enemyRed3.png"));
	public static final Texture enemyRed4 = new Texture(Gdx.files.internal("Enemies/enemyRed4.png"));
	
	//Bolts
	public static final Texture bolt_Bronze = new Texture(Gdx.files.internal("Power-ups/Bolt/bolt_bronze.png"));
	public static final Texture bolt_Silver= new Texture(Gdx.files.internal("Power-ups/Bolt/bolt_silver.png"));
	public static final Texture bolt_Gold = new Texture(Gdx.files.internal("Power-ups/Bolt/bolt_gold.png"));
	
	//Pill
	public static final Texture pill_blue = new Texture(Gdx.files.internal("Power-ups/Pill/pill_blue.png"));
	public static final Texture pill_green = new Texture(Gdx.files.internal("Power-ups/Pill/pill_green.png"));
	public static final Texture pill_red = new Texture(Gdx.files.internal("Power-ups/Pill/pill_red.png"));
	public static final Texture pill_yellow = new Texture(Gdx.files.internal("Power-ups/Pill/pill_yellow.png"));

	//Stars
	public static final Texture star_Bronze = new Texture(Gdx.files.internal("Power-ups/Star/star_bronze.png"));
	public static final Texture star_Silver= new Texture(Gdx.files.internal("Power-ups/Star/star_silver.png"));
	public static final Texture star_Gold = new Texture(Gdx.files.internal("Power-ups/Star/star_gold.png"));

	//Things
	public static final Texture things_Bronze = new Texture(Gdx.files.internal("Power-ups/Things/things_bronze.png"));
	public static final Texture things_Silver= new Texture(Gdx.files.internal("Power-ups/Things/things_silver.png"));
	public static final Texture things_Gold = new Texture(Gdx.files.internal("Power-ups/Things/things_gold.png"));

	public static final Texture defaulNormal = new Texture(Gdx.files.internal("enemyBlue1Normal.png"));
	
	static {
	    final Map<String, Texture> NormasByName = new HashMap<String, Texture>();
	    NormasByName.put("playerNormal1", new Texture(Gdx.files.internal("enemyBlue1Normal.png")));
	    NormasByName.put("playerNormal2", new Texture(Gdx.files.internal("enemyBlue1Normal.png")));
	    NormasByName.put("playerNormal3", new Texture(Gdx.files.internal("enemyBlue1Normal.png")));
	    NormasByName.put("playerNormal4", new Texture(Gdx.files.internal("enemyBlue1Normal.png")));
	    
	    NormasByName.put("enemyNormal1", new Texture(Gdx.files.internal("enemyBlue1Normal.png")));
	    NormasByName.put("enemyNormal2", new Texture(Gdx.files.internal("enemyBlue1Normal.png")));
	    NormasByName.put("enemyNormal3", new Texture(Gdx.files.internal("enemyBlue1Normal.png")));
	    NormasByName.put("enemyNormal4", new Texture(Gdx.files.internal("enemyBlue1Normal.png")));

	    NormasByName.put("meteorNormal", new Texture(Gdx.files.internal("enemyBlue1Normal.png")));
	    
	    NORMALS_BY_NAME = Collections.unmodifiableMap(NormasByName);
	}
}
