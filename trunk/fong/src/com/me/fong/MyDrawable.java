package com.me.fong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class MyDrawable extends Tickable implements Drawable {
	private float leftWidth, rightWidth, topHeight, bottomHeight, minWidth,
			minHeight;
	private Texture texture;
	public float posX, posY, width, height;
	
	public MyDrawable(){
	}
	
	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		batch.draw(texture, x, y, width, height);
	}

	public void setPosition(float x, float y) {
		this.posX = x;
		this.posY = y;
	}

	public void setX(float x) {
		this.posX = x;
	}
	
	public float getX(){
		return this.posX;
	}

	public void setY(float y) {
		this.posY = y;
	}
	
	public float getY(){
		return this.posY;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	public float getHeight(){
		return this.height;
	}

	public void setWidth(float width) {
		this.width = width;
	}
	
	public float getWidth(){
		return this.width;
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Texture getTexture() {
		return texture;
	}

	@Override
	public float getLeftWidth() {
		return leftWidth;
	}

	@Override
	public void setLeftWidth(float leftWidth) {
		this.leftWidth = leftWidth;
	}

	@Override
	public float getRightWidth() {
		return rightWidth;
	}

	@Override
	public void setRightWidth(float rightWidth) {
		this.rightWidth = rightWidth;
	}

	@Override
	public float getTopHeight() {
		return topHeight;
	}

	@Override
	public void setTopHeight(float topHeight) {
		this.topHeight = topHeight;
	}

	@Override
	public float getBottomHeight() {
		return bottomHeight;
	}

	@Override
	public void setBottomHeight(float bottomHeight) {
		this.bottomHeight = bottomHeight;
	}

	@Override
	public float getMinWidth() {
		return minWidth;
	}

	@Override
	public void setMinWidth(float minWidth) {
		this.minWidth = minWidth;
	}

	@Override
	public float getMinHeight() {
		return minHeight;
	}

	@Override
	public void setMinHeight(float minHeight) {
		this.minHeight = minHeight;
	}
	
}
