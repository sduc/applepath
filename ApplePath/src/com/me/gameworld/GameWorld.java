package com.me.gameworld;

import java.util.HashMap;

import com.me.gameobjects.Apple;
import com.me.gameobjects.GameObject;

public class GameWorld {
	
	public static final float GRAVITY = 9.81f;
	
	public static final String APPLE_LABEL = "apple";
	
	private int gameHeight;
	private int gameWidth;
	
	private HashMap<String, GameObject> gameObjects;
	
	public GameWorld(int gameHeight, int gameWidth) {
		this.gameHeight = gameHeight;
		this.gameWidth = gameWidth;
		
		gameObjects = new HashMap<String, GameObject>();
		
		gameObjects.put(APPLE_LABEL, new Apple(gameWidth/2, gameHeight/2, 16, 16));
	}

	public void update(float delta) {
		
		for (GameObject go : gameObjects.values()) {
			go.update(delta);
		}
		
	}
	
	public GameObject getGameObject(String label) {
		return gameObjects.get(label);
	}
	
	public HashMap<String, GameObject> getGameObjects() {
		return gameObjects;
	}

}
