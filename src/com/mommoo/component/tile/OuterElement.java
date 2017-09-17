package com.mommoo.component.tile;

/**
 * Created by mommoo on 2017-04-20.
 */
public enum OuterElement {
    EMPTY,FLAG,QUESTION;
    private static int size;
    public static int getCount(){
        if(size == 0) size = OuterElement.values().length;
        return size;
    }
}
