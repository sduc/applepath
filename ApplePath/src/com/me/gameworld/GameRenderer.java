package com.me.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.me.gameobjects.Apple;

public class GameRenderer {
	
	public static final Color BG_COLOR = new Color(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
	
	private GameWorld world;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	
	private SpriteBatch batcher;
	
	private Apple apple;
	
	private int gameHeight;
	private int gameWidth;
	
	public GameRenderer(GameWorld w, int gameHeight, int gameWidth) {
		world = w;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, gameWidth, gameHeight);
		
		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		
		apple = (Apple) world.getGameObject(GameWorld.APPLE_LABEL);
		
		this.gameHeight = gameHeight;
		this.gameWidth = gameWidth;
		
	}

	public void render(float runTime) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        
        // Begin ShapeRenderer
        shapeRenderer.begin(ShapeType.Filled);
        
        // Draw Background color
        shapeRenderer.setColor(BG_COLOR);
        shapeRenderer.rect(0, 0, gameWidth, gameHeight);
        
        // Draw Apple
        shapeRenderer.setColor(200 / 255.0f, 20 / 255.0f, 20 / 255.0f, 1);
		shapeRenderer.circle(apple.getX() + 4, apple.getY() + 4, 4);
		
		// End ShapeRenderer
        shapeRenderer.end();
	}
	
	public OrthographicCamera getCam() {
		return this.cam;
	}

}
