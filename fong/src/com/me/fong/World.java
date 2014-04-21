package com.me.fong;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class World extends Tickable {
	private MyGame game;
	private Generator generator;
	private ActivePowerUp app;
	private Player player;

	public static ArrayList<Disposable> dispList = new ArrayList<Disposable>();

	public World(MyGame myGame) {
		this.game = myGame;

		player = new Player(new Texture(
				Gdx.files.internal("playerShip2_blue.png")));
		player.setPosition((MyGame.screenWidth * 0.5f)
				- (player.getTexture().getWidth() * 0.5f * MyGame.scaleX),
				MyGame.screenHeight * 0.15f);
		player.setWidth(player.getTexture().getWidth() * MyGame.scaleX);
		player.setHeight(player.getTexture().getHeight() * MyGame.scaleY);
	}

	@Override
	public void tick(float delta) {
		
		ArrayList<Disposable> temp = new ArrayList<Disposable>(dispList);

		for (Disposable e : temp) {
			if (e instanceof Tickable) {
				((Tickable) e).onTick(delta);
			}
			if (e instanceof MyDrawable) {
				((MyDrawable) e).draw(game.batch, ((MyDrawable) e).getX(),
						((MyDrawable) e).getY(), ((MyDrawable) e).getWidth(),
						((MyDrawable) e).getHeight());
			}
		}
	}
	
	@Override
	public void dispose(){
	}
}
