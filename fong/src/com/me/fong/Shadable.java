package com.me.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.math.Vector2;

public class Shadable extends DrawComponent{
	
	private boolean ignoreLighting = true;
	private Texture normalTexture = null;
	
	public Shadable(SpriteBatch batch, Texture texture, float x, float y, EntityManager entityManager, boolean ignoreLighting) {
		super(batch, texture, x, y, entityManager);
		this.ignoreLighting = ignoreLighting;
		setNormal(texture);
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
	
	protected void setNormal(Texture texture){
		String path = ((FileTextureData)texture.getTextureData()).getFileHandle().path();
		
		if(path.contains("player")){
			path = "playerNormal" + path.replaceAll("[^0-9]+", "");
			//System.out.println(path);
			this.normalTexture = Assets.NORMALS_BY_NAME.get(path);
		}
		else if(path.contains("ufo")){
			this.normalTexture = Assets.NORMALS_BY_NAME.get("ufoNormal");
		}
		else if(path.contains("enemy")){
			path = "enemyNormal" + path.replaceAll("[^0-9]+", "");
			//System.out.println(path);
			this.normalTexture = Assets.NORMALS_BY_NAME.get(path);
		}
		else if(path.contains("meteor") && !path.contains("Explosion")){
			this.normalTexture = Assets.NORMALS_BY_NAME.get("meteorNormal");
		}
		else if(path.contains("menu")){
			path = path.substring(path.indexOf("/")+1, path.indexOf("."));
			//System.out.println(path);
			this.normalTexture = Assets.NORMALS_BY_NAME.get(path + "Normal");
		}
		
		else if(path.contains("bolt")){
			this.normalTexture = Assets.NORMALS_BY_NAME.get("bolt_goldNormal");
		}
		
		else if(path.contains("pill")){
			this.normalTexture = Assets.NORMALS_BY_NAME.get("pill_greenNormal");
		}
		
		else if(path.contains("shield")){
			this.normalTexture = Assets.NORMALS_BY_NAME.get("shield_bronzeNormal");
		}
		
		else{
			this.normalTexture = Assets.NORMALS_BY_NAME.get("defaultNormal");
		}
	}
}
