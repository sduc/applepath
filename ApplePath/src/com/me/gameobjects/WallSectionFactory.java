package com.me.gameobjects;

import java.util.Random;

public class WallSectionFactory {
	
	private Random r;
	private final static int NB_WALL_SECTION_T = 3;
	
	public WallSection createWallSection(int x, int y, int v_y, int a_y, int width, int height) {
		int selectType = r.nextInt(NB_WALL_SECTION_T);
		int rHeight = randomInt(WallSection.MIN_HEIGHT, WallSection.MAX_HEIGHT);
		int rWidth = randomInt(TurnWallSection.MIN_WIDTH, TurnWallSection.MAX_WIDTH);
		switch (selectType) {
		case 0:
			return new TurnWallSection(x, y, v_y, a_y, rWidth, rHeight, true);
		case 1:
			return new TurnWallSection(x, y, v_y, a_y, rWidth, rHeight, false);
		case 2:
			return new StraightWallSection(x, y, v_y, a_y, rHeight);
		default:
			assert false;
			return null;
		}
	}
	
	private int randomInt(int min, int max) {
		 return r.nextInt(max - min + 1) + min;
	}
	
}
