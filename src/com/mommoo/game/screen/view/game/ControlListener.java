package com.mommoo.game.screen.view.game;

/**
 * Created by mommoo on 2017-04-21.
 */
public interface ControlListener {
    public void tileOpened(TileObject obj);
    public void tileOuterChanged(TileObject obj);
    public void markHint(TileObject obj);
    public void openHint(TileObject obj);
    public void bombExploded(TileObject obj);
}
