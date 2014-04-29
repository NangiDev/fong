package com.me.fong;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BaseShip extends CollidableComponent{
	private boolean alive;
	private float health;
	private float healthModifier;
	private float speed;
	private float speedModifier;
	private float fireRate;
	private float fireRateModifier;
	private ArrayList<PowerUps> powerUps;
	private EntityManager entityManager;
	
	public BaseShip(SpriteBatch batch, Texture texture, float x, float y, EntityManager entityManager){
		super(batch, texture, x, y);
		this.entityManager = entityManager;
		this.alive = true;
		this.powerUps = new ArrayList<PowerUps>();
		this.healthModifier = 1;
		this.fireRate = 50;
		this.fireRateModifier = 1;
		this.speed = 500;
		this.speedModifier = 1;
		randomizePowerUps();
	}
	
	private void randomizePowerUps(){
		//Här ska powerups skapas och läggas i listan så att modifierarna kan ändras.
	}
		
	public void setAlive(Boolean alive){
		this.alive = alive;
	}
	
	public boolean getAlive(){
		return this.alive;
	}
	
	public void addPowerUp(PowerUps pwu){
		this.powerUps.add(pwu);
	}
	
	public ArrayList<PowerUps> getPowerUpList(){
		return this.powerUps;
	}
	
	public void setHealth(float health){
		this.health = health;
	}

	public void setSpeed(float speed){
		this.speed = speed;
	}
	
	public float getHealth(){
		return this.health * healthModifier;
	}
	
	public float getSpeed(){
		return this.speed * speedModifier;
	}
	
	public float getFireRate(){
		return this.fireRate * fireRateModifier;
	}
}
