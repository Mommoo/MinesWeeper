package com.mommoo.game.screen.view.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mommoo.game.main.GameDescription;
import com.mommoo.manager.FontManager;
import com.mommoo.manager.ScreenManager;

class NavigationView extends JPanel{
	private static final ScreenManager SM = ScreenManager.getInstance();
	private final TimerView TIMER_VIEW = new TimerView();
	private final MineCountView MINE_COUNT_VIEW = new MineCountView(100);
	
	NavigationView(){
		setLayout(new BorderLayout());
		add(TIMER_VIEW,BorderLayout.WEST);
		add(MINE_COUNT_VIEW,BorderLayout.EAST);
		TIMER_VIEW.startTimer();
		setOpaque(true);
	}

	private class GuideView extends JPanel{
		
		private final JLabel VALUE_LABEL = new JLabel("",JLabel.CENTER);
//		private final int PREFERRED_WIDTH = GameDescription.DEFAULT_APP_SCREEN_WIDTH/4;
//		private final int PREFERRED_HEIGHT = GameDescription.DEFAULT_APP_SCREEN_HEIGHT/9;
		private final RoundRectangle2D RECT = new RoundRectangle2D.Double();
		private boolean once;
		
		private GuideView(String guideText){
			initValueLabel();
			setLayout(new GridLayout(2,1,0,0));
			setOpaque(false);
			setBackground(Color.WHITE);
			JLabel guideLabel = new JLabel(guideText , JLabel.CENTER);
			guideLabel.setFont(guideLabel.getFont().deriveFont((float)SM.dip2px(9)));
			guideLabel.setBorder(BorderFactory.createEmptyBorder());
			add(guideLabel);
			add(VALUE_LABEL);
		}
		
		private void initValueLabel(){
			VALUE_LABEL.setFont(FontManager.getTrackFont(Font.PLAIN, SM.dip2px(8)));
		}
		
		protected void setValue(String value){
			VALUE_LABEL.setText(value);
		}
	}
	
	private class TimerView extends GuideView{
		private final Time TIME = new Time();
		private Thread timerThread;
		private boolean isRunning;
		
		private TimerView(){
			super("Elapsed Time");
			initTimer();	
			setValue(TIME.toString());
		}
		
		private void initTimer(){
			timerThread = new Thread(()->{
				try {
					while(isRunning){
						Thread.sleep(1000);
						TIME.increase();
						setValue(TIME.toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		
		private void startTimer(){
			if(isRunning) return;
			isRunning = true;
			timerThread.setDaemon(true);
			timerThread.start();
		}
		
		private boolean isRunning(){
			return isRunning;
		}
		
		private void stopTimer(){
			try {
				timerThread.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		private void resumeTimer(){
			timerThread.notifyAll();
		}
		
		private void resetTimer(){
			TIME.reset();
		}
		
		private class Time{
			private int hour, min, sec;
			private void reset(){
				hour = 0 ;
				min = 0;
				sec = 0;
			}
			private void increase(){
				sec++;
				
				if (sec == 60) {
					sec = 0;
					min++;
				}
				
				if (min == 60) {
					min = 0;
					hour ++;
				}
			}
			
			@Override
			public String toString(){
				return String.format("%02d", hour) +" : " + String.format("%02d", min)+ " : " + String.format("%02d", sec);
			}
		}
	}
	
	private class MineCountView extends GuideView{
		private int mineCnt;
		
		private MineCountView(int mineCnt){
			super("Left Mines");
			this.mineCnt = mineCnt;
			setValue(mineCnt+"");
		}
		
		private void decreaseMine(){
			mineCnt--;
			setValue(mineCnt+"");
		}
		
		private void increaseMine(){
			mineCnt++;
			setValue(mineCnt+"");
		}
	}
}
