package se.nangidev.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;

public class MovingLabel extends Ai{

	private AiControllers aiC;
	private se.nangidev.fong.EnumAiControllers controllerType;
	private Label label;
	private FongMain game;
	private boolean selfDying = false;
	private float startTime;
	private float lifeTime = 0;

	public MovingLabel(String text, SpriteBatch batch, FongMain game, Texture texture, float x, float y,
					   EntityManager entityManager, boolean ignoreLighting,
					   EnumAiControllers controllerType) {
		super(batch, texture, x, y, entityManager, ignoreLighting, controllerType);

		setPowerUp(EnumPowerUp.Shield);
		aiC = new AiControllers(this);
		this.controllerType = controllerType;
		this.label = new Label(text, game.mediumlabelStyle);
		setSpeed(200);
		label.setAlignment(Align.center);
		label.setWidth(FongMain.screenWidth);
		label.setWrap(true);
		this.game = game;
		this.startTime = System.nanoTime();
	}

	@Override
	public void draw(){
		if (!ignoreDraw && getY() < FongMain.screenHeight) {
			label.setPosition(getX(), getY());
			label.draw(game.batch, 1.0f);
		}
	}
	
	@Override
	public void onTick(float delta) {
	}
	
	@Override
	public void tick(float delta) {
		aiC.controller(controllerType, delta);

		if (getY() < -FongMain.screenHeight*0.25f) {
			this.dispose();
		}
		
		if(selfDying){
			if((System.nanoTime() - startTime)/1000000 > lifeTime){
				this.dispose();
			}
		}
	}
	
	@Override
	public BoundingBox getWings(){
		return new BoundingBox();
	}

	@Override
	public BoundingBox getFront(){
		return new BoundingBox();
	}
	
	public void setLabelStyle(LabelStyle labelStyle){
		label.setStyle(labelStyle);
	}
	
	public void setLifeTime(float lifeTime){
		selfDying = true;
		this.lifeTime = lifeTime;		
	}
}
