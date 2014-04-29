package com.me.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FileTextureData;

public class Shadable extends DrawComponent{
	
	private boolean ignoreLighting = true;
	private Texture normalTexture;
	
	public Shadable(SpriteBatch batch, Texture texture, float x, float y, EntityManager entityManager, boolean ignoreLighting) {
		super(batch, texture, x, y, entityManager);
		this.ignoreLighting = ignoreLighting;
		String path = ((FileTextureData)texture.getTextureData()).getFileHandle().path();
		
		this.normalTexture = Assets.NORMALS_BY_NAME.get(path);
		if(this.normalTexture == null)
			this.normalTexture = Assets.defaulNormal;
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
