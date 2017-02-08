package com.xogud02.gameComponent;

public class Game {

}

class Tile{
	public final static int TILE_CONTENT_EMPTY = 0;
	public final static int TILE_CONTENT_1 = 1;
	public final static int TILE_CONTENT_2 = 2;
	public final static int TILE_CONTENT_3 = 3;
	public final static int TILE_CONTENT_4 = 4;
	public final static int TILE_CONTENT_5 = 5;
	public final static int TILE_CONTENT_6 = 6;
	public final static int TILE_CONTENT_7 = 7;
	public final static int TILE_CONTENT_8 = 8;
	public final static int TILE_CONTENT_MINE = 9;
	
	public final static int TILE_STATE_HIDDEN = 0;
	public final static int TILE_STATE_REVEALED = 1;
	public final static int TILE_STATE_FLAGGED = 2;
	
	private final int TILE_CONTENT;
	private int tileState;
	public Tile(int content){
		if(content>=0 && content<=9){
			TILE_CONTENT = content;
		}else{
			TILE_CONTENT = -1;
		}
	}
	public void setState(int state){
		if(state>=0){
			this.tileState = state;
		}else{
			this.tileState = -1;
		}
	}
	public int getState(){
		return this.tileState; 
	}
	public boolean isValid(){
		if(TILE_CONTENT<0 || this.tileState<0)
			return false;
		return true;
	}
}

class TilePanel{
	public final int PANEL_WIDTH, PANEL_HEIGHT;
	public final int MINE_NUM;
	public TilePanel(int x,int y, int num){
		PANEL_WIDTH = x;
		PANEL_HEIGHT = y;
		MINE_NUM = num;
	}
}