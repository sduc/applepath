package com.me.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.me.aphelpers.InputHandler;
import com.me.gameobjects.Apple;
import com.me.gameworld.GameRenderer;
import com.me.gameworld.GameWorld;

public class GameScreen implements Screen {
	
	private GameWorld world;
	private GameRenderer renderer;
	private float runTime = 0;
	
	public GameScreen() {
		
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameWidth = screenWidth;//136;
		float gameHeight = screenHeight;//screenHeight / (screenWidth / gameWidth);
		
		world = new GameWorld((int) gameHeight, (int) gameWidth);
		renderer = new GameRenderer(world, (int) gameHeight, (int) gameWidth);
		
		Gdx.input.setInputProcessor(
				new InputHandler((Apple) world.getGameObject(GameWorld.APPLE_LABEL),
								         renderer.getCam()));

	}

	@Override
	public void render(float delta) {
		runTime += delta;
		world.update(delta);
		renderer.render(runTime);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
