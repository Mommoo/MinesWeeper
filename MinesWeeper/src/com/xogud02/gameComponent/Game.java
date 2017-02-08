package com.xogud02.gameComponent;

public class Game {

}

class Tile{
	private final boolean HAVE_MINE;
	
	private boolean clicked = false;
	private boolean flagged = false;
	public Tile(boolean haveMine){
		HAVE_MINE = haveMine;
	}
	public boolean isClicked(){
		return clicked;
	}
	public boolean isFlagged(){
		return flagged;
	}
	public boolean havaMine(){
		return HAVE_MINE;
	}
	public void setClicked(){
		clicked = true;
	}
	public void setflagged(){
		flagged = !flagged;
	}
}