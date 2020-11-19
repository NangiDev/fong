package se.nangidev.fong;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Animation extends Shadable{
	
	private SpriteBatch batch;
	private Texture texture;
	private float scaleVar, scaleMax, scaleSpeed;
	private se.nangidev.fong.LightSource lightSource;

	public Animation(SpriteBatch batch, Texture texture, float x, float y, float scaleMax, float scaleSpeed, EntityManager entityManager, Sound sound, boolean light){
		super(batch, texture, x, y, entityManager, true);
		this.batch = batch;
		this.texture = texture;
		this.scaleVar = 0.0f;
		this.scaleMax = scaleMax;
		this.scaleSpeed = scaleSpeed;
		if(light){
			lightSource = new LightSource(x, y,getEntityManager().shaderManager);
			lightSource.setExplosionLight();
		}
		else
			lightSource = null;
		if(sound != null && FongMain.soundOn)
			sound.play(0.2f);
	}
	
	
	@Override
	public void draw(){
		batch.draw(texture, getX()-getTexture().getWidth()*0.5f* FongMain.scaleX*scaleVar, getY()-getTexture().getHeight()*0.5f* FongMain.scaleY*scaleVar, texture.getWidth() * FongMain.scaleX * scaleVar, texture.getHeight() * FongMain.scaleY * scaleVar);
	}
	
	@Override
	public void onTick(float delta){
		if(lightSource != null)
			lightSource.setPos(getOrigoX(), getOrigoY(), 0.2f);
		scaleVar += delta*scaleSpeed;
		if(scaleVar > scaleMax || scaleVar < -scaleMax){
			dispose();
			if(lightSource != null)
				lightSource.dispose();
		}
	}
}
