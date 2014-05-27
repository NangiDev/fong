package com.me.fong;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


public class LightSource implements Comparable<LightSource>{
	
	public static final float defaultLightZ = 0.5f;
	private Vector3 pos;
	private Vector3 color;
	private Vector3 fallOff;
	private float intensity;
	private float distanceToSprite;
	private ShaderManager shaderManager;
		
	public LightSource(float x, float y, ShaderManager shaderManager){
		pos = new Vector3(x, y, defaultLightZ);
		setDefaultLight();
		this.shaderManager = shaderManager;
		this.shaderManager.addLight(this);
		
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
		color = new Vector3(0.0f, 1.0f, 0.0f);
		fallOff = new Vector3(.4f, 3f, 20f);
		intensity = 5.0f;
	}
	
	public void setBlueLaserLight(){
		color = new Vector3(0.0f, 0.0f, 1.0f);
		fallOff = new Vector3(.4f, 3f, 20f);
		intensity = 5.0f;
	}
	
	public void setSunLight(){
		color = new Vector3(1.0f, 1.0f, 1.0f);
		fallOff = new Vector3(5f, 5f, 50f);
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
	
	public void setPos(float x, float y){
		this.pos = new Vector3(x, y, defaultLightZ);
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
