package com.me.fong;
import com.badlogic.gdx.math.Vector3;


public class LightSource {
	
	public static final float defaultLightZ = 0.1f;
	private Vector3 pos;
	private Vector3 color;
	private Vector3 fallOff;
	private float intensity;
	
	public LightSource(){
		this(0.0f, 0.0f);
	}
	
	public LightSource(float x, float y){
		pos = new Vector3(x, y, defaultLightZ);
		setDefaultLight();		
	}
	
	public void setDefaultLight(){
		color = new Vector3(1.0f, 1.0f, 1.0f);
		fallOff = new Vector3(.4f, 3f, 20f);
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
		color = new Vector3(0.96f, 0.9f, 0.14f);
		fallOff = new Vector3(.4f, 3f, 20f);
		intensity = 5.0f;
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
	
	public void setPos(Vector3 pos){
		this.pos = pos;
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
}
