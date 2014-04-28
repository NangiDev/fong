package com.me.fong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureCombiner {
	
	public Texture texture;
	public Texture normalTexture;
	
	public TextureCombiner(String fileName){
		texture = new Texture(Gdx.files.internal(fileName));
		fileName = fileName.substring(0, fileName.indexOf("."));
		normalTexture = new Texture(Gdx.files.internal(fileName + "Normal.png"));
	}

	public void bind(){
		normalTexture.bind(1);
		texture.bind(0);
	}
	
	public void draw(){
		
	}
	
}
