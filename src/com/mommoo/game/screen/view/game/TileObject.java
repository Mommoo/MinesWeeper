package com.mommoo.game.screen.view.game;

import java.awt.BasicStroke;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.mommoo.game.component.Tile;
import com.mommoo.game.component.Tile.OuterElement;
import com.mommoo.game.main.GameDescription;
import com.mommoo.manager.ImageManager;

class TileObject extends JPanel{
	private static Paint gradientPaint;
	private static Image flag,question;
	private final Tile TILE;
	private final RoundRectangle2D RECT = new RoundRectangle2D.Double();
	private boolean isMouseEntered;
	private boolean isLeftMousePressed;
	private boolean isRightMousePressed;
	private boolean isOpen;
	private boolean isHintMark;
	
	TileObject(Tile tile){
		TILE = tile;
		setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.5f)));
		addMouseListener(new TitleMouseListener());
	}
	
	boolean isShow(){
		return TILE.isShow();
	}
	
	void drawPressed(){
		isLeftMousePressed = true;
		TileObject.this.repaint();
	}
	
	void drawReleased(){
		isLeftMousePressed = false;
		TileObject.this.repaint();
	}
	
	void drawOpen(){
		isOpen = true;
		TileObject.this.repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		final double WIDTH = getWidth();
		final double HEIGHT = getHeight();
		final double PADD = WIDTH/20;
		
		if(gradientPaint == null) {
			gradientPaint = new GradientPaint((int)PADD*2,(int)PADD*2,GameDescription.SUB_COLOR.brighter()
					,(int)PADD*2,(int)(HEIGHT-PADD*4),GameDescription.MAIN_COLOR);
		}
		
		RECT.setRoundRect(0,0,WIDTH,HEIGHT,0,0);
		if (isOpen) g2d.setColor(GameDescription.SUB_COLOR.brighter().brighter());
		else g2d.setColor(GameDescription.SUB_COLOR);
		g2d.fill(RECT);
		
		if (isOpen) {
			return;
		}
		
		RECT.setRoundRect(PADD,PADD,WIDTH-PADD*2,HEIGHT-PADD*2,10,10);
		g2d.setColor(GameDescription.MAIN_COLOR);
		g2d.fill(RECT);
		
		if (isLeftMousePressed) {
			
			return;
		}
		
		final double RECT_WIDTH = WIDTH-PADD*4;
		final double RECT_HEIGHT = HEIGHT-PADD*4;
		
		if(flag == null){
			flag = GameDescription.FLAG_ICON.getScaledInstance((int)RECT_WIDTH, (int)RECT_HEIGHT, Image.SCALE_SMOOTH);
			question = GameDescription.QUESTION_ICON.getScaledInstance((int)RECT_WIDTH, (int)RECT_HEIGHT, Image.SCALE_SMOOTH);
		}
		
		RECT.setRoundRect(PADD*2,PADD*2,RECT_WIDTH,RECT_HEIGHT,10,10);
		if (isMouseEntered) g2d.setColor(GameDescription.SUB_COLOR.brighter().brighter()); 	
		else g2d.setPaint(gradientPaint);
		g2d.fill(RECT);
		
		if(TILE.getTileOuterElement() == OuterElement.FLAG) g2d.drawImage(flag,(int)PADD*4,(int)PADD*4,this);
		else if(TILE.getTileOuterElement() == OuterElement.QUESTION) g2d.drawImage(question, (int)PADD*4,(int)PADD*4,this);
	}
	
	private class TitleMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			isMouseEntered = true;
			TileObject.this.repaint();
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			isMouseEntered = false;
			TileObject.this.repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(isOpen) return;
			if (e.getButton() == MouseEvent.BUTTON1) isLeftMousePressed = true;
			else if (e.getButton() == MouseEvent.BUTTON3) isRightMousePressed = true;
			
			if(isLeftMousePressed && isRightMousePressed) isHintMark = true;
			TileObject.this.repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			int buttonsDownMask = MouseEvent.BUTTON1_DOWN_MASK 
		            | MouseEvent.BUTTON2_DOWN_MASK 
		            | MouseEvent.BUTTON3_DOWN_MASK;
			
			if ( isMouseEntered && isLeftMousePressed && !isRightMousePressed) {
				isLeftMousePressed = false;
				isOpen = true;
			} else if ( isMouseEntered && isRightMousePressed && !isLeftMousePressed ) {
				isRightMousePressed = false;
				
			} else if ( isMouseEntered && isRightMousePressed && isLeftMousePressed && (e.getModifiersEx() & buttonsDownMask) == 0) {
				System.out.println("ㅇㅇ");
				isRightMousePressed = false;
				isLeftMousePressed = false;
			}
			TileObject.this.repaint();
		}
		
	}
}
