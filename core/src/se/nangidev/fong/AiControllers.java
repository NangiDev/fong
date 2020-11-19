package se.nangidev.fong;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class AiControllers {
	private Ai ai;
	private float strafe;
	private float startX;
	private Vector2 midPoint;
	private Vector2 currentPos;
	private Vector2 dir;
	private boolean startCircle = false;
	private boolean endCircle = false;
	private float index = 1;
	private float oldTime = 1;

	public AiControllers(Ai ai) {
		updateAiC(ai);
	}

	public void updateAiC(Ai ai) {
		this.ai = ai;
		strafe = ai.getSpeed() * 2;
		startX = ai.getOrigoX();
		
	}

	public void controller(EnumAiControllers controller, float delta) {
		//ai.updatePowerUps();
		switch (controller) {
		case None:
			ai.setY(ai.getY() - ai.getSpeed() * delta * FongMain.scaleY);
			break;
		case Snake:
			ai.setY(ai.getY() - ai.getSpeed() * delta * FongMain.scaleY);

			if (ai.getY() < FongMain.screenHeight - ai.getTexture().getHeight()
					* FongMain.scaleY) {
				float next = ai.getX() + strafe * delta * FongMain.scaleX;
				if (next + ai.getTexture().getWidth() * FongMain.scaleX >= FongMain.screenWidth) {
					strafe = strafe * -1.0f;
				}
				if (next <= 0) {
					strafe = strafe * -1.0f;
				}
				ai.setX(ai.getX() + strafe * delta * FongMain.scaleX);
			}
			break;
		case Round:
			midPoint = new Vector2(FongMain.screenWidth * 0.5f,
					FongMain.screenHeight * 0.75f);
			currentPos = new Vector2(ai.getOrigoX(), ai.getOrigoY());
			dir = new Vector2(midPoint.x - currentPos.x, midPoint.y
					- currentPos.y);
			dir = dir.nor();

			if (startX > midPoint.x)
				dir.rotate(89);
			else
				dir.rotate(-89);

			if ((!startCircle && ai.getOrigoY() >= FongMain.screenHeight * 0.75f)
					|| (endCircle && ai.getOrigoY() <= FongMain.screenHeight * 0.75f)) {
				ai.setY(ai.getY() - ai.getSpeed() * delta * FongMain.scaleY);
			} else {
				startCircle = true;
				ai.setY(ai.getY() + ai.getSpeed() * dir.y * delta
						* FongMain.scaleY);
				ai.setX(ai.getX() + ai.getSpeed() * dir.x * delta
						* FongMain.scaleX);

				if (startX < FongMain.screenWidth * 0.5f && dir.angle() > 180
						&& dir.angle() < 200) {
					endCircle = true;
				}
				if (startX > FongMain.screenWidth * 0.5f && dir.angle() > 270) {
					endCircle = true;
				}
			}
			break;
		case ZigZag:
			ai.setY(ai.getY() - ai.getSpeed() * delta * FongMain.scaleY);
			if (startX > FongMain.screenWidth * 0.5f) {
				if (ai.getOrigoX() >= startX) {
					startCircle = true;
				} else if (startX - ai.getOrigoX() > FongMain.screenWidth * 0.5f) {
					startCircle = false;
				}
			} else {
				if (ai.getOrigoX() - strafe * delta * FongMain.scaleX <= startX) {
					startCircle = false;
				} else if (startX - ai.getOrigoX() < -FongMain.screenWidth * 0.5f) {
					startCircle = true;
				}
			}

			if (startCircle) {
				ai.setX(ai.getX() - strafe * delta * FongMain.scaleX);
			} else {
				ai.setX(ai.getX() + strafe * delta * FongMain.scaleX);
			}
			break;
		case homingHunting:

			if (!startCircle) {
				ai.setY(ai.getY() - ai.getSpeed() * delta * FongMain.scaleY);
				oldTime = TimeUtils.nanoTime();
			}

			if (!endCircle && ai.getOrigoY() <= FongMain.screenHeight * 0.7f) {
				startCircle = true;
				if (((TimeUtils.nanoTime() - oldTime) / 1000000) > 500.0f) {
					midPoint = new Vector2(se.nangidev.fong.Player.getPlayerPos().x, Player.getPlayerPos().y);
					currentPos = new Vector2(ai.getOrigoX(), ai.getOrigoY());
					dir = new Vector2(midPoint.x - currentPos.x, midPoint.y
							- currentPos.y);
					dir = dir.nor();
					endCircle = true;
				}
			}

			if (endCircle) {
				ai.setY(ai.getY() + ai.getSpeed() * 1.5f * dir.y * delta
						* FongMain.scaleY);
				ai.setX(ai.getX() + ai.getSpeed() * 1.5f * dir.x * delta
						* FongMain.scaleX);
			}
			break;
		default:
			break;
		}
	}
}
