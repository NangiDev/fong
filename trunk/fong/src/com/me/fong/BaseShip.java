package com.me.fong;

import java.util.ArrayList;

public class BaseShip extends CollidableComponent{
	private boolean alive;
	private int health;
	private ArrayList<PowerUps> powerUps;
	private EntityManager entityManager;
	
	public BaseShip(EntityManager entityManager){
		this.entityManager = entityManager;
		this.alive = true;
		this.powerUps = new ArrayList<PowerUps>();
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
	
	public PowerUps getPowerUp(int index){
		return this.powerUps.get(index);
	}
	
	public void setHealth(int health){
		this.health = health;
	}
	
	public int getHealth(){
		return this.health;
	}
}
