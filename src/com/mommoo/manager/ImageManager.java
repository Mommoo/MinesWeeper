package com.mommoo.manager;

import java.awt.Image;
import java.awt.Toolkit;

public class ImageManager {
	public static Image BOMB;
	public static Image INTRO_BACKGROUND;
	public static Image FLAG;
	public static Image QUESTION;
	public static final Image[] NUMBERS = new Image[8];
	public static Image X;
	static{
		if(ImageManager.class.getResource("/image/bomb.png") == null){
			BOMB = Toolkit.getDefaultToolkit().getImage("image/bomb.png");
			INTRO_BACKGROUND = Toolkit.getDefaultToolkit().getImage("image/background.png");
			FLAG = Toolkit.getDefaultToolkit().getImage("image/flag.png");
			QUESTION = Toolkit.getDefaultToolkit().getImage("image/question.png");

			for(int i = 0, size = NUMBERS.length ; i < size ; i++) {
				NUMBERS[i] = Toolkit.getDefaultToolkit().getImage("image/" + (i + 1) + ".png");
			}

			X = Toolkit.getDefaultToolkit().getImage("image/x.png");
		}
	}
}
