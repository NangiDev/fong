package com.me.fong;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Animation extends DrawComponent{
	
	private SpriteBatch batch;
	private Texture texture;
	private float scaleVar, scaleMax, scaleSpeed;

	public Animation(SpriteBatch batch, Texture texture, float x, float y, float scaleMax, float scaleSpeed, EntityManager entityManager, Sound sound){
		super(batch, texture, x, y, entityManager);
		this.batch = batch;
		this.texture = texture;
		this.scaleVar = 0.0f;
		this.scaleMax = scaleMax;
		this.scaleSpeed = scaleSpeed;
		if(sound != null && MyGame.soundOn)
			sound.play();
		entityManager.addToEndEntity(this);
	}
	
	@Override
	public void draw(){
		batch.draw(texture, getX()-getTexture().getWidth()*0.5f*MyGame.scaleX*scaleVar, getY()-getTexture().getHeight()*0.5f*MyGame.scaleY*scaleVar, texture.getWidth() * MyGame.scaleX * scaleVar, texture.getHeight() * MyGame.scaleY * scaleVar);
	}
	
	@Override
	public void onTick(float delta){
		scaleVar += delta*scaleSpeed;
		if(scaleVar > scaleMax || scaleVar < -scaleMax)
			dispose();
	}
}
