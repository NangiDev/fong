package com.me.fong;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ai extends BaseShip {
	private Random rand = new Random();
	private Animation disposeAnimation;

	public Ai(SpriteBatch batch, Texture texture, float x, float y,
			EntityManager entityManager, boolean ignoreLighting) {
		super(batch, texture, x, y, entityManager, ignoreLighting, true);
		setHealth(1);
	}

	@Override
	public void onTick(float delta) {
		setY(getY() - getSpeed() * delta * MyGame.scaleY);

		if (getY() > MyGame.screenHeight
				|| getY() < 0 - (getTexture().getHeight() * MyGame.scaleY)
				|| getX() > MyGame.screenWidth || getX() < 0) {
			int n = rand.nextInt((int) (MyGame.screenWidth - getTexture()
					.getWidth() * MyGame.scaleX)) + 1;
			setX(n);
			setY(MyGame.screenHeight);
			// this.dispose();
		}
	}

	@Override
	public void onCollision(Object o) {
		super.onCollision(o);
		if (o instanceof Projectile && ((Projectile)o).getProjectileParent() != this.getID()) {
			setHealth(getHealth() - 1);
			if (getHealth() <= 0) {
				// dispose();
				//disposeAnimation = new Animation(getSpriteBatch(), Assets.explosion, getOrigoX(), getY(), 2.5f, 3.0f, getEntityManager());
				setHealth(1);
				int n = rand.nextInt((int) MyGame.screenWidth) + 1;
				setX(n);
				setY(MyGame.screenHeight);
				MyGame.score += 100;
			}
		}
		if (o instanceof Player) {
			dispose();
		}
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
