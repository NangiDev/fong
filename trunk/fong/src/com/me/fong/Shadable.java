package com.me.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Shadable extends DrawComponent{
	
	private boolean ignoreLighting = true;
	private Texture normalTexture;
	
	public Shadable(SpriteBatch batch, Texture texture, float x, float y, EntityManager entityManager, boolean ignoreLighting) {
		super(batch, texture, x, y, entityManager);
		this.ignoreLighting = ignoreLighting;
		this.normalTexture = Assets.enemyBlue1Normal;
	}
	
	public void bind(){
		if(ignoreLighting){
			((EntityManager)getEntityManager()).shaderManager.switchToDefaultShader(getSpriteBatch());
		}
		else{
			((EntityManager)getEntityManager()).shaderManager.switchToNormalShader(getSpriteBatch());
			normalTexture.bind(1);
			getTexture().bind(0);
		}
	}
}
