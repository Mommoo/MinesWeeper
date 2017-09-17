package com.mommoo.main;

import javax.swing.SwingUtilities;

import com.mommoo.game.screen.AppScreen;

public class GameRunner {
	public static void main(String[] args){
		SwingUtilities.invokeLater(() -> AppScreen.show());
	}
}
