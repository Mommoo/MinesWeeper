package com.mommoo.game.screen.view.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.geom.RoundRectangle2D;
import java.util.concurrent.locks.Lock;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mommoo.manager.FontManager;
import com.mommoo.manager.ScreenManager;

class NavigationView extends JPanel{
	private static final ScreenManager SM = ScreenManager.getInstance();
	private final TimerView TIMER_VIEW = new TimerView();
	private final MineCountView MINE_COUNT_VIEW;
	private final int MAX_MINE_CNT;
	
	NavigationView(int mineCnt){
		this.MAX_MINE_CNT = mineCnt;
		MINE_COUNT_VIEW = new MineCountView(mineCnt);
		setLayout(new BorderLayout());
		add(TIMER_VIEW,BorderLayout.WEST);
		add(MINE_COUNT_VIEW,BorderLayout.EAST);
		TIMER_VIEW.startTimer();
		setOpaque(true);
	}

	void decreaseMine(){
		MINE_COUNT_VIEW.setValue((--MINE_COUNT_VIEW.mineCnt)+"");
	}

	void increaseMine(){
		MINE_COUNT_VIEW.setValue((++MINE_COUNT_VIEW.mineCnt)+"");
	}

	int getMineCnt(){
		return MINE_COUNT_VIEW.mineCnt;
	}

	void reset(){
		MINE_COUNT_VIEW.mineCnt = this.MAX_MINE_CNT;
		MINE_COUNT_VIEW.setValue((this.MAX_MINE_CNT)+"");
		TIMER_VIEW.resetTimer();
		TIMER_VIEW.resumeTimer();
	}

	void stopTime(){
		TIMER_VIEW.stopTimer();
	}

	String getTime(){
		return TIMER_VIEW.TIME.toString();
	}

	private class GuideView extends JPanel{
		
		private final JLabel VALUE_LABEL = new JLabel("",JLabel.CENTER);
		
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
		private final Runnable RUNNABLE =()->{
			try {
				while(isRunning){
					Thread.sleep(1000);
					TIME.increase();
					setValue(TIME.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
		
		private TimerView(){
			super("Elapsed Time");
			initTimer();	
			setValue(TIME.toString());
		}
		
		private void initTimer(){
			timerThread = new Thread(RUNNABLE);
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
			timerThread.interrupt();
			isRunning = false;
		}
		
		private void resumeTimer(){
			timerThread = new Thread(RUNNABLE);
			startTimer();
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
	}
}
