package com.xogud02.test;

public class MySingleTon {
	private static final int MAX_INSTANCE_NUM = 2;
	private final int INSTANCE_INDEX;
	private static int INSTANCE_COUNT = -1;
	private static MySingleTon INVALID_INSTANCE = new MySingleTon();
	private static MySingleTon[] MY_INSTANCE = new MySingleTon[MAX_INSTANCE_NUM];
	private static void makeInstance(){
		for(int i=0;i<MAX_INSTANCE_NUM;i++){
			MY_INSTANCE[i] = new MySingleTon();
		}
	}
	static{
		makeInstance();
	}
	private MySingleTon(){
		INSTANCE_INDEX = INSTANCE_COUNT++;
	}
	public static MySingleTon getInstance(int idx){
		if(idx>=0 && idx<=MAX_INSTANCE_NUM){
			return MY_INSTANCE[idx];	
		}
		return INVALID_INSTANCE;
	}
	@Override
	public String toString(){
		return "INSTANCE INDEX : "+INSTANCE_INDEX;
	}
	@Override
	public boolean equals(Object o){
		if(o != null && o instanceof MySingleTon){
			return ((MySingleTon)o).INSTANCE_INDEX == this.INSTANCE_INDEX;
		}
		return false;
	}
	public static void main(String[] args) {
		System.out.println(MySingleTon.getInstance(5));
	}
}
