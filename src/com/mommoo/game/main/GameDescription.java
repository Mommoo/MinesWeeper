package com.mommoo.game.main;

import java.awt.Color;
import java.awt.Image;

import com.mommoo.manager.ImageManager;
import com.mommoo.manager.ScreenManager;
/**
 * 
 * @author mommoo
 * 
 * GameDescription class is helper that enable users to change many element of the game.
 * If users write element in this, when the game start
 * GameDescriptionObejct is made and apply element changed by users to the game automatically
 * @see(com.mommoo.game.main.GameDescriptionObject)
 *
 */
public class GameDescription {
	private static final ScreenManager SM = ScreenManager.getInstance();
	
	/* App screen description */
	public static final String APP_TITLE = "MinesWeeper";
	public static final Image APP_ICON = ImageManager.BOMB;
	public static final int DEFAULT_APP_SCREEN_WIDTH = SM.getWindowHeight() / 2;
	public static final int DEFAULT_APP_SCREEN_HEIGHT = SM.getWindowHeight() / 2;
	
	/* Intro view description */
	public static final Image INTRO_BACKGROUND_IMAGE = ImageManager.INTRO_BACKGROUND;
	public static final String INTRO_START_BTN_TEXT = "START";
	
	/* Game view description */
	public static final int GAME_NAVIGATION_VIEW_HEIGHT = SM.dip2px(20);
	public static final int GAME_VIEW_PADDING = SM.dip2px(8);
	
	public static final int LEVEL1_GAME_VIEW_TILE_COL_CNT = 10;
	public static final int LEVEL1_GAME_VIEW_TILE_ROW_CNT = 10;
	public static final int LEVEL1_GAME_VIEW_MINE_CNT = 30; 
	
	
	/* Common Option Description */
	public static final Color MAIN_COLOR = Color.decode("#303F9F");
	public static final Color SUB_COLOR = Color.decode("#5C6BC0");
	public static final Image FLAG_ICON = ImageManager.FLAG;
	public static final Image QUESTION_ICON = ImageManager.QUESTION;
	public static final int MINE_SIZE = SM.dip2px(18);
}
