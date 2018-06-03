package model;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

public class ball {
	
	//Coordenas
	private int _y = 0;
	private int _x = 180;
	//velocidad de la bola
	private int _ax;
	private int _ay;
	//Si la bola est� parada o no
	boolean paused;
	private Color _color;
	private Game _game;
	
	private int ballHeigth = 30;

	public ball(Game game){
		_game = game; 
		_ax = 1;
		_ay = 1;
		

	}
	
	public int getY() {
		return _y;
	}
	public int getX() {
		return _x;
	}

	public Color getColor() {
		return _color;
	}
	public boolean getpaused() {
		return paused;
	}
	public void setpuased(boolean _paused) {
		paused = _paused;
	}
	
	public int getballHeigth() {
		return ballHeigth;
	}
	
	public void move() {
		if(!paused) {
			
			if(_x + _ax < 0) {			
				_ax = +_game.getSpeed();
			}
			//Miramo que no sea mayor que el tablero
			if( _x + _ax >= _game.getWidth()) {
				//_x = _ax - _game.getWidth();
				_ax = -_game.getSpeed();
			}
			if( _y +_ay < 0) {			
				_ay = +_game.getSpeed();
			}
			//Miramos que no sea mayor que e ltablero
			if(_y + _ay>= _game.getHeight()) {
			//	_y = _ay - _game.getHeight(); 
				_ay = -_game.getSpeed();
			}
			if(_y +_ay >= _game.getHeight() - getballHeigth()) {
				_game.setgameOver(true);
			}
			if(_game.collision()) {				
				_game.setCollision(true);
				_ay = -_game.getSpeed();
				_y = _game.getBall().getY() - getballHeigth();
			}
			_x += _ax;
			_y += _ay;
			
		}
	}
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(),  getballHeigth(),  getballHeigth());
	}
}
