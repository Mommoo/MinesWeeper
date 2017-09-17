package com.mommoo.game.screen.view.game;

import com.mommoo.component.MatrixInfo;
import com.mommoo.component.tile.TilePanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.function.Consumer;

/**
 * Created by mommoo on 2017-04-19.
 */
class GameView extends JPanel {

    private final TileObject[][] TILE_OBJECT_ARRAY;
    private final HashMap<TileObject,MatrixInfo> SEARCH_MAP = new HashMap<>();
    private final int MAX_ROW,MAX_COL,MINE_CNT;
    private final ControlListener CONTROL_LISTENER;

    GameView(final int MAX_ROW, final int MAX_COL, final int MINE_CNT, ControlListener controlListener){
        this.MAX_ROW = MAX_ROW;
        this.MAX_COL = MAX_COL;
        this.MINE_CNT = MINE_CNT;
        this.CONTROL_LISTENER = controlListener;
        setLayout(new GridLayout(MAX_ROW,MAX_COL,0,0));
        TILE_OBJECT_ARRAY = new TileObject[MAX_ROW][MAX_COL];
        init();
    }

    void init(){
        TilePanel tilePanel = new TilePanel(MAX_ROW,MAX_COL,MINE_CNT);
        System.out.println(tilePanel);
        this.removeAll();
        this.SEARCH_MAP.clear();
        for(int row = 0 ; row < MAX_ROW ; row++){
            for(int col = 0 ; col < MAX_COL ; col++){
                TILE_OBJECT_ARRAY[row][col] = new TileObject(tilePanel.getElement(row, col));
                TILE_OBJECT_ARRAY[row][col].setControlListener(CONTROL_LISTENER);
                SEARCH_MAP.put(TILE_OBJECT_ARRAY[row][col],new MatrixInfo(row,col));
                add(TILE_OBJECT_ARRAY[row][col]);
            }
        }
        revalidate();
        repaint();
    }

    void applyAll(Consumer<TileObject> consumer){
        for(TileObject[] array : TILE_OBJECT_ARRAY){
            for(TileObject tileObject : array){
                consumer.accept(tileObject);
            }
        }
    }

    MatrixInfo getMatrixInfo(TileObject key){
        return SEARCH_MAP.get(key);
    }

    TileObject getTileObject(int row, int col){
        return TILE_OBJECT_ARRAY[row][col];
    }
}
