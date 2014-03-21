package com.me.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;


/**
 * @author sduc
 *
 */
public class Apple extends GameObject {
	
	private final static float SPEED_SCL = 100f;
	private final static float ACC_SCL = 10f;
	private final static float EPSILON = 1f;
	
	public final static int APPLE_RADUIS = 20;
	
	private Circle hitBox;
	
	/**
	 * Destination to where the apple should move
	 * while the finger is on the screen
	 */
	private Vector2 targetClick;
	
	public Apple(float x, float y, int width, int height) {
		super(x,y,0,0,0,0,width,height);
		targetClick = null;
		hitBox = new Circle(new Vector2(x + width/2, y + height/2), Math.min(width/2, height/2));
		
	}
	
	public void onClick(float x, float y) {
		targetClick = new Vector2(x, y);
		Vector2 targetVel = new Vector2(targetClick);
		targetVel.sub(super.position).nor();
		Vector2 targetAcc = new Vector2(targetVel);
		super.velocity = targetVel.scl(Apple.SPEED_SCL);
		super.acceleration = targetAcc.scl(Apple.ACC_SCL);
	}

	public void onRelease() {
		reInitializeMove();
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		if (targetClick != null) {
			stopIfReachedTarget();
		}
	}
	
	private void stopIfReachedTarget() {
		if (targetClick.dst(super.position) < Apple.EPSILON) {
			reInitializeMove();
		}
	}
	
	private void reInitializeMove(){
		targetClick = null;
		super.velocity = Vector2.Zero;
		super.acceleration = Vector2.Zero;
	}
	
	public Circle getHitBox() {
		return hitBox;
	}

}
