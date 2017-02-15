package com.mommoo.game.screen;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MainGameScreen extends JFrame{
	
	private final int GAME_SCREEN_WIDTH,GAME_SCREEN_HEIGHT; 
	
	public MainGameScreen(){
		int screenWidth = ScreenManager.getScreenWidth();
		int screenHeight = ScreenManager.getScreenHeight();
		if(ScreenManager.isEnoughScreen()){
			GAME_SCREEN_WIDTH = screenWidth/3;
			GAME_SCREEN_HEIGHT = screenHeight/2; 
		}else{
			GAME_SCREEN_WIDTH = -1;
			GAME_SCREEN_HEIGHT = -1;
		}
		
		this.setTitle("MinesWeeper");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getRootPane().setPreferredSize(new Dimension(GAME_SCREEN_WIDTH,GAME_SCREEN_HEIGHT));
		this.pack();
		
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		new MainGameScreen();
	}
}
