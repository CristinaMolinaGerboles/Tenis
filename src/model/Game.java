package model;


import java.util.ArrayList;
import java.util.List;

import model.Game.GameObserver;

@SuppressWarnings("serial")
public class Game {

	private static final int MINWIDTH = 380;
	private static final int MINHEIGHT =  310;
	
	private int _width;
	private int _height;
	
	private ball _ball;
	private bar _bar;
	
	private boolean gameOver;
	private boolean Collision;
	private int _gameSpeed;
	
	private List<GameObserver> _obs;
	
	public Game() {
		_ball = new ball(this);
		_bar = new bar(this);	
		_obs = new ArrayList<>();
		_width = MINWIDTH;
		_height = MINHEIGHT ;
		_gameSpeed = 1;
		gameOver = false;
		Collision = false;
		}
	
	 public interface GameObserver {
		void move(bar Bar, ball Ball);
		void reset(bar Bar, ball Ball);
		void notifyScore();
	
	}
	 
	 public void moveBar(int xa) {		 
		 _bar.move(xa);
		 for(GameObserver o: _obs) {
			 o.move(_bar, _ball);
		 }
		 
	 }
	public void moveBall() {	
		_ball.move();
		 for(GameObserver o: _obs) {
			 o.move(_bar, _ball);
		 }
		if(getCollision()){
				 for(GameObserver o: _obs)
				 o.notifyScore();
			 }
		setCollision(false);
			 
	} 
	
	public boolean getCollision() {
		return Collision;
	}
	public void  setCollision(boolean _collision) {
		Collision = _collision;
	}
	public bar getBar() {
		return _bar;
	}
	public ball getBall() {
		return _ball;
	}
	public void setBar(bar Bar) {
		_bar = Bar;
	}
	public void setBall(ball Ball) {
		_ball = Ball;
	}
	public int getWidth() {
		return _width;
	}
	public boolean gameOver() {
		return gameOver;
	}
	public void setgameOver(boolean _gameOver) {
		gameOver = _gameOver;
		//_gameOver = true;
	}
	
	public int getHeight() {
		return _height;
	}
	public boolean collision() {
		return getBar().getBounds().intersects(getBall().getBounds());
		
	}
	public int getSpeed() {
		return _gameSpeed;
	}
	public void setSpeed(int speed) {
		_gameSpeed = speed;
	}
	/**
	 * Add an observer
	 * @param o
	 */
	public void addObserver(GameObserver o) {
		_obs.add(o);
	}
	/**
	 * Remove an observer
	 * @param o
	 */
	public void RemoveObserver(GameObserver o) {
		_obs.remove(o);
	}

}
