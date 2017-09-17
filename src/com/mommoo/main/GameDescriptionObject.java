package com.mommoo.main;

import com.mommoo.manager.ImageManager;

import static com.mommoo.main.GameDescription.*;

import java.awt.*;

/**
 * 
 * @author mommoo
 *
 * GameDescriptionObejct is Helper class to apply option to game easily.
 * Users have to don't modify this class.
 * If User want to modify option, try modify GameDescription.
 *
 * @see com.mommoo.main.GameDescription;
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
		
		int gamePanelWidth = tileColumn * TILE_SIZE;
		int gamePanelHeight = tileRow * TILE_SIZE;
		
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

	public static Image[] getOuterTileIcons(){
		return new Image[]{FLAG_ICON,QUESTION_ICON};
	}

	public static Image[] getNumberIcons(){
		return new Image[]{ONE_ICON,TWO_ICON,THREE_ICON,FOUR_ICON,FIVE_ICON,SIX_ICON,SEVEN_ICON,EIGHT_ICON};
	}

	public static Image getMineIcon(){
		return MINE_ICON;
	}

	public static Image get_X_Icon() { return X_ICON;}

	public static Color getMainColor(){
		return Color.decode(MAIN_COLOR_CODE);
	}

	public static Color getSubColor(){
		return Color.decode(SUB_COLOR_CODE);
	}
}
