package com.me.fong;

import com.badlogic.gdx.utils.Disposable;

public class Entity implements Disposable {

	private boolean ignoreTick;
	private float x, y;
	private int ID;

	public Entity(float x, float y) {
		this.ignoreTick = false;
		this.x = x;
		this.y = y;
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
	
	@Override
	public void dispose() {
	}
}
