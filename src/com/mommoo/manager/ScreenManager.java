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

	public static void main(String str[]){
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = res.width;
		int screenHeight = res.height;

		Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		int windowWidth = winSize.width;
		int windowHeight = winSize.height;

		int windowNavigationBarHeight = screenHeight - windowHeight;

		System.out.println("디바이스의 넓이 : "+screenWidth+"px , 디바이스의 높이 : "+screenHeight+"px");
		System.out.println("윈도우OS 에서 사용가능한 그래픽 넓이 :  " + windowWidth+"px , 윈도우OS 에서 사용가능한 그래픽 높이 : "+windowHeight+"px");
		System.out.println("윈도우OS 에서의 네비게이션바 높이\n(만약 윈도우 OS에서 다른 네비게이션바 처럼 화면을 차지하는 그래픽 요소가 추가되면 코드 변경해야함)\n"+windowNavigationBarHeight+"px");
	}
}

