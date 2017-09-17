package com.mommoo.game.screen.view.game;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import com.mommoo.component.MatrixInfo;
import com.mommoo.component.tile.InnerElement;
import com.mommoo.component.tile.OuterElement;
import com.mommoo.component.tile.TileStatus;
import com.mommoo.game.screen.AppScreen;
import com.mommoo.game.screen.view.dialog.SimpleDialog;
import com.mommoo.main.GameDescription;

/**
 * @author mommoo
 *
 * This class manage of tile objects
 */

public class GamePanel extends JPanel implements ControlListener {
	private final NavigationView NAVIGATION_VIEW;
	private final GameView GAME_VIEW;
	private MatrixInfo[] allDirectionMatrix;
	private final int MAX_ROW,MAX_COL,MAX_MINE;
	private int realRemainMine;
	private int remainNotCheckedTile;

	public GamePanel(final int tileRow, final int tileColumn, final int mineCnt){
		this.MAX_ROW = tileRow;
		this.MAX_COL = tileColumn;
		this.MAX_MINE = mineCnt;
		this.NAVIGATION_VIEW = new NavigationView(mineCnt);
		this.GAME_VIEW = new GameView(tileRow,tileColumn,mineCnt,this);
		this.realRemainMine = mineCnt;
		this.remainNotCheckedTile = MAX_ROW*MAX_COL;
		System.out.println("MAX Remain not checked tile : " + remainNotCheckedTile);
		final int PADDING = GameDescription.GAME_VIEW_PADDING;
		setLayout(new BorderLayout(0,PADDING));
		setBorder(BorderFactory.createEmptyBorder(PADDING,PADDING,PADDING,PADDING));
		add(NAVIGATION_VIEW,BorderLayout.NORTH);
		add(GAME_VIEW);
	}

	@Override
	public void tileOpened(TileObject targetTile) {
		System.out.println("[TileOpen] 남은 마인의 갯수 : "+NAVIGATION_VIEW.getMineCnt() +", 남은 타일의 갯수 : "+ remainNotCheckedTile);
		checkGameEnd();
		remainNotCheckedTile--;
		final MatrixInfo[] ALL_DIRECTION_MATRIX = GAME_VIEW.getMatrixInfo(targetTile).moveAllDirectionMatrix();
		ArrayList<TileObject> cacheList = new ArrayList<>();
		for(MatrixInfo matrix : ALL_DIRECTION_MATRIX){
			if(matrix.isValid(MAX_ROW , MAX_COL)){
				TileObject tileObject = GAME_VIEW.getTileObject(matrix.getRow(),matrix.getCol());
				cacheList.add(tileObject);
				if(tileObject.getTile().getInnerElement() == InnerElement.MINE) return;
			}
		}

		for(TileObject tileObject : cacheList) tileObject.open();
	}

	@Override
	public void tileOuterChanged(TileObject targetTile) {
		TileStatus tileStatus = targetTile.getTile();
		if(tileStatus.getOuterElement() == OuterElement.FLAG) {
			boolean isCorrect = tileStatus.getInnerElement() == InnerElement.MINE;
			if(isCorrect) realRemainMine--;
			NAVIGATION_VIEW.decreaseMine();
			remainNotCheckedTile--;
		}
		else if ( tileStatus.getOuterElement() == OuterElement.QUESTION) {
			if ( tileStatus.getInnerElement() == InnerElement.MINE ) realRemainMine++;
			NAVIGATION_VIEW.increaseMine();
		}

		//System.out.println("[TileOuterChange] 남은 마인의 갯수 : "+NAVIGATION_VIEW.getMineCnt() +", 남은 타일의 갯수 : "+ remainNotCheckedTile);
		checkGameEnd();
	}

	@Override
	public void markHint(TileObject targetTile) {
		allDirectionMatrix = GAME_VIEW.getMatrixInfo(targetTile).moveAllDirectionMatrix();
		for(MatrixInfo matrix : allDirectionMatrix){
			if(matrix.isValid(MAX_ROW,MAX_COL)) GAME_VIEW.getTileObject(matrix.getRow(),matrix.getCol()).pressTile();
		}
	}

	@Override
	public void openHint(TileObject targetTile) {
		int flagCount = 0;
		int hintNumber = targetTile.getTile().getInnerElement().ordinal();
		ArrayList<TileObject> cacheList = new ArrayList<>();
		for(MatrixInfo matrix : allDirectionMatrix){
			if(matrix.isValid(MAX_ROW,MAX_COL)) {
				TileObject targetObject = GAME_VIEW.getTileObject(matrix.getRow(),matrix.getCol());
				targetObject.releaseTile();
				if(targetObject.getTile().getOuterElement() == OuterElement.FLAG) flagCount++;
				else cacheList.add(targetObject);
			}
		}

		/*
		 * If hint number and flag count equals,
		 * User think that another tile that doesn't have outer mark of tile is be a normal tile
		 * So, user don't need to click tile.
		 * As a result we can auto open tile regardless of whether set flag correctly
		 */
		if(hintNumber == flagCount) {
			/* Open a tile that not have outer mark */
			for(TileObject tileObject : cacheList) tileObject.open();
		}
	}

	@Override
	public void bombExploded(TileObject targetTile) {
		lockGameControl();
		openAllTile();
		checkAllTileIsCorrectPutFlag();
		NAVIGATION_VIEW.stopTime();
		showResultDialog(false);
	}

	private void reStart(){
		realRemainMine = MAX_MINE;
		remainNotCheckedTile = MAX_COL * MAX_ROW;
		GAME_VIEW.init();
		NAVIGATION_VIEW.reset();
	}


	private void lockGameControl(){
		GAME_VIEW.applyAll(TileObject::lock);
	}

	private void openAllTile(){
		GAME_VIEW.applyAll(TileObject::open);
	}

	private void checkAllTileIsCorrectPutFlag(){
		GAME_VIEW.applyAll(TileObject::checkIsCorrectPutFlag);
	}

	private void checkGameEnd(){
		if(remainNotCheckedTile != 0) return;
		/* If remainNotCheckedTile is 0 ,it is means that user inspected all tiles */
		else{
			lockGameControl();
			openAllTile();
			NAVIGATION_VIEW.stopTime();
			showResultDialog(realRemainMine == 0 && NAVIGATION_VIEW.getMineCnt() == 0);
		}
	}

	private void showResultDialog(boolean success){
		JButton button = new JButton("RESTART");
		String result = success?"Success!!":"Failed!!...";
		SimpleDialog dialog = new SimpleDialog.Builder()
				.setTitle("Game End")
				.setMessage("<html><h1 style='font-size : 80pt'>"+result+"</h1>" +
						"<div style='font-size : 20pt'>" +
						"<div style='margin-bottom:10pt'>Time taken : <span style='color:"+GameDescription.MAIN_COLOR_CODE+"'>"+NAVIGATION_VIEW.getTime()+"</span></div>"+
						"<div style='margin-bottom:10pt'>Mine found : <span style='color:"+GameDescription.MAIN_COLOR_CODE+"'>"+(MAX_MINE - realRemainMine)+"</span></div>"+
						"<div style='margin-bottom:10pt'>If you want try again, press '<span style='color:"+GameDescription.MAIN_COLOR_CODE+"'>RESTART</span>' button</div>" +
						"</div>"+
						"</html>")
				.addButton(button)
				.setLocation(new Point(AppScreen.getLocation().x + AppScreen.getSize().width , AppScreen.getLocation().y))
				.build();

		dialog.show();

		button.addActionListener((e)->{
			reStart();
			dialog.hide();
		});
	}
}