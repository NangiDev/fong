package com.me.fong;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends BaseShip {
	private int projectileInterval = 0;
	private ArrayList<Entity> projectiles = new ArrayList<Entity>();
	private EntityManager entityManager;

	public Player(SpriteBatch batch, Texture texture, float x, float y, EntityManager entityManager) {
		super(entityManager);
		this.entityManager = entityManager;
		setTexture(texture);
		setSpriteBatch(batch);
		setX(x);
		setY(y);
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

		if (projectileInterval < 0)
			fireProjectile();
		}

	@Override
	public void onCollision(Object o) {
		super.onCollision(o);
		if (o instanceof Projectile && projectiles.contains(((Projectile)o))) {
			return;
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
				Assets.projectileTexture, getOrigiX()
						- Assets.projectileTexture.getWidth() * 0.5f
						* MyGame.scaleX, getY()
						+ getTexture().getHeight() * MyGame.scaleY, this.entityManager);
		projectiles.add(projectile);
		entityManager.addEntity(projectile);
		projectileInterval = 50;
		
		//System.out.println(projectiles.toString());
	}
}
