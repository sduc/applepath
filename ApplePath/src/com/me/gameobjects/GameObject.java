package com.me.gameobjects;

import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
	
	protected Vector2 position;
	protected Vector2 velocity;
	protected Vector2 acceleration;
	
	protected int width;
	protected int height;
	
	public GameObject(float x, float y, float v_x, float v_y, float a_x, float a_y, int width, int height) {
		this.position = new Vector2(x, y);
		this.velocity = new Vector2(v_x, v_y);
		this.acceleration = new Vector2(a_x, a_y);
		this.width = width;
		this.height = height;
	}

	public void update(float delta) {
		velocity.add(acceleration.cpy().scl(delta));
		position.add(velocity.cpy().scl(delta));
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}

}
