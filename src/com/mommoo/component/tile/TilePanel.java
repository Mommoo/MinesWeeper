package com.mommoo.component.tile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import com.mommoo.component.MatrixInfo;
import com.mommoo.component.MatrixInfo.MatrixDirection;
/**
 * @author mommoo
 *
 */
public class TilePanel {
	private final int PANEL_COL, PANEL_ROW, MINE_CNT;
	private final int MAX_COL = 20, MAX_ROW = 20;
	private final int MIN_COL = 1, MIN_ROW = 1, MIN_MINE_CNT = MIN_COL*MIN_ROW;
	private final ElementManager ELEMENT_MANAGER;

	public TilePanel(final int col,final int row,final int mineCnt){
		isValid(col,row,mineCnt);
		this.PANEL_COL = col;
		this.PANEL_ROW = row;
		this.MINE_CNT = mineCnt;
		this.ELEMENT_MANAGER = new ElementManager();
		this.ELEMENT_MANAGER.plantMine();
		this.ELEMENT_MANAGER.plantHintNumber();
	}
	
	/** If col,row and mineCnt are invalid condition, fire error */
	private void isValid(final int col,final int row,final int mineCnt){
		if(col>=MIN_COL&&col<=MAX_COL
			&&row>=MIN_ROW&&row<=MAX_ROW
			&&mineCnt>=MIN_MINE_CNT&&mineCnt<=col*row/2){
		} else
			try {
				throw new Exception("col is valid from "+MIN_COL+" to "+MAX_COL+"\n"
						+ "row is valid from "+MIN_ROW+" to "+MAX_ROW+"\n"
						+ "mineCnt is valid from "+MIN_MINE_CNT+" to " +col*row/2);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/*  To manage element efficiently, ElementManager class was created  */
	private final class ElementManager{
		
		private final int[][] ELEMENT_REPOSITORY = new int[PANEL_ROW][PANEL_COL];
		private final ArrayList<MatrixInfo> MINE_MATRIX_LIST = new ArrayList<>();
		private final int MINE = InnerElement.MINE.ordinal();
		
		private void plantMine(){
			final HashSet<Integer> HASH_SET = new HashSet<>();
			final Random R = new Random();
			int randomCnt = 0;
			while(HASH_SET.size()<MINE_CNT){
				randomCnt = R.nextInt(PANEL_COL*PANEL_ROW);
				if(HASH_SET.add(randomCnt)){
					int row = randomCnt/PANEL_ROW;
					int col = randomCnt%PANEL_COL;
					/* Save coordinate of mine to use cache data later */
					MINE_MATRIX_LIST.add(new MatrixInfo(row,col));
					/* Mark to mine using by MINE_SYMBOL */
					ELEMENT_REPOSITORY[row][col] = MINE;
				}
			}
		}
		
		private boolean isMine(int row,int col){
			return ELEMENT_REPOSITORY[row][col] == MINE;
		}
		
		private void plantHintNumber(){
			for(MatrixInfo mineMatrix : MINE_MATRIX_LIST){
				
				final MatrixInfo[] ALL_DIRECTION_MATRIX_ARRAY = mineMatrix.moveAllDirectionMatrix();
				
				for(MatrixInfo movedMatrix : ALL_DIRECTION_MATRIX_ARRAY){
					if(movedMatrix.isValid(PANEL_ROW, PANEL_COL)){
						final int ROW = movedMatrix.getRow();
						final int COL = movedMatrix.getCol();
						if(!isMine(ROW,COL)) ELEMENT_REPOSITORY[ROW][COL]++;
					}
				}
			}
		}
		
		@Override
		public String toString(){
			final StringBuilder BLUE_PRINT = new StringBuilder();
			BLUE_PRINT.append("********** element-blueprint **********\n\n");
			for(int row=0; row<PANEL_ROW; row++){
				for(int col=0; col<PANEL_COL; col++){
					int hint = ELEMENT_REPOSITORY[row][col];
					BLUE_PRINT.append(hint == MINE?"* ":hint+" ");
				}
				BLUE_PRINT.append("\n");
			}
			BLUE_PRINT.append("\n");
			return BLUE_PRINT.toString();
		}
	}
	
	/**
	 *  @public API
	 * */

	public int getElement(int row, int col){
		return ELEMENT_MANAGER.ELEMENT_REPOSITORY[row][col];
	}

	@Override
	public String toString(){
		return new StringBuilder()
				.append("********** panel-info **********\n\n")
				.append("row : ")
				.append(PANEL_ROW)
				.append("\n")
				.append("col : ")
				.append(PANEL_COL)
				.append("\n")
				.append("mine count : ")
				.append(MINE_CNT)
				.append("\n\n")
				.append(this.ELEMENT_MANAGER.toString())
				.toString();
	}
}
