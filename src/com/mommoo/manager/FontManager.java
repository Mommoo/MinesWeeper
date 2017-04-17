package com.mommoo.manager;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class FontManager {
	private static final File TRACK_FONT_FILE = new File("font/Track.ttf");
	public static Font getTrackFont(int fontStyle, int size){
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, TRACK_FONT_FILE);
			font = font.deriveFont(fontStyle, size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return font;
	}
}
