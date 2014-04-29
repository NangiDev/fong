package com.me.fong;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.OnscreenKeyboard;

public class Player extends BaseShip {
	private float
	projectileInterval = 0;
	private ArrayList<Entity> projectiles = new ArrayList<Entity>();
	private EntityManager entityManager;

	public Player(SpriteBatch batch, Texture texture, float x, float y, EntityManager entityManager) {
		super(batch, texture, y, y, entityManager);
		this.entityManager = entityManager;
		setSpeed(800);
	}

	@Override
	public void onTick(float delta) {
		if (Gdx.input.isTouched() && Gdx.input.getY() > MyGame.screenHeight * 0.1f * MyGame.scaleY) {
			if (Gdx.input.getX() > getX() + getTexture().getWidth() * 0.5f
					* MyGame.scaleX + 10.0f * MyGame.scaleX)
				setX(getX() + getSpeed() * delta * MyGame.scaleX);

			else if (Gdx.input.getX() < getX() + getTexture().getWidth() * 0.5f
					* MyGame.scaleX - 10.0f * MyGame.scaleX)
				setX(getX() - getSpeed() * delta * MyGame.scaleX);
		}

		if (getX() < 0)
			setX(0);
		if (getX() > MyGame.screenWidth - getTexture().getWidth()
				* MyGame.scaleX)
			setX(MyGame.screenWidth - getTexture().getWidth() * MyGame.scaleX);

		projectileInterval -= delta * 100;

		if (projectileInterval < 0)
			fireProjectile();
		}

	@Override
	public void onCollision(Object o) {
		super.onCollision(o);
		if (o instanceof Projectile && projectiles.contains(((Projectile)o))) {
			return;
		}
		else if(o instanceof Projectile){
			dispose();
		}
		if (o instanceof Ai) {
			dispose();
		}
	}
	
	@Override
	public void dispose(){
		super.dispose();
		projectiles.clear();
		entityManager.removeEntity(this);
		entityManager.game.switchToScreen(GameState.GameOver);
	}

	public void fireProjectile() {
		Projectile projectile = new Projectile(getSpriteBatch(),
				Assets.laserRed, getOrigiX()
						- Assets.laserRed.getWidth() * 0.5f
						* MyGame.scaleX, getY()
						+ getTexture().getHeight()*0.5f * MyGame.scaleY + Assets.laserRed.getHeight()*0.5f * MyGame.scaleY, this.entityManager, orientation);
		projectiles.add(projectile);
		entityManager.addEntity(projectile);
		projectileInterval = getFireRate();
	}
}
