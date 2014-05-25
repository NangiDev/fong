package com.me.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Projectile extends CollidableComponent {

	private boolean orientation;
	private boolean parentIsPlayer;
	private Animation disposeAnimation;
	private int speed;

	public Projectile(SpriteBatch batch, Texture texture, float x, float y,
			EntityManager entityManager, boolean orientation,
			boolean parentIsPlayer) {
		super(batch, texture, x, y, entityManager, true);
		this.orientation = orientation;
		this.parentIsPlayer = parentIsPlayer;
		if(parentIsPlayer)
			speed = 1000;
		else
			speed = 700;
		if (MyGame.soundOn)
			Assets.laserSound.play(0.2f);
		entityManager.addEntity(this);
	}

	@Override
	public void onTick(float delta) {
		if (orientation)
			setY(getY() - speed * delta * MyGame.scaleY);
		else
			setY(getY() + speed * delta * MyGame.scaleY);

		if (getY() > MyGame.screenHeight || getY() < 0
				|| getX() > MyGame.screenWidth || getX() < 0) {
			this.dispose();
		}
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void onCollision(Object o) {
		super.onCollision(o);
		if (o instanceof Projectile || o instanceof PowerUpPickup) {
			return;
		}
		if (o instanceof Player && parentIsPlayer) {
			return;
		}
		if (o instanceof Ai && !parentIsPlayer) {
			return;
		}
		if (o instanceof Ai && parentIsPlayer) {
			disposeAnimation = new Animation(getSpriteBatch(),
					Assets.laserGreen4, getOrigoX(), getOrigoY()
							+ getTexture().getHeight() * 0.5f * MyGame.scaleY,
					0.5f, 2.5f, getEntityManager(), Assets.laserSound);
			getEntityManager().addToEndEntity(disposeAnimation);
		}
		dispose();
	}

	public boolean getProjectileParent() {
		return this.parentIsPlayer;
	}
}
