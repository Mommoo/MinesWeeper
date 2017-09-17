package com.mommoo.component;
/**
 * 
 * @author mommoo
 *
 *
 * This class manage of positive matrix.
 * if matrix elements have a negative value, this class is invalid
 * not provide setter, so this class is immutable 
 * 
 */
public class MatrixInfo{
	private final int ROW,COL;
	private static final int MAXIMUM_DIRECTION_CNT = MatrixDirection.values().length;
	private boolean isValid;
	public MatrixInfo(final int row, final int col){
		isValid = (row >=0 && col >=0);
		if(isValid){
			this.ROW = row;
			this.COL = col;
		}else{
			this.ROW = -1;
			this.COL = -1;
		}
	}
	
	/**
	 * @param matrixDirection
	 * @return at user's wanted direction, moved MatrixInfo's instance;
	 */
	public MatrixInfo moveMatrix(MatrixDirection matrixDirection){
		return matrixDirection.moveMatrix(this.ROW, this.COL);
	}

	public MatrixInfo[] moveAllDirectionMatrix(){
		return new MatrixInfo[]{
				moveMatrix(MatrixInfo.MatrixDirection.LEFT_TOP),
				moveMatrix(MatrixInfo.MatrixDirection.LEFT),
				moveMatrix(MatrixInfo.MatrixDirection.LEFT_BOTTOM),

				moveMatrix(MatrixInfo.MatrixDirection.MIDDLE_TOP),
				moveMatrix(MatrixInfo.MatrixDirection.MIDDLE_BOTTOM),

				moveMatrix(MatrixInfo.MatrixDirection.RIGHT_TOP),
				moveMatrix(MatrixInfo.MatrixDirection.RIGHT),
				moveMatrix(MatrixInfo.MatrixDirection.RIGHT_BOTTOM)
		};
	}

	public int getRow(){
		return ROW;
	}

	public int getCol(){
		return COL;
	}

	public static int getMaximumDirectionCount(){
		return MAXIMUM_DIRECTION_CNT;
	}

	public boolean isValid(final int maxRow, final int maxCol){
		return isValid && ROW < maxRow && COL < maxCol;
	}


	@Override
	public boolean equals(Object o){
		if(o == null || !(o instanceof MatrixInfo)){
			return false;
		}
		MatrixInfo tempMatrixInfo = (MatrixInfo)o;
		return this.ROW == tempMatrixInfo.ROW && this.COL == tempMatrixInfo.COL;
	}
	
	public enum MatrixDirection{
		LEFT_TOP(-1,-1),LEFT(0,-1),LEFT_BOTTOM(+1,-1),
		MIDDLE_TOP(+1,0),MIDDLE_BOTTOM(-1,0),
		RIGHT_TOP(-1,+1),RIGHT(0,+1),RIGHT_BOTTOM(+1,+1);
		
		private final int ROW_DIRECTION,COL_DIRECTION;
		
		private MatrixDirection(int rowDirection, int colDirection){
			this.ROW_DIRECTION = rowDirection;
			this.COL_DIRECTION = colDirection;
		}
		
		private MatrixInfo moveMatrix(int originalRow, int originalCol){
			return new MatrixInfo(originalRow + this.ROW_DIRECTION,originalCol + this.COL_DIRECTION);
		}
	}
}