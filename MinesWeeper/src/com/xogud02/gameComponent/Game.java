package com.xogud02.gameComponent;

import java.util.Random;

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

class Panel{
	public final int PANEL_WIDTH, PANEL_HEIGHT;
	public final int MINE_NUM;
	private boolean[][] minePlanted;
	private static final Random r = new Random();
	public Panel(int x,int y, int num){
		if(x>0 && y>0 && x*y >= num){
			PANEL_WIDTH = x;
			PANEL_HEIGHT = y;
			MINE_NUM = num;
			minePlanted = new boolean[PANEL_WIDTH][PANEL_HEIGHT];
		}else{
			PANEL_WIDTH = -1;
			PANEL_HEIGHT = -1;
			MINE_NUM = -1;
		}
	}
	private void PlantMines(int MineNum){
		if(MineNum>0){
			int tmpX = r.nextInt(PANEL_WIDTH);
			int tmpY = r.nextInt(PANEL_HEIGHT);
			if(PlantMine(tmpX,tmpY)){
				PlantMines(MineNum-1);
			}else{
				PlantMines(MineNum);
			}
		}
	}
	private boolean PlantMine(int x,int y){
		if(x>0 && x<PANEL_WIDTH  && y>0 && y<PANEL_HEIGHT && !minePlanted[x][y]){
			minePlanted[x][y] = true;
			return true;
		}
		return false;
	}
}