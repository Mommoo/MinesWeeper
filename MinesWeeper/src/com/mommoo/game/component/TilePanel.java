package com.mommoo.game.component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import com.mommoo.game.component.MatrixInfo.MatrixDirection;
import com.mommoo.game.component.Tile.InnerElement;
/**
 * 
 * @author mommoo
 *
 */
public class TilePanel {
	public final int PANEL_COL, PANEL_ROW, MINE_CNT;
	public final int MAX_COL = 20, MAX_ROW = 20, MAX_MINE_CNT = MAX_COL*MAX_ROW/2;
	public final int MIN_COL = 1, MIN_ROW = 1, MIN_MINE_CNT = MIN_COL*MIN_ROW;
	private final ElementManager ELEMENT_MANAGER;
	
	public static void main(String[] args){
		TilePanel mine = new TilePanel(10,10,50);
		/** Debugging */
		System.out.println(mine);
	}
	
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
	
	/**
	 *  To manage element efficiently, ElementManager class was created 
	 * */
	private final class ElementManager{
		
		private final int[][] ELEMENT_REPOSITORY = new int[PANEL_COL][PANEL_ROW];
		private final InnerElement[][] INNER_ELEMENT_REPOSITORY = new InnerElement[PANEL_COL][PANEL_ROW];
		private final ArrayList<MatrixInfo> MINE_MATRIX_LIST = new ArrayList<>();
		/** {@link #Tile.InnerElement}*/
		private final int MINE_SYMBOL = InnerElement.MINE.ordinal();
		
		private void plantMine(){
			final HashSet<Integer> HASH_SET = new HashSet<>();
			final Random R = new Random();
			int randomCnt = 0;
			while(HASH_SET.size()<MINE_CNT){
				randomCnt = R.nextInt(PANEL_COL*PANEL_ROW);
				if(HASH_SET.add(randomCnt)){
					int row = randomCnt/PANEL_COL;
					int col = randomCnt%PANEL_ROW;
					/** save coordinate of mine to use cache data later */
					MINE_MATRIX_LIST.add(new MatrixInfo(row,col));
					/** mark to mine using by MINE_SYMBOL */
					ELEMENT_REPOSITORY[row][col] = MINE_SYMBOL;
				}
			}
		}
		
		private boolean isMine(int row,int col){
			return ELEMENT_REPOSITORY[row][col] == MINE_SYMBOL;
		}
		
		private void plantHintNumber(){
			for(MatrixInfo mineMatrix : MINE_MATRIX_LIST){
				
				final MatrixInfo[] ALL_DIRECTION_MATRIX_ARRAY = new MatrixInfo[]{
						mineMatrix.moveMatrix(MatrixDirection.LEFT_TOP),
						mineMatrix.moveMatrix(MatrixDirection.LEFT),
						mineMatrix.moveMatrix(MatrixDirection.LEFT_BOTTOM),
						mineMatrix.moveMatrix(MatrixDirection.MIDDLE_TOP),
						mineMatrix.moveMatrix(MatrixDirection.MIDDLE_BOTTOM),
						mineMatrix.moveMatrix(MatrixDirection.RIGHT_TOP),
						mineMatrix.moveMatrix(MatrixDirection.RIGHT),
						mineMatrix.moveMatrix(MatrixDirection.RIGHT_BOTTOM)};
				
				for(MatrixInfo movedMatrix : ALL_DIRECTION_MATRIX_ARRAY){
					if(movedMatrix.isValid(PANEL_ROW-1, PANEL_COL-1)){
						if(!isMine(movedMatrix.ROW,movedMatrix.COL))
							ELEMENT_REPOSITORY[movedMatrix.ROW][movedMatrix.COL]++;
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
					BLUE_PRINT.append(hint == MINE_SYMBOL?"■ ":hint+" ");
				}
				BLUE_PRINT.append("\n");
			}
			BLUE_PRINT.append("\n")
			.append("********** element-type-blueprint **********\n\n");
			for(InnerElement[] t : valueOfInnerElementArray()){
				for(InnerElement elem : t){
					BLUE_PRINT.append(elem).append(" ");
				}
				BLUE_PRINT.append("\n");
			}
			new SimpleDateFormat("yyyy");
			return BLUE_PRINT.toString();
		}
	}
	
	/**
	 *  @public API
	 * */
	@Override
	public String toString(){
		final StringBuilder SB = new StringBuilder();
		SB.append("********** panel-info **********\n\n")
		.append("row : ")
		.append(PANEL_ROW)
		.append("\n")
		.append("col : ")
		.append(PANEL_COL)
		.append("\n")
		.append("mine count : ")
		.append(MINE_CNT)
		.append("\n\n")
		.append(this.ELEMENT_MANAGER.toString());
		return SB.toString();
	}
	
	public InnerElement[][] valueOfInnerElementArray(){
		if(this.ELEMENT_MANAGER.INNER_ELEMENT_REPOSITORY[0][0] == null){
			InnerElement[] tempArray = InnerElement.values();
			int rowNo=0,colNo=0;
			for(int[] cols : this.ELEMENT_MANAGER.ELEMENT_REPOSITORY){
				for(int elementNum : cols){
					this.ELEMENT_MANAGER.INNER_ELEMENT_REPOSITORY[rowNo][colNo] = tempArray[elementNum]; 
					colNo++;
				}
				rowNo++;
				colNo = 0;
			}
		}
		return this.ELEMENT_MANAGER.INNER_ELEMENT_REPOSITORY;
	}
}
