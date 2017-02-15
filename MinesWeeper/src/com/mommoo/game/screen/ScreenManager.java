package com.mommoo.game.screen;

import java.awt.Dimension;
import java.awt.Toolkit;

class ScreenManager {
	private static final Dimension D = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int MINIMUM_WIDTH = 300;
	private static final int MINIMUM_HEIGHT = 300;
	
	static int getScreenWidth(){
		return D.width;
	}
	static int getScreenHeight(){
		return D.height;
	}
	
	static int getMinimumWidth(){
		return MINIMUM_WIDTH;
	}
	
	static int getMinimumHeight(){
		return MINIMUM_HEIGHT;
	}
	
	static boolean isEnoughScreen(){
		return getScreenWidth() > getMinimumWidth() && getScreenHeight() > getMinimumHeight();
	}
}
