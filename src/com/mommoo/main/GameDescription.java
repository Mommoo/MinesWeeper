package com.mommoo.main;

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
 *
 * @see com.mommoo.main.GameDescriptionObject
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
	public static final int LEVEL1_GAME_VIEW_MINE_CNT = 20;
	
	
	/* Common Option Description */
	public static final String MAIN_COLOR_CODE = "#303F9F";
	public static final String SUB_COLOR_CODE = "#5C6BC0";
	public static final Image FLAG_ICON = ImageManager.FLAG;
	public static final Image QUESTION_ICON = ImageManager.QUESTION;
	public static final Image ONE_ICON = ImageManager.NUMBERS[0];
	public static final Image TWO_ICON = ImageManager.NUMBERS[1];
	public static final Image THREE_ICON = ImageManager.NUMBERS[2];
	public static final Image FOUR_ICON = ImageManager.NUMBERS[3];
	public static final Image FIVE_ICON = ImageManager.NUMBERS[4];
	public static final Image SIX_ICON = ImageManager.NUMBERS[5];
	public static final Image SEVEN_ICON = ImageManager.NUMBERS[6];
	public static final Image EIGHT_ICON = ImageManager.NUMBERS[7];
	public static final Image MINE_ICON = ImageManager.BOMB;
	public static final Image X_ICON = ImageManager.X;
	public static final int TILE_SIZE = SM.dip2px(18);
	//public static final int TILE_SIZE = SM.dip2px(30);
}
