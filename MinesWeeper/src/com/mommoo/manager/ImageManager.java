package com.mommoo.manager;

import java.awt.Image;
import java.awt.Toolkit;

public class ImageManager {
	public static Image BOMB;
	static{
		if(ImageManager.class.getResource("/image/bomb.png") == null){
			BOMB = Toolkit.getDefaultToolkit().getImage("image/bomb.png");
			System.out.println(BOMB == null);
		}
	}
}
