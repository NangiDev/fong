package com.me.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Player extends Collidable {
	private int projectileInterval = 0;

	public Player(Texture texture) {
		this.setTexture(texture);
	}

	@Override
	public void onTick(float delta) {

		if (Gdx.input.isTouched()) {
			if (Gdx.input.getX() > getX() + getTexture().getWidth() * 0.5f
					* MyGame.scaleX + 10.0f * MyGame.scaleX)
				setX(getX() + 800 * delta * MyGame.scaleX);

			else if (Gdx.input.getX() < getX() + getTexture().getWidth() * 0.5f
					* MyGame.scaleX - 10.0f * MyGame.scaleX)
				setX(getX() - 800 * delta * MyGame.scaleX);
		}

		if (getX() < 0)
			setX(0);
		if (getX() > MyGame.screenWidth - getTexture().getWidth()
				* MyGame.scaleX)
			setX(MyGame.screenWidth - getTexture().getWidth() * MyGame.scaleX);
				
		projectileInterval -= delta * 100;
		
		if(projectileInterval < 0)
			fireProjectile();
	}

	public void fireProjectile() {
		Projectile projectile = new Projectile(new Texture(
				Gdx.files.internal("laserRed07.png")));
		projectile.setPosition(this.getX() + this.getTexture().getWidth()
				* 0.5f * MyGame.scaleX, this.getY()
				+ this.getTexture().getWidth() * 0.5f * MyGame.scaleX);
		projectile.setWidth(projectile.getTexture().getWidth() * MyGame.scaleX);
		projectile.setHeight(projectile.getTexture().getHeight()
				* MyGame.scaleY);
		
		projectileInterval = 50;
	}
}
