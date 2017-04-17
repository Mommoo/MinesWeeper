package com.mommoo.game.screen.view.game;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.mommoo.game.component.Tile;
import com.mommoo.game.component.TilePanel;
import com.mommoo.game.main.GameDescription;
import com.mommoo.manager.ScreenManager;

public class GameView extends JPanel{
	private static final ScreenManager SM = ScreenManager.getInstance();
	private final int TILE_COLUMN, TILE_ROW, MINE_CNT;
	private final TilePanel TILE_PANEL;
	public GameView(final int tileColumn, final int tileRow, int mineCnt){
		this.TILE_COLUMN = tileColumn;
		this.TILE_ROW = tileRow;
		this.MINE_CNT = mineCnt;
		TILE_PANEL = new TilePanel(TILE_COLUMN,TILE_ROW,MINE_CNT);
		
		final int PADDING = GameDescription.GAME_VIEW_PADDING;
		setLayout(new BorderLayout(0,PADDING));
		setBorder(BorderFactory.createEmptyBorder(PADDING,PADDING,PADDING,PADDING));
		add(new NavigationView(),BorderLayout.NORTH);
		add(new GamePanel());
	}
	
	private class GamePanel extends JPanel{
		private GamePanel(){
			setLayout(new GridLayout(GameView.this.TILE_COLUMN,GameView.this.TILE_ROW,0,0));
			for(int col = 0 ; col < GameView.this.TILE_COLUMN ; col++){
				for(int row = 0 ; row < GameView.this.TILE_ROW ; row ++){
					add(new TileObject(new Tile(GameView.this.TILE_PANEL.getInnerElement(col, row))));
				}
			}
		}
	}
}
