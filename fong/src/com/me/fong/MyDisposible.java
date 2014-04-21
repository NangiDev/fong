package com.me.fong;

import com.badlogic.gdx.utils.Disposable;

public class MyDisposible implements Disposable{
	
	public MyDisposible(){
		World.dispList.add(this);
	}

	@Override
	public void dispose() {
		World.dispList.remove(this);
	}
}