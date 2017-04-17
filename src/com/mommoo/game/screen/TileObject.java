package com.mommoo.game.screen;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.mommoo.game.component.Tile;

public class TileObject extends JPanel implements MouseListener{
	
	private final Tile TILE;
	
	public TileObject(Tile tile){
		this.TILE = tile;
		this.setBackground(Color.RED);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse Click!!!");
		if(e.getButton() == MouseEvent.BUTTON1){
			System.out.println("1");
		}else if(e.getButton() == MouseEvent.BUTTON2){
			System.out.println("2");
		}else if(e.getButton() == MouseEvent.BUTTON3){
			System.out.println("3");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Mouse press!!!");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
