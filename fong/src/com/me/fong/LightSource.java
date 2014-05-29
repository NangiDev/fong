package com.me.fong;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


public class LightSource implements Comparable<LightSource>{
	
	public static final float defaultLightZ = 2.0f;
	private Vector3 pos;
	private Vector3 color;
	private Vector3 fallOff;
	private float intensity;
	private float distanceToSprite;
	private ShaderManager shaderManager;
	private float speedy, speedx = 0;
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
		
		System.out.println("Time: " + time);
	}
	
	public void setDistance(Vector2 spritePos){
		distanceToSprite = spritePos.dst(new Vector2(pos.x, pos.y));
	}
	public void setDefaultLight(){
		color = new Vector3(1.0f, 1.0f, 1.0f);
		fallOff = new Vector3(0f, 0f, 0f);
		intensity = 5.0f;
	}
	
	public void setRedLaserLight(){
		color = new Vector3(1.0f, 0.0f, 0.0f);
		fallOff = new Vector3(.4f, 3f, 20f);
		intensity = 5.0f;
	}
	
	public void setGreenLaserLight(){
		color = new Vector3(0.0025f, 0.005f, 0.0025f);
		fallOff = new Vector3(.4f, 3f, 20f);
		intensity = 5.0f;
	}
	
	public void setBlueLaserLight(){
		color = new Vector3(0.0f, 0.0f, 1.0f);
		fallOff = new Vector3(.4f, 3f, 20f);
		intensity = 5.0f;
	}
	
	public void setSunLight(){
		color = new Vector3(4.0f, 4.0f, 4.0f);
		fallOff = new Vector3(100f, 100f, 100f);
		intensity = 100.0f;
	}
	
	public Vector3 getPos(){
		return pos;
	}
	
	public Vector3 getColor(){
		return color;
	}
	
	public Vector3 getFallOff(){
		return fallOff;
	}
	
	public float getIntensity(){
		return intensity;
	}
	
	public void setPos(float x, float y, float z){
		this.pos = new Vector3((1.0f/MyGame.screenWidth)*x, (1.0f/MyGame.screenWidth)*y, z);
	}
	
	public void setColor(Vector3 color){
		this.color = color;
	}
	
	public void setFallOff(Vector3 fallOff){
		this.fallOff = fallOff;
	}
	
	public void setIntensity(float intensity){
		this.intensity = intensity;
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
