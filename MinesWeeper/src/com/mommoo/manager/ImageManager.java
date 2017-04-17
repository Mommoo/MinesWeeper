package com.mommoo.manager;

import java.awt.Image;
import java.awt.Toolkit;

public class ImageManager {
	public static Image BOMB;
	public static Image INTRO_BACKGROUND;
	public static Image FLAG;
	public static Image QUESTION;
	static{
		if(ImageManager.class.getResource("/image/bomb.png") == null){
			BOMB = Toolkit.getDefaultToolkit().getImage("image/bomb.png");
			INTRO_BACKGROUND = Toolkit.getDefaultToolkit().getImage("image/background.png");
			FLAG = Toolkit.getDefaultToolkit().getImage("image/flag.png");
			QUESTION = Toolkit.getDefaultToolkit().getImage("image/question.png");
		}
	}
}
