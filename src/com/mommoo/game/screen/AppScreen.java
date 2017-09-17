package com.mommoo.game.screen;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mommoo.main.GameDescription;
import com.mommoo.game.screen.view.intro.GameIntroView;
import com.mommoo.manager.ScreenManager;

public class AppScreen{
	private final static ScreenManager SM = ScreenManager.getInstance();
	private final static JFrame MAIN_FRAME = new JFrame(GameDescription.APP_TITLE);
	private final static GameIntroView INTRO_VIEW = GameIntroView.getInstance();
	static{
		new AppScreen();
	}
	
	private AppScreen(){
		MAIN_FRAME.setResizable(false);
		MAIN_FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MAIN_FRAME.getRootPane().setPreferredSize(new Dimension(GameDescription.DEFAULT_APP_SCREEN_WIDTH,GameDescription.DEFAULT_APP_SCREEN_HEIGHT));
		MAIN_FRAME.getContentPane().setLayout(new BorderLayout(0,0));
		MAIN_FRAME.pack();
		MAIN_FRAME.getContentPane().add(INTRO_VIEW,BorderLayout.CENTER);
		MAIN_FRAME.setIconImage(GameDescription.APP_ICON);
		MAIN_FRAME.setLocation((SM.getWindowWidth() - MAIN_FRAME.getSize().width)/2 , (SM.getWindowHeight() - MAIN_FRAME.getSize().height)/2);
	}
	
	
	public static void setView(Dimension viewDimension,JPanel view){
		MAIN_FRAME.getRootPane().setPreferredSize(viewDimension);
		MAIN_FRAME.pack();
		MAIN_FRAME.getContentPane().remove(0);
		MAIN_FRAME.getContentPane().add(view,BorderLayout.CENTER);
		MAIN_FRAME.setLocation((SM.getWindowWidth() - MAIN_FRAME.getSize().width)/2 , (SM.getWindowHeight() - MAIN_FRAME.getSize().height)/2);
	}
	
	public static void setIntroView(){
		setView(new Dimension(GameDescription.DEFAULT_APP_SCREEN_WIDTH,GameDescription.DEFAULT_APP_SCREEN_HEIGHT),INTRO_VIEW);
	}

	public static Point getLocation(){
		return MAIN_FRAME.getLocation();
	}

	public static Dimension getSize(){
		return MAIN_FRAME.getSize();
	}
	
	public static void show(){
		MAIN_FRAME.setVisible(true);
	}
	
}
