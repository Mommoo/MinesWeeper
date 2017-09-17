package com.mommoo.game.screen.view.game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.mommoo.component.tile.InnerElement;
import com.mommoo.component.tile.OuterElement;
import com.mommoo.component.tile.TileStatus;
import com.mommoo.main.GameDescription;
import com.mommoo.main.GameDescriptionObject;

class TileObject extends JPanel{
	private static final Image[] OUTER_TILE_IMAGE_ARRAY = GameDescriptionObject.getOuterTileIcons();
	private static final Image[] HINT_NUMBER_IMAGE_ARRAY = GameDescriptionObject.getNumberIcons();
	private static Image MINE_ICON = GameDescriptionObject.getMineIcon();
	private static Image X_ICON = GameDescription.X_ICON;

	private static final int EMPTY = InnerElement.EMPTY.ordinal();
	private static final int MINE = InnerElement.MINE.ordinal();
	private static final double ARC_SIZE = 10.0d;

	private final int HINT_NUMBER;
	private final RoundRectangle2D.Double RECT = new RoundRectangle2D.Double();
	private final TileMouseManager TILE_MOUSE_MANAGER = new TileMouseManager();
	private boolean isLock,isOpen;

	private int outerElementStatus;
	private int tileWidth,tileHeight;
	private int innerTileWidth, innerTileHeight;
	private int innerPadding;

	private BasicStroke basicStroke;

	private Paint gradientPaint;

	private int mineIconSize;

	private boolean isInit;
	private boolean checkCorrectPutFlag;

	private ControlListener controlListener;

	TileObject(int hintNumber){
		this.HINT_NUMBER = hintNumber;
		setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.5f)));
		addMouseListener(TILE_MOUSE_MANAGER);
	}

	TileStatus getTile(){
		return TileStatus.createTile(HINT_NUMBER , outerElementStatus);
	}
	
	void pressTile(){
		TILE_MOUSE_MANAGER.forceLeftMousePressed(true);
		TileObject.this.repaint();
	}

	void releaseTile(){
		TILE_MOUSE_MANAGER.forceLeftMousePressed(false);
		TileObject.this.repaint();
	}

	void setControlListener(ControlListener controlListener){
		this.controlListener = controlListener;
	}
	
	void open(){
		if(isOpen) return;
		isOpen = true;
		if(controlListener != null && !isLock) {
			if(HINT_NUMBER == InnerElement.MINE.ordinal()) controlListener.bombExploded(this);
			controlListener.tileOpened(this);
		}
		TileObject.this.repaint();
	}

	void checkIsCorrectPutFlag(){
		if(outerElementStatus != OuterElement.FLAG.ordinal()) return;
		checkCorrectPutFlag = true;
		TileObject.this.repaint();
	}

	boolean isOpen(){
		return isOpen;
	}

	void lock(){
		isLock = true;
		removeMouseListener(TILE_MOUSE_MANAGER);
	}

	void unLock(){
		isLock = false;
		addMouseListener(TILE_MOUSE_MANAGER);
	}

	boolean isLock(){
		return isLock;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		/* When first running init  */
		if(!isInit) {
			isInit = true;

			tileWidth = getWidth();
			tileHeight = getHeight();

			innerPadding = tileWidth/20;

			innerTileWidth = tileWidth - innerPadding*4;
			innerTileHeight = tileHeight - innerPadding*4;

			basicStroke = new BasicStroke(innerPadding*2);

			gradientPaint = new GradientPaint(
					innerPadding*2,innerPadding*2,GameDescriptionObject.getSubColor().brighter()
					,innerPadding*2,(int)(innerTileHeight + innerPadding*2),GameDescriptionObject.getMainColor());

			for(int i = 0, length = OUTER_TILE_IMAGE_ARRAY.length; i < length; i++){
				OUTER_TILE_IMAGE_ARRAY[i] = OUTER_TILE_IMAGE_ARRAY[i].getScaledInstance(tileWidth,tileHeight,Image.SCALE_SMOOTH);
			}

			for(int i = 0, length = HINT_NUMBER_IMAGE_ARRAY.length; i < length ; i++){
				HINT_NUMBER_IMAGE_ARRAY[i] = HINT_NUMBER_IMAGE_ARRAY[i].getScaledInstance(tileWidth,tileHeight,Image.SCALE_SMOOTH);
			}

			mineIconSize = 3*tileWidth/5;
			MINE_ICON = MINE_ICON.getScaledInstance(mineIconSize,mineIconSize,Image.SCALE_SMOOTH);
			X_ICON = X_ICON.getScaledInstance(tileWidth,tileHeight,Image.SCALE_SMOOTH);
		}

		/* Draw open tile if hint number 1~8, draw hint number icon and if hint number 0, draw nothing, if hint number 9, draw mine */
		if (isOpen) {
			if(outerElementStatus == EMPTY){
				RECT.setFrame(0, 0, tileWidth, tileHeight);
				g2d.setColor(GameDescriptionObject.getSubColor().brighter().brighter());
				g2d.fill(RECT);
				/* outer */
				if(HINT_NUMBER != EMPTY && HINT_NUMBER != MINE){
					g2d.drawImage(HINT_NUMBER_IMAGE_ARRAY[HINT_NUMBER - 1],0,0,this);
				}else if(HINT_NUMBER == MINE){
					RECT.setFrame(0, 0, tileWidth, tileHeight);
					g2d.setColor(Color.RED);
					g2d.fill(RECT);
					int imageX = (tileWidth - mineIconSize)/2;
					int imageY = (tileHeight - mineIconSize)/2;
					g2d.drawImage(MINE_ICON,imageX,imageY,this);
				}
				return;
			}
		}

		/* Draw inner tile border */
		RECT.setRoundRect(innerPadding, innerPadding, tileWidth - innerPadding * 2, tileHeight - innerPadding * 2, ARC_SIZE, ARC_SIZE);
		g2d.setColor(GameDescriptionObject.getMainColor());
		g2d.setStroke(basicStroke);
		g2d.draw(RECT);

		/* Draw inner tile */
		RECT.setRoundRect(innerPadding * 2.0d, innerPadding * 2.0d, innerTileWidth, innerTileHeight, ARC_SIZE,ARC_SIZE);
		g2d.setPaint(gradientPaint);
		g2d.fill(RECT);

		/* If force click or user click */
		boolean USER_MOUSE_LEFT_CLICK = TILE_MOUSE_MANAGER.isLeftMousePressed() && !TILE_MOUSE_MANAGER.isRightMousePressed();
		if ((TILE_MOUSE_MANAGER.isForceLeftMousePressed() && outerElementStatus == EMPTY) || USER_MOUSE_LEFT_CLICK) {
			/* Draw pressed tile  */
			RECT.setRoundRect(innerPadding * 2.0d, innerPadding * 2.0d, innerTileWidth, innerTileHeight, ARC_SIZE,ARC_SIZE);
			g2d.setColor(GameDescriptionObject.getMainColor().darker());
			g2d.fill(RECT);

			return;
		}

		/* Draw outer tile image */
		if(outerElementStatus == 0) return;
		g2d.drawImage(OUTER_TILE_IMAGE_ARRAY[outerElementStatus-1],0,0,this);

		/* If need to check whether correct put flag,
		 * Change checkCorrectPutFlag variable to true using by checkIsCorrectPutFlag method.
		 * Then if not correct put flag, mark X image
		 */
		if(checkCorrectPutFlag && HINT_NUMBER != MINE) g2d.drawImage(X_ICON,0,0,this);
	}

	private class TileMouseManager implements MouseListener{
		private boolean isMouseEntered;
		private boolean isLeftMousePressed;
		private boolean isRightMousePressed;
		private boolean isForceLeftMousePressed;
		private boolean isForceRightMousePressed;

		public void forceLeftMousePressed(boolean forceLeftMousePress){
			isForceLeftMousePressed = forceLeftMousePress;
		}

		public void forceRightMousePressed(boolean forceRightMousePress){
			isForceRightMousePressed = forceRightMousePress;
		}

		public boolean isMouseEntered(){
			return isMouseEntered;
		}

		public boolean isLeftMousePressed(){
			return isLeftMousePressed;
		}

		public boolean isForceLeftMousePressed(){
			return isForceLeftMousePressed;
		}

		public boolean isRightMousePressed(){
			return isRightMousePressed;
		}

		public boolean isForceRightMousePressed(){
			return isForceRightMousePressed;
		}

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

			if (e.getButton() == MouseEvent.BUTTON1) isLeftMousePressed = true;
			else if (e.getButton() == MouseEvent.BUTTON3) isRightMousePressed = true;



			if(isLeftMousePressed && isRightMousePressed) {
				if(controlListener != null) controlListener.markHint(TileObject.this);
			}

			if(isOpen) return;

			if(!isLeftMousePressed && isRightMousePressed) {
				outerElementStatus++;
				if(outerElementStatus == OuterElement.getCount()) outerElementStatus = 0;
				if(controlListener != null) controlListener.tileOuterChanged(TileObject.this);
			}

			TileObject.this.repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			int buttonsDownMask = MouseEvent.BUTTON1_DOWN_MASK |
							  MouseEvent.BUTTON2_DOWN_MASK |
							  MouseEvent.BUTTON3_DOWN_MASK;
			
			if ( isLeftMousePressed && !isRightMousePressed) {
				isLeftMousePressed = false;
				if( isMouseEntered ) {
					open();
					return;
				}
			}

			else if ( isMouseEntered && isRightMousePressed && !isLeftMousePressed ) {
				isRightMousePressed = false;
			}
			/* when run After mouse right and left button pressed, mouse right and left button released */
			else if ( isRightMousePressed && isLeftMousePressed && (e.getModifiersEx() & buttonsDownMask) == 0) {
				if(controlListener != null) controlListener.openHint(TileObject.this);
				isRightMousePressed = false;
				isLeftMousePressed = false;
			}
			TileObject.this.repaint();
		}
	}
}
