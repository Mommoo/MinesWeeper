package com.mommoo.manager;

import java.awt.Color;

public class ColorManager {
	private static final Color TRANSPARENT_COLOR = new Color(1.0f,1.0f,1.0f,0f);
	
	private ColorManager(){};
	
	public static Color getTransparentColor(){
		return TRANSPARENT_COLOR;
	}
	
	public static Color getTransparentColor(float alpha){
		return new Color(1.0f,1.0f,1.0f,alpha);
	}
}
