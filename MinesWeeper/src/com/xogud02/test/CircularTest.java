package com.xogud02.test;

public class CircularTest {
	public static void main(String[] args) {
		System.out.println("Aa".compareTo("가c"));
	}
}

class Foot{
	private final int IDX;
	private static final int MAX_FOOT_NUM = 4;
	private static int cnt = 0;
	private static final Foot[] FEET = new Foot[4];
	static{
		for(int i=0;i<MAX_FOOT_NUM;i++){
			FEET[i] = new Foot();
		}
	}
	private Foot(){
		IDX = cnt++; 
	}
	public static Foot getInstance(final int idx){
		if(idx>=0)
			return FEET[idx%MAX_FOOT_NUM];
		return null;
	}
	@Override
	public String toString(){
		return IDX + "번째 발자국";
	}
}