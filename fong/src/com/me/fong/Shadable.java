package com.me.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FileTextureData;

public class Shadable extends DrawComponent{
	
	private boolean ignoreLighting = true;
	private Texture normalTexture = null;
	
	public Shadable(SpriteBatch batch, Texture texture, float x, float y, EntityManager entityManager, boolean ignoreLighting) {
		super(batch, texture, x, y, entityManager);
		this.ignoreLighting = ignoreLighting;
		String path = ((FileTextureData)texture.getTextureData()).getFileHandle().path();
		
		if(path.contains("player")){
			path = "playerNormal" + path.replaceAll("[^0-9]+", "");
			System.out.println(path);
			this.normalTexture = Assets.NORMALS_BY_NAME.get(path);
		}
		else if(path.contains("ufo")){
			this.normalTexture = Assets.NORMALS_BY_NAME.get("ufoNormal");
		}
		else if(path.contains("enemy")){
			path = "enemyNormal" + path.replaceAll("[^0-9]+", "");
			System.out.println(path);
			this.normalTexture = Assets.NORMALS_BY_NAME.get(path);
		}
		else if(path.contains("meteor")){
			this.normalTexture = Assets.NORMALS_BY_NAME.get("meteorNormal");
		}
		else{
			this.normalTexture = Assets.defaulNormal;
		}
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
