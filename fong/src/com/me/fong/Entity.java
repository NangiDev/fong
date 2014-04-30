package com.me.fong;

import com.badlogic.gdx.utils.Disposable;

public class Entity implements Disposable {

	private boolean ignoreTick;
	private float x, y;
	private int ID;
	private EntityManager entityManager;

	public Entity(float x, float y, EntityManager entityManager) {
		this.ignoreTick = false;
		this.x = x;
		this.y = y;
		this.entityManager = entityManager;
		this.ID = entityManager.getNewID();
	}

	public void onTick(float delta) {
	}

	public void tick(float delta) {
		if (!getIgnoresTicks()) {
			onTick(delta);
		}
	}

	public final void setIgnoreTicks(boolean flag) {
		ignoreTick = flag;
	}

	public final boolean getIgnoresTicks() {
		return ignoreTick;
	}

	public float getX() {
		return this.x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return this.y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public void setID(int ID){
		this.ID= ID;
	}

	public int getID(){
		return ID;
	}
	
	public void setEntityManager(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	public EntityManager getEntityManager(){
		return this.entityManager;
	}
	
	@Override
	public void dispose() {
	}
}
