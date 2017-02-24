package com.mommoo.game.component;
/**
 * 
 * @author mommoo
 *
 * This class has a role in MinesWeeper's game tile object
 * Tile objects have properties that InnerElement and OuterElement
 * 
 */
public class Tile {
	
	public enum InnerElement{
		EMPTY,ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,MINE;
	}
	
	public enum OuterElement{
		NONE,FLAG,QUESTION;
	}
	
	private final InnerElement INNER_ELEMENT;
	private static final OuterElement[] OUTER_ELEMENT_ARRAY = OuterElement.values();
	private int outerTileElementPosition = 0;
	private boolean isShow;
	public Tile(InnerElement elem){
		this.INNER_ELEMENT = elem;
	}
	
	/**
	 *  @public API
	 */
	
	/** If user click mouse left button, show innerElement [number or mine] */
	public InnerElement show(){
		this.isShow = true;
		return this.INNER_ELEMENT;
	}
	
	public boolean isShow(){
		return isShow;
	}
	
	/** If user click mouse right button, change outer-tile state */
	public OuterElement changeOuterTile(){
		if(++outerTileElementPosition == OUTER_ELEMENT_ARRAY.length){
			outerTileElementPosition = 0;
		}
		return OUTER_ELEMENT_ARRAY[outerTileElementPosition];
	}
}
