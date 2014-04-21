package com.me.fong;

public class Tickable extends MyDisposible{
	
	private boolean ignore;
	
	public Tickable(){
		ignore = false;
	}
	
	public void onTick(float delta) {
		//System.out.println("Tick");
	}
	
	public void tick(float delta) {
		if(!ignoresTicks()){
			onTick(delta);
		}
	}
	
	public final void ignoreTicks(boolean flag) {
		ignore = flag;
	}

	public final boolean ignoresTicks() {
		return ignore;
	}

	public void dispose() {
		super.dispose();
	}
}
