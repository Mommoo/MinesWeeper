package com.mommoo.game.screen.view.intro;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JPanel;

import com.mommoo.main.GameDescription;
import com.mommoo.manager.ColorManager;

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
		
		/* for manipulate view locations */
		JPanel blankView = new JPanel();
		blankView.setBackground(ColorManager.getTransparentColor());
		add(blankView);
		
		add(new ManipulateView());
	}
	
	@Override
	public void paint(Graphics g) {
		setOpaque(false);
		if(BACKGROUND_IMAGE == null) BACKGROUND_IMAGE = GameDescription.INTRO_BACKGROUND_IMAGE.getScaledInstance(getWidth(),getHeight(), Image.SCALE_SMOOTH); 
		g.drawImage(BACKGROUND_IMAGE,0,0,this);
		super.paint(g);
	}
	
	
	public static GameIntroView getInstance(){
		return INSTANCE;
	};
}
