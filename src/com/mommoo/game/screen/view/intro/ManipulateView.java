package com.mommoo.game.screen.view.intro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.mommoo.game.main.GameDescription;
import com.mommoo.game.main.GameDescriptionObject;
import com.mommoo.game.screen.AppScreen;
import com.mommoo.game.screen.view.game.GameView;
import com.mommoo.manager.ColorManager;
import com.mommoo.manager.FontManager;
import com.mommoo.manager.ScreenManager;

/**
 * This class is a View that user can manipulate many of manipulation system about game
 * @author mommoo
 *
 */
class ManipulateView extends JPanel{
	private static final ScreenManager SM = ScreenManager.getInstance();
	boolean once;
	private final JButton GAME_START_BTN = new JButton(GameDescription.INTRO_START_BTN_TEXT);
	private final BorderLayout BORDER_LAYOUT = new BorderLayout();
	
	ManipulateView(){
		initGameStartBtn();
		setBackground(ColorManager.getTransparentColor());
		setLayout(BORDER_LAYOUT);
		add(new DifficultyChoiceView(),BorderLayout.NORTH);
		add(GAME_START_BTN,BorderLayout.CENTER);
	}
	
	private void initGameStartBtn(){
		int level = 1;
		GAME_START_BTN.setFont(FontManager.getTrackFont(Font.BOLD, SM.dip2px(22)));
		GAME_START_BTN.setFocusPainted(false);
		GAME_START_BTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
		GAME_START_BTN.addActionListener((e)->{
			int[] gameElement = GameDescriptionObject.getLevelGameElement(level);
			GameView gameView = new GameView(gameElement[0],gameElement[1],gameElement[2]);
			AppScreen.setView(GameDescriptionObject.getLevelDimension(level),gameView);
		});
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		if(!once){
			once = true;
			final int PADDING_RATIO = getHeight()/6;
			setBorder(BorderFactory.createEmptyBorder(PADDING_RATIO, PADDING_RATIO*2, PADDING_RATIO, PADDING_RATIO*2));
			BORDER_LAYOUT.setVgap(PADDING_RATIO/2);
		}
	}
	
	private class DifficultyChoiceView extends JPanel{
		private final int PADDING_RATIO = SM.dip2px(7);
		private int userMouseX;
		private int userMouseY;
		
		private DifficultyChoiceView(){
			/* init label */
			JLabel guideLabel = new JLabel("Difficulty",JLabel.CENTER);
			guideLabel.setFont(guideLabel.getFont().deriveFont(Font.BOLD,(float)SM.dip2px(13)));
			guideLabel.setBorder(BorderFactory.createEmptyBorder(PADDING_RATIO, PADDING_RATIO, PADDING_RATIO, PADDING_RATIO));
			
			/* make choice view */
			JPanel choiceView = new JPanel(){
				private final RoundRectangle2D LEFT_BTN = new RoundRectangle2D.Double();
				private final Rectangle2D MIDDLE_BTN = new Rectangle2D.Double();
				private final RoundRectangle2D RIGHT_BTN = new RoundRectangle2D.Double();
				private final Color CHOICE_COLOR = GameDescription.MAIN_COLOR;
				private final Color NOT_CHOICE_COLOR = GameDescription.SUB_COLOR;
				private final Color CHOICE_TEXT_COLOR = Color.WHITE;
				private final Font CHOICE_FONT = FontManager.getTrackFont(Font.BOLD,SM.dip2px(6));
				private final Font NOT_CHOICE_FONT = FontManager.getTrackFont(Font.PLAIN,SM.dip2px(5));
				private final String LEVEL1 = "level1";
				private final String LEVEL2 = "level2";
				private final String LEVEL3 = "level3";
				
				@Override
				public void paint(Graphics g){
					super.paint(g);
					Graphics2D g2 = (Graphics2D)g;
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
					final int RECT_WIDTH = getWidth();
					final int RECT_HEIGHT = getHeight() - PADDING_RATIO*2;
					final int PANEL_Y = PADDING_RATIO;
					FontMetrics fontMetrics = null;
					int clickedPosition = 0;
					
					if(MIDDLE_BTN.contains(userMouseX, userMouseY)) clickedPosition = 1;
					else if(RIGHT_BTN.contains(userMouseX, userMouseY)) clickedPosition = 2;
					
					
					LEFT_BTN.setRoundRect(0, PANEL_Y, RECT_WIDTH/2.5d, RECT_HEIGHT, 20, 20);
					g2.setColor(clickedPosition == 0 ? CHOICE_COLOR : NOT_CHOICE_COLOR);
					g2.setFont (clickedPosition == 0 ? CHOICE_FONT : NOT_CHOICE_FONT);
					fontMetrics = g2.getFontMetrics();
					g2.fill(LEFT_BTN);
					
					int stringWidth = fontMetrics.stringWidth(LEVEL1);
					int stringHeight = fontMetrics.getHeight();
					g2.setColor(CHOICE_TEXT_COLOR);
					g2.drawString(LEVEL1, (int)((RECT_WIDTH/6d) - stringWidth/2.0d), PANEL_Y + (RECT_HEIGHT/2 - stringHeight/2)+fontMetrics.getAscent());
					
					double rightBtnX = (RECT_WIDTH/1.5d)-(RECT_WIDTH/2.5d - RECT_WIDTH/3.0d);
					RIGHT_BTN.setRoundRect(rightBtnX,PANEL_Y,RECT_WIDTH/2.5d,RECT_HEIGHT,20,20);
					g2.setColor(clickedPosition == 2 ? CHOICE_COLOR : NOT_CHOICE_COLOR);
					g2.setFont (clickedPosition == 2 ? CHOICE_FONT : NOT_CHOICE_FONT);
					fontMetrics = g2.getFontMetrics();
					g2.fill(RIGHT_BTN);
					
					stringWidth = fontMetrics.stringWidth(LEVEL3);
					g2.setColor(CHOICE_TEXT_COLOR);
					g2.drawString(LEVEL3, (int)(RECT_WIDTH/1.5d+(RECT_WIDTH/6d) - stringWidth/2.0d), PANEL_Y + (RECT_HEIGHT/2 - stringHeight/2) + +fontMetrics.getAscent());
					
					MIDDLE_BTN.setRect(RECT_WIDTH/3.0d,PANEL_Y,RECT_WIDTH/3.0d,RECT_HEIGHT);
					g2.setColor(clickedPosition == 1 ? CHOICE_COLOR : NOT_CHOICE_COLOR);
					g2.setFont (clickedPosition == 1 ? CHOICE_FONT : NOT_CHOICE_FONT);
					fontMetrics = g2.getFontMetrics();
					g2.fill(MIDDLE_BTN);
					
					stringWidth = fontMetrics.stringWidth(LEVEL2);
					g2.setColor(CHOICE_TEXT_COLOR);
					g2.drawString(LEVEL2, (int)(RECT_WIDTH/3.0d+(RECT_WIDTH/6d) - stringWidth/2.0d), PANEL_Y + ((RECT_HEIGHT - stringHeight)/2) + +fontMetrics.getAscent());
					
				}
				
				@Override
				public Dimension getPreferredSize(){
					return new Dimension(DifficultyChoiceView.this.getWidth()-DifficultyChoiceView.this.getComponent(0).getWidth() - PADDING_RATIO,DifficultyChoiceView.this.getHeight());
				}
			};
			choiceView.setOpaque(false);
			choiceView.setBackground(ColorManager.getTransparentColor());
			/* init main view */
			setBackground(new Color(0.8f,0.8f,0.8f,0.4f));
			setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
			add(guideLabel);
			add(choiceView);
			addMouseListener(new MouseAdapter(){
				@Override
				public void mousePressed(MouseEvent e){
					userMouseX = e.getX() - choiceView.getX();
					userMouseY = e.getY() - choiceView.getY();
					SwingUtilities.getWindowAncestor(ManipulateView.this).repaint();
				}
			});
			
		}

	}
}
