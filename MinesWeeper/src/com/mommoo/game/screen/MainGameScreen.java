package com.mommoo.game.screen;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.mommoo.manager.ScreenManager;

public class MainGameScreen extends JFrame{
	private final static ScreenManager SM = ScreenManager.getInstance();
	private final static int GAME_SCREEN_WIDTH = SM.getWindowHeight() / 2;
	private final static int GAME_SCREEN_HEIGHT = GAME_SCREEN_WIDTH;
	private final static GameIntroView INTRO_VIEW = GameIntroView.getInstance();
	
	public MainGameScreen(){
		this.setTitle("MinesWeeper");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getRootPane().setPreferredSize(new Dimension(GAME_SCREEN_WIDTH,GAME_SCREEN_HEIGHT));
		this.getContentPane().setLayout(new BorderLayout(0,0));
		this.pack();
		this.getContentPane().add(INTRO_VIEW,BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(() -> {
			new MainGameScreen();
		});
	}
}
