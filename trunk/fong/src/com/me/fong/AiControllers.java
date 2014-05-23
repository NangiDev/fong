package com.me.fong;

import com.badlogic.gdx.math.Vector2;

public class AiControllers {
	private Ai ai;
	private float strafe;
	private float startX;
	private Vector2 midPoint;
	private Vector2 currentPos;
	private Vector2 dir;
	private boolean startCircle = false;
	private boolean endCircle = false;

	public AiControllers(Ai ai) {
		this.ai = ai;
		updateAiC();
	}

	public void updateAiC() {
		strafe = ai.getSpeed() * 2;
		startX = ai.getOrigoX();
	}

	public void controller(EnumAiControllers controller, float delta) {
		switch (controller) {
		case None:
			ai.setY(ai.getY() - ai.getSpeed() * delta * MyGame.scaleY);
			break;
		case Snake:
			ai.setY(ai.getY() - ai.getSpeed() * delta * MyGame.scaleY);

			if (ai.getY() < MyGame.screenHeight - ai.getTexture().getHeight()
					* MyGame.scaleY) {
				float next = ai.getX() + strafe * delta * MyGame.scaleX;
				if (next + ai.getTexture().getWidth() * MyGame.scaleX >= MyGame.screenWidth) {
					strafe = strafe * -1.0f;
				}
				if (next <= 0) {
					strafe = strafe * -1.0f;
				}
				ai.setX(ai.getX() + strafe * delta * MyGame.scaleX);
			}
			break;
		case Round:
			midPoint = new Vector2(MyGame.screenWidth * 0.5f,
					MyGame.screenHeight * 0.5f);
			currentPos = new Vector2(ai.getOrigoX(), ai.getOrigoY());
			dir = new Vector2(midPoint.x - currentPos.x, midPoint.y
					- currentPos.y);
			dir = dir.nor();
			
			if (startX > midPoint.x)
				dir.rotate90(1);
			else
				dir.rotate90(-1);
			
			if((!startCircle && ai.getOrigoY() >= MyGame.screenHeight*0.5f) || (endCircle && ai.getOrigoY() <= MyGame.screenHeight*0.5f)  ){
				ai.setY(ai.getY() - ai.getSpeed() * delta * MyGame.scaleY);
			}
			else{
				startCircle = true;
				ai.setY(ai.getY() + ai.getSpeed() * dir.y * delta
						* MyGame.scaleY);
				ai.setX(ai.getX() + ai.getSpeed() * dir.x * delta
						* MyGame.scaleX);
				
				if(startX < MyGame.screenWidth*0.5f && dir.angle() > 180 && dir.angle() < 200){
					endCircle = true;
				}
				if(startX > MyGame.screenWidth*0.5f && dir.angle() > 270){
					endCircle = true;
				}
			}
			break;
		default:
			break;
		}
	}
}
