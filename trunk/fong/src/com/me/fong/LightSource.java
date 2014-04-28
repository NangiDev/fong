package com.me.fong;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;


public class LightSource extends Entity{
	
	public static final float defaultLightZ = 0.1f;
	private Vector3 pos;
	private Vector3 color;
	private Vector3 fallOff;
	private float intensity;
	private SpriteBatch batch;
	
	public LightSource(SpriteBatch batch){
		this(0.0f, 0.0f, batch);	
	}
	
	public LightSource(float x, float y, SpriteBatch batch){
		this.batch = batch;
		pos = new Vector3(x, y, defaultLightZ);
		setDefaultLight();		
	}
	
	public void passToGPU(){
		
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
}
