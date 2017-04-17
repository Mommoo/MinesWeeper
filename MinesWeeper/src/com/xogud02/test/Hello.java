package com.xogud02.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Hello {
	public static void main(String[] args) {
		HashSet<Integer> set = new HashSet<Integer>();
		Random r = new Random();
		while(set.size()<20){
			set.add(r.nextInt(50));
		}
		ArrayList<Integer> list = new ArrayList<>(set);
		System.out.println(list);
	}
	
	
}
