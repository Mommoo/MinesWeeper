package com.mommoo.game.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JPanel;

import com.mommoo.manager.ImageManager;

/**
 * 
 * @author mommoo
 *
 * This class play role of a game intro for user
 * This Allow users to choose the difficulty of game and to option of game
 *    
 */
public class GameIntroView extends JPanel{
	private static Image BACKGROUND_IMAGE;
	private static final GameIntroView INSTANCE = new GameIntroView(); 
	private GameIntroView(){
		setLayout(new GridLayout(2,1,0,0));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		setOpaque(false);
		final int WIDTH = getWidth();
		final int HEIGHT = getHeight();
		if(BACKGROUND_IMAGE == null ) BACKGROUND_IMAGE = ImageManager.BOMB.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		g.drawImage(BACKGROUND_IMAGE,0,0,this);
		super.paintComponent(g);
	}
	
	public static GameIntroView getInstance(){
		return INSTANCE;
	};
}
