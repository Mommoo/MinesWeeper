package com.mommoo.manager;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class ScreenManager {
	private final int screenWidth;
	private final int screenHeight;
	private final int windowWidth;
	private final int windowHeight;
	private static final ScreenManager SCREEN_MANAGER = new ScreenManager();
	{
		Toolkit.getDefaultToolkit().getScreenResolution();
	}
	private ScreenManager(){
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = res.width;
		screenHeight = res.height;
		Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		windowWidth = winSize.width;
		windowHeight = winSize.height;
	}
	public int getScreenWidth(){
		return screenWidth;
	}
	public int getScreenHeight(){
		return screenHeight;
	}
	public int getWindowWidth(){
		return windowWidth;
	}
	public int getWindowHeight(){
		return windowHeight;
	}
	public int dip2px(int px){
		return px*screenWidth/1000;
	}
	
	public int px2dip(int dip){
		return dip/screenWidth/1000;
	}
	
	public static ScreenManager getInstance(){
		return SCREEN_MANAGER;
	}
}

