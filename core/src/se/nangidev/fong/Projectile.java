package se.nangidev.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends CollidableComponent {

	private boolean orientation;
	private boolean parentIsPlayer;
	private Animation disposeAnimation;
	private int speed;
	private Vector2 direction;
	private se.nangidev.fong.LightSource lightSource;

	public Projectile(SpriteBatch batch, Texture texture, float x, float y,
					  EntityManager entityManager, boolean orientation,
					  boolean parentIsPlayer, Vector2 direction) {
		super(batch, texture, x, y, entityManager, true);
		this.orientation = orientation;
		this.parentIsPlayer = parentIsPlayer;
		this.direction = direction;
		lightSource = new LightSource(x, y,getEntityManager().shaderManager);
		
		if(parentIsPlayer){
			speed = 1000;
			lightSource.setGreenLaserLight();
		}
		else{
			speed = 700;
			lightSource.setRedLaserLight();
		}
		
		if(parentIsPlayer)
			getEntityManager().playerProjectiles.add(this);
		
		entityManager.addEntity(this);
	}

	@Override
	public void onTick(float delta) {
		if (orientation){
			setY(getY() - speed * delta * FongMain.scaleY * direction.y);
			setX(getX() - speed * delta * FongMain.scaleX * direction.x);
		}
		else{
			setY(getY() + speed * delta * FongMain.scaleY* direction.y);
			setX(getX() + speed * delta * FongMain.scaleX * direction.x);
		}

		if (getY() > FongMain.screenHeight || getY() < 0
				|| getX() > FongMain.screenWidth || getX() < 0) {
			this.dispose();
		}
		lightSource.setPos(getX(), getY(), 0.1f);
	}

	@Override
	public void dispose() {
		lightSource.dispose();
		getEntityManager().playerProjectiles.remove(this);
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
							+ getTexture().getHeight() * 0.5f * FongMain.scaleY,
					0.5f, 2.5f, getEntityManager(), Assets.laserSound, false);
			getEntityManager().addToEndEntity(disposeAnimation);
		}
		dispose();
	}

	public boolean getProjectileParent() {
		return this.parentIsPlayer;
	}
}
