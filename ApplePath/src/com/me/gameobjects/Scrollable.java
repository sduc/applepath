package com.me.gameobjects;


public class Scrollable extends GameObject {
	
	protected boolean isFullyScrolled;

	public Scrollable(
			float x, float y, 
			float v_x, float v_y, 
			float a_x, float a_y, 
			int width, int height) {
		super(x, y, v_x, v_y, a_x, a_y, width, height);
		isFullyScrolled = false;
	}
	
	public Scrollable(
			float x, float y, 
			float v_y, float a_y, 
			int width, int height) {
		super(x, y, 0, v_y, 0, a_y, width, height);
		isFullyScrolled = false;
	}
	
	public void update(float delta) {
        position.add(velocity.cpy().scl(delta));

        // If the Scrollable object is no longer visible:
        if (super.position.y + super.height < 0) {
            isFullyScrolled = true;
        }
    }
	
	public void reset(float newY) {
        position.y = newY;
        isFullyScrolled = false;
    }
	
    public boolean isFullyScrolled() {
        return isFullyScrolled;
    }
    
    public void stop() {
    	velocity.y = 0;
    }

    public float getTailY() {
        return position.y + height;
    }

}
