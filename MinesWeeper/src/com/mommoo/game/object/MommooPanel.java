package com.mommoo.game.object;

import java.util.HashSet;
import java.util.Random;
/**
 * 
 * @author mommoo
 *
 */
public class MommooPanel {
	public final int PANEL_COL, PANEL_ROW, MINE_CNT;
	public final int MAX_COL = 7, MAX_ROW = 7, MAX_MINE_CNT = MAX_COL*MAX_ROW/2;
	public final int MIN_COL = 1, MIN_ROW = 1, MIN_MINE_CNT = MIN_COL*MIN_ROW;
	private final MinePlant MINE_PLANT;
	
	public static void main(String[] args){
		MommooPanel mine = new MommooPanel(7,7,20);
		/** Debugging */
		System.out.println(mine);
	}
	
	public MommooPanel(final int col,final int row,final int mineCnt){
		isValid(col,row,mineCnt);
		this.PANEL_COL = col;
		this.PANEL_ROW = row;
		this.MINE_CNT = mineCnt;
		this.MINE_PLANT = new MinePlant();
		this.MINE_PLANT.plantMine();
		/** Debugging */
		System.out.println(this.MINE_PLANT);
	}
	
	/** If col,row and mineCnt are invalid condition, fire error */
	private void isValid(final int col,final int row,final int mineCnt){
		if(col>=MIN_COL&&col<=MAX_COL
			&&row>=MIN_ROW&&row<=MAX_ROW
			&&mineCnt>=MIN_MINE_CNT&&mineCnt<=MAX_MINE_CNT){
		} else
			try {
				throw new Exception("col is valid from "+MIN_COL+" to "+MAX_COL+"\n"
						+ "row is valid from "+MIN_ROW+" to "+MAX_ROW+"\n"
						+ "mineCnt is valid from "+MIN_MINE_CNT+" to " +MAX_MINE_CNT);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**
	 *  To manage mine efficiently, MinePlant class is created 
	 * */
	private final class MinePlant{
		private final boolean[][] MINE_REPOSITORY = new boolean[PANEL_COL][PANEL_ROW];
		
		private void plantMine(){
			final HashSet<Integer> HASH_SET = new HashSet<>();
			final Random R = new Random();
			int randomCnt = 0;
			while(HASH_SET.size()<MINE_CNT){
				randomCnt = R.nextInt(PANEL_COL*PANEL_ROW);
				if(HASH_SET.add(randomCnt)){
					int row = randomCnt/PANEL_COL;
					int col = randomCnt%PANEL_ROW;
					MINE_REPOSITORY[row][col] = true;
				}
			}
		}
		
		private boolean isMine(int row,int col){
			return MINE_REPOSITORY[row][col];
		}
		
		@Override
		public String toString(){
			final StringBuilder MINE_BLUE_PRINT = new StringBuilder();
			for(int row=0; row<PANEL_ROW; row++){
				for(int col=0; col<PANEL_COL; col++){
					MINE_BLUE_PRINT.append(MINE_REPOSITORY[row][col]?"бс ":"бр ");
				}
				MINE_BLUE_PRINT.append("\n");
			}
			return MINE_BLUE_PRINT.toString();
		}
	}
	
	/**
	 *  @public API
	 * */
	@Override
	public String toString(){
		return "row : "+PANEL_ROW+"\n"
				+ "col : "+PANEL_COL+"\n"
				+ "mineCnt : " +MINE_CNT;
	}
}
