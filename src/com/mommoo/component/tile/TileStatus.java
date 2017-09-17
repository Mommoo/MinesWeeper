package com.mommoo.component.tile;

import java.util.HashMap;

/**
 * 
 * @author mommoo
 *
 * This class is that express tile status
 * 
 */
public class TileStatus {
    private static final int INNER_ELEMENT_SIZE = InnerElement.values().length;
    private static final int OUTER_ELEMENT_SIZE = OuterElement.values().length;
    private final InnerElement INNER_ELEMENT;
    private final OuterElement OUTER_ELEMENT;
    private static final HashMap<Integer,TileStatus> TILE_STATUS_POOL = new HashMap<>();


    /* Block external generation */
    private TileStatus(int innerElement, int outerElement){
        this.INNER_ELEMENT = InnerElement.values()[innerElement];
        this.OUTER_ELEMENT = OuterElement.values()[outerElement];
    }

    public static TileStatus createTile(int innerElement, int outerElement){
        if(innerElement >= INNER_ELEMENT_SIZE || outerElement >= OUTER_ELEMENT_SIZE) return null;
        int key = innerElement*10 + outerElement;
        /* If hashMap have a value of key, return value, but not have it, create new value using by key and return value of key*/
        return TILE_STATUS_POOL.computeIfAbsent(key, k->new TileStatus(innerElement,outerElement));
    }

    public InnerElement getInnerElement(){
        return INNER_ELEMENT;
    }

    public OuterElement getOuterElement(){
        return OUTER_ELEMENT;
    }
}
