package com.me.fong;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


public class LightSource implements Comparable<LightSource>{
	
	public static final float defaultLightZ = 2.0f;
	private Vector3 pos;
	private Vector3 color;
	private float distanceToSprite;
	private ShaderManager shaderManager;
	private float time;
		
	public LightSource(float x, float y, ShaderManager shaderManager){
		pos = new Vector3((1.0f/MyGame.screenWidth) * x, (1.0f/MyGame.screenHeight) * y, defaultLightZ);
		setDefaultLight();
		this.shaderManager = shaderManager;
		this.shaderManager.addLight(this);
		time = 0.0f;
	}
	public void updateLight() {
		setPos((float)(MyGame.screenWidth*(0.5f + Math.sin(time))), (float)(MyGame.screenHeight*(0.5f + Math.cos(time))), defaultLightZ);
		time = time + 0.01f;
		
		//System.out.println("Time: " + time);
	}
	
	public void setDistance(Vector2 spritePos){
		distanceToSprite = spritePos.dst(new Vector2(pos.x, pos.y));
	}
	public void setDefaultLight(){
		color = new Vector3(1.0f, 1.0f, 1.0f);
	}
	
	public void setRedLaserLight(){
		//color = new Vector3(0.005f, 0.0025f, 0.0025f);
		color = new Vector3(0.005f, 0.0f, 0.0f);
	}
	
	public void setGreenLaserLight(){
		//color = new Vector3(0.0025f, 0.005f, 0.0025f);
		color = new Vector3(0.0f, 0.005f, 0.0f);
	}
	
	public void setExplosionLight(){
		color = new Vector3(0.05f, 0.025f, 0.0f);
	}
	
	public void setSunLight(){
		color = new Vector3(255.0f / 255.0f *5.0f,
				255.0f / 255.0f*5.0f, 225.0f / 255.0f*5.0f);
	}
	
	public Vector3 getPos(){
		return pos;
	}
	
	public Vector3 getColor(){
		return color;
	}
	
	
	public void setPos(float x, float y, float z){
		this.pos = new Vector3((1.0f/MyGame.screenWidth)*x, (1.0f/MyGame.screenHeight)*y, z);
	}
	
	public void setColor(Vector3 color){
		this.color = color;
	}

	public void dispose(){
		shaderManager.removeLight(this);
	}
	
	@Override
	public int compareTo(LightSource l) {
		if(this.distanceToSprite > l.distanceToSprite)
			return -1;
		if(this.distanceToSprite < l.distanceToSprite)
			return 1;
		return 0;
	}
}
