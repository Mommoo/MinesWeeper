package com.mommoo.game.main;

import static com.mommoo.game.main.GameDescription.*;

import java.awt.Dimension;

/**
 * 
 * @author mommoo
 *
 * GameDescriptionObejct is Helper class to apply option to game easily.
 * Users must don't modify this class.  
 * 
 */
public class GameDescriptionObject {
	public static final int LEVEL1 = 1;
	public static final int LEVEL2 = 2;
	public static final int LEVEL3 = 3;
	
	private GameDescriptionObject(){};
	
	public static Dimension getLevelDimension(int level){
		int viewWidth = 0;
		int viewHeight = 0;
		viewWidth += GAME_VIEW_PADDING * 2; viewHeight += GAME_VIEW_PADDING * 2;
		viewHeight += GAME_NAVIGATION_VIEW_HEIGHT;

		/* get tile col, row for match level*/
		int tileColumn = 0;
		int tileRow = 0;
		switch(level){
		case LEVEL1 :
			tileColumn = LEVEL1_GAME_VIEW_TILE_COL_CNT;
			tileRow = LEVEL1_GAME_VIEW_TILE_ROW_CNT;
			break;
		case LEVEL2 :
			break;
		case LEVEL3 :
			break;
		}
		
		int gamePanelWidth = tileColumn * MINE_SIZE;
		int gamePanelHeight = tileRow * MINE_SIZE;
		
		viewWidth += gamePanelWidth;
		viewHeight += gamePanelHeight;
		
		return new Dimension(viewWidth,viewHeight);
	}
	
	/**
	 * 
	 * @param level
	 * @return Array of size 3
	 *   index 0 : tile column count
	 *   index 1 : tile row count
	 *   index 2 : mine count
	 *   
	 */
	public static int[] getLevelGameElement(int level){
		if(level == LEVEL1) return new int[]{LEVEL1_GAME_VIEW_TILE_COL_CNT,LEVEL1_GAME_VIEW_TILE_ROW_CNT,LEVEL1_GAME_VIEW_MINE_CNT};
		return null;
	}
}
