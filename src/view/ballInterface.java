package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.*;

import model.Game;
import model.Game.GameObserver;
import model.ball;
import model.bar;

public class ballInterface extends JComponent implements GameObserver{

	private Game _game;
	private ball _ball;

	
	public ballInterface(Game game) {
		_ball = new ball(_game);
		//_g = new Graphics();
		_game = game;
		/*super.paint(_g);
		initGUI();*/
		_game.addObserver(this);
		
	}
	
	protected void paintComponent(Graphics2D g) {
		g.fillOval(_ball.getX(),_ball.getY(), 30, 30);
		
	}
	@Override
	public void move(bar Bar, ball Ball) {
		// TODO Auto-generated method stub
		_ball = Ball;
		repaint();
		
	}

	@Override
	public void reset(bar Bar, ball Ball) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyScore() {
		// TODO Auto-generated method stub
		
	}

}
