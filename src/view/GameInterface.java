package view;

import java.awt.Graphics;

import java.awt.Graphics2D;
import view.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Game;
import model.Game.GameObserver;
import model.ball;
import model.bar;

public class GameInterface extends JPanel implements GameObserver{

	private Game _game;
	private ballInterface _ball;
	private ball _Ball;
	private bar _Bar;
	private barInterface _bar;
	private mainWindow main;
	

	
	public GameInterface(Game game) {
		_game = game;
		_ball = new ballInterface(_game);
		_bar = new barInterface(_game);
		
		//_g =_; 
		//paint(_g);
		//main = mainWindow(_game, null);
			
		
		
		_game.addObserver(this);
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		//g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		_ball.paintComponent(g2d);
		_bar.paint(g2d);
		if(_game.gameOver()) {
			gameOver();
		}
	}
	private void gameOver() {
		 JOptionPane.showMessageDialog(this, "GAME OVER"
				,"GAME OVER", JOptionPane.YES_NO_OPTION);
		
			System.exit(ABORT);
	
	}
	
	@Override
	public void move(bar Bar, ball Ball) {
		// TODO Auto-generated method stub
		_Ball = Ball;
		_Bar = Bar;
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
