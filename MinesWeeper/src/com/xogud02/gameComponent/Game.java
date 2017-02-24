package com.xogud02.gameComponent;

import java.util.HashSet;
import java.util.Random;

public class Game {
	public static void main(String[] args) {
		TilePanel tp = new TilePanel(10,15,20);
		System.out.println(tp);
	}
}

class Tile{
	public enum TileContent{
		EMPTY,ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,MINE;
	}
	public enum TileState{
		HIDDEN, REVEALED, FLAGGED;
	}
	
	private final TileContent TILE_CONTENT;
	private TileState tileState;
	public Tile(final TileContent content){
		TILE_CONTENT = content;
		tileState = TileState.HIDDEN;
	}
	public void setState(final TileState state){
		tileState = state;
	}
	public TileContent getContent(){
		return TILE_CONTENT;
	}
	public TileState getState(){
		return tileState; 
	}
	@Override
	public String toString(){
		if(TILE_CONTENT == TileContent.MINE){
			return "* ";
		}
		if(TILE_CONTENT == TileContent.EMPTY){
			return "бр ";
		}
		return TILE_CONTENT.ordinal()+" ";
	}
}

class TilePanel{
	private final int PANEL_ROW, PANEL_COL;
	private static final int MIN_PANEL_ROW = 10, MIN_PANEL_COL = 10;
	private static final int MAX_PANEL_ROW = 100, MAX_PANEL_COL = 100;
	private final int MINE_NUM;
	private final boolean[][] minePlanted;
	private static final Random r = new Random();
	private Tile[][] tiles;
	public TilePanel(final int row,final int col,final int mineNum){
		if(row>=MIN_PANEL_ROW && col>=MIN_PANEL_COL &&row<=MAX_PANEL_ROW && col <= MAX_PANEL_COL && row*col >= mineNum){
			PANEL_ROW = row;
			PANEL_COL = col;
			MINE_NUM = mineNum;
			minePlanted = new boolean[PANEL_ROW][PANEL_COL];
			tiles = new Tile[PANEL_ROW][PANEL_COL];
			plantMines(mineNum);
			setTiles();
		}else{
			PANEL_ROW = -1;
			PANEL_COL = -1;
			MINE_NUM = -1;
			minePlanted = new boolean[1][1];
			System.out.println("ERROR : public TilePanel(...)");
		}
	}
	private void plantMines(final int MineNum){
		final HashSet<Integer> HASH_SET = new HashSet<>();
		int tmpMul, tmpRow, tmpCol;
		while(HASH_SET.size()<MineNum){
			tmpMul = r.nextInt(PANEL_ROW * PANEL_COL);
			tmpRow = tmpMul/PANEL_COL;
			tmpCol = tmpMul%PANEL_COL;
			minePlanted[tmpRow][tmpCol] = true;
			HASH_SET.add(tmpMul);
		}
	}
	private void setTiles(){
		final int TMP_CONTENT[][] = MatrixCalculator.getMineHintCounter(minePlanted);
		for(int i=0;i<PANEL_ROW;i++){
			for(int j=0;j<PANEL_COL;j++){
				if(minePlanted[i][j]){
					tiles[i][j] = new Tile(Tile.TileContent.MINE);
				}else{
					tiles[i][j] = new Tile(Tile.TileContent.values()[TMP_CONTENT[i][j]]);
				}
			}
		}
	}
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<PANEL_ROW;i++){
			for(int j=0;j<PANEL_COL;j++){
				sb.append(tiles[i][j]);
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}

class MatrixCalculator{
	private MatrixCalculator(){};
	private static int maxRow=0,maxCol=0;
	private static int[][] tmpMatrix;
	public static int[][] getMineHintCounter(final boolean[][] minePlanted){
		maxRow = minePlanted.length;
		maxCol = minePlanted[0].length;
		tmpMatrix = new int[maxRow][maxCol];
		for(int i=0;i<maxRow;i++){
			for(int j=0;j<maxCol;j++){
				if(minePlanted[i][j]){
					addFourDirection(i,j);
				}
			}
		}
		return tmpMatrix;
	}
	private static void addFourDirection(final int row, final int col){
		addHere(row+1,col);
		addHere(row-1,col);
		addHere(row,col+1);
		addHere(row,col-1);
		addHere(row+1,col+1);
		addHere(row+1,col-1);
		addHere(row-1,col+1);
		addHere(row-1,col-1);
	}
	private static void addHere(final int row, final int col){
		if(isValid(row,col)){
			tmpMatrix[row][col]++;
		}
	}
	private static boolean isValid(final int row, final int col){
		return row>=0 && row<maxRow && col >= 0 && col < maxCol;
	}
}