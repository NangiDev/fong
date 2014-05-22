package com.me.fong;

public class AiControllers {
	private Ai ai;
	private float strafe;

	public AiControllers(Ai ai) {
		this.ai = ai;
		strafe = ai.getSpeed() * 2;
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
		default:
			break;
		}
	}
}
