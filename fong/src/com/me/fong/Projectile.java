package com.me.fong;

import com.badlogic.gdx.graphics.Texture;

public class Projectile extends Collidable{
	
	public Projectile(Texture texture){
		this.setTexture(texture);
	}
	
	@Override
	public void onTick(float delta){
		setY(getY() + 500 * delta * MyGame.scaleY);
		
		if(getY() > MyGame.screenHeight || getY() < 0 || getX() > MyGame.screenWidth || getX() < 0){
			this.dispose();
		}
	}
}
