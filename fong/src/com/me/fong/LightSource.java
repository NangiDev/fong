package com.me.fong;
import com.badlogic.gdx.math.Vector3;


public class LightSource{
	
	public static final float defaultLightZ = 1.0f;
	private Vector3 pos;
	private Vector3 color;
	private Vector3 fallOff;
	private float intensity;
		
	public LightSource(float x, float y, ShaderManager shaderManager){
		pos = new Vector3(x, y, defaultLightZ);
		setDefaultLight();
		shaderManager.addLight(this);
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
}
