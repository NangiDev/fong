package com.me.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class MovingLabel extends Ai{

	private AiControllers aiC;
	private EnumAiControllers controllerType;
	private Label label;
	private MyGame game;

	public MovingLabel(String text, SpriteBatch batch, MyGame game, Texture texture, float x, float y,
			EntityManager entityManager, boolean ignoreLighting,
			EnumAiControllers controllerType) {
		super(batch, texture, x, y, entityManager, ignoreLighting, controllerType);

		setPowerUp(EnumPowerUp.Shield);
		aiC = new AiControllers(this);
		this.controllerType = controllerType;
		this.label = new Label(text, game.mediumlabelStyle);
		setSpeed(200);
		label.setAlignment(Align.center);
		label.setWidth(MyGame.screenWidth);
		label.setWrap(true);
		this.game = game;
	}

	@Override
	public void draw(){
		if (!ignoreDraw && getY() < MyGame.screenHeight) {
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

		if (getY() < -MyGame.screenHeight*0.25f) {
			this.dispose();
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
}
