package com.me.fong;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Animation extends Shadable{
	
	private SpriteBatch batch;
	private Texture texture;
	private float scaleVar, scaleMax, scaleSpeed;
	private LightSource lightSource;

	public Animation(SpriteBatch batch, Texture texture, float x, float y, float scaleMax, float scaleSpeed, EntityManager entityManager, Sound sound){
		super(batch, texture, x, y, entityManager, true);
		this.batch = batch;
		this.texture = texture;
		this.scaleVar = 0.0f;
		this.scaleMax = scaleMax;
		this.scaleSpeed = scaleSpeed;
		lightSource = new LightSource(x, y,getEntityManager().shaderManager);
		lightSource.setExplosionLight();
		if(sound != null && MyGame.soundOn)
			sound.play(0.2f);
	}
	
	@Override
	public void draw(){
		batch.draw(texture, getX()-getTexture().getWidth()*0.5f*MyGame.scaleX*scaleVar, getY()-getTexture().getHeight()*0.5f*MyGame.scaleY*scaleVar, texture.getWidth() * MyGame.scaleX * scaleVar, texture.getHeight() * MyGame.scaleY * scaleVar);
	}
	
	@Override
	public void onTick(float delta){
		lightSource.setPos(getOrigoX(), getOrigoY(), 0.2f);
		scaleVar += delta*scaleSpeed;
		if(scaleVar > scaleMax || scaleVar < -scaleMax){
			dispose();
			lightSource.dispose();
		}
	}
}
