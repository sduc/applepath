package com.me.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

abstract public class WallSection extends Scrollable {
	
	public final static int TUNNEL_WIDTH = 50;
	
	public final static int MIN_HEIGHT = 250;
	public final static int MAX_HEIGHT = 500;
	
	
	
	public final static int WALLS_WIDTH = 50;
	
	public WallSection(float x, float y, float v_y, float a_y,
			int width, int height) {
		super(x, y, v_y, a_y, width, height);
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		
	}
	
	abstract public boolean collide(Apple apple);

}

class TurnWallSection extends WallSection {	
	
	public final static int MIN_WIDTH = 200;
	public final static int MAX_WIDTH = 300;
	
	private Rectangle firstTurn, lastTurn, bump;
	private Rectangle leftWall, rightWall;
	
	private boolean isLeftTurn;
	private int turnDepth;

	public TurnWallSection(float x, float y, float v_y, float a_y, int width,
			int height, boolean isLeftTurn) {
		super(x, y, v_y, a_y, width, height);
		this.isLeftTurn = isLeftTurn;
		
		turnDepth = width - 2*WALLS_WIDTH;
		
		firstTurn = new Rectangle();
		lastTurn = new Rectangle();
		bump = new Rectangle();
		leftWall = new Rectangle();
		rightWall = new Rectangle();
		
		setRectangles(WALLS_WIDTH, TUNNEL_WIDTH);
	}
	
	private void setRectangles(int wallWidth, int tunnelWidth) {
		leftWall.set(position.x, position.y, wallWidth, height);
		rightWall.set(position.x + width - wallWidth, position.y, 
									wallWidth, height);
		
		int turnWidth = turnDepth - tunnelWidth;
		int turnHeight = wallWidth;
		int bumpWidth = turnWidth;
		int bumpHeight = height - 2*wallWidth - 2*tunnelWidth;
		
		if (isLeftTurn) {
			firstTurn.set(leftWall.x + leftWall.width, 
					position.y, 
					turnWidth, 
					turnHeight);
			bump.set(rightWall.x - bumpWidth, 
					position.y + firstTurn.height + tunnelWidth,
					bumpWidth, 
					bumpHeight);
			lastTurn.set(leftWall.x + leftWall.width,
					position.y + height,
					turnWidth,
					turnHeight);
		} else {
			firstTurn.set(rightWall.x - turnWidth, 
					position.y, 
					turnWidth, 
					turnHeight);
			bump.set(leftWall.x + leftWall.width, 
					position.y + firstTurn.height + tunnelWidth,
					bumpWidth, 
					bumpHeight);
			lastTurn.set(rightWall.x - turnWidth,
					position.y + height,
					turnWidth,
					turnHeight);
		}
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		setRectangles(WALLS_WIDTH, TUNNEL_WIDTH);
	}

	@Override
	public boolean collide(Apple apple) {
		if (super.position.y < apple.position.y + apple.height) {
			Circle hb = apple.getHitBox();
			return Intersector.overlaps(hb, firstTurn) ||
					Intersector.overlaps(hb, lastTurn) ||
					Intersector.overlaps(hb, bump) 	   ||
					Intersector.overlaps(hb, leftWall) ||
					Intersector.overlaps(hb, rightWall);
		}
		return false;
	}
	
	
}

class StraightWallSection extends WallSection {
	
	Rectangle left, right;

	public StraightWallSection(float x, float y, float v_y, float a_y, int height) {
		super(x, y, v_y, a_y, TUNNEL_WIDTH + 2*WALLS_WIDTH, height);
		
		right = new Rectangle();
		left = new Rectangle();
		
		setRectangles(WALLS_WIDTH, TUNNEL_WIDTH);

	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		setRectangles(WALLS_WIDTH, TUNNEL_WIDTH);
	}
	
	private void setRectangles(int wallWidth, int tunnelWidth) {
		left.set(position.x, position.y, wallWidth, height);
		right.set(position.x + width - wallWidth, position.y, wallWidth, height);
	}

	@Override
	public boolean collide(Apple apple) {
		if (super.position.y < apple.position.y + apple.height) {
			Circle hb = apple.getHitBox();
			return Intersector.overlaps(hb, left) ||
					Intersector.overlaps(hb, right);
		}
		return false;
	}
	
}
