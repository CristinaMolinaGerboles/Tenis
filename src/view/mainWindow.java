package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.Border;

import controller.controller;
import model.Game;
import model.Game.GameObserver;
import model.ball;
import model.bar;

public class mainWindow extends JFrame implements GameObserver{
		
	private Game _game;
	private controller _control;
	private int direction;
	private BallsWorker _ballsworker;
	private int _Score;
	private JLabel ScoreLabel, RecordLabel ;
	private int _record;
	
	public mainWindow(Game game, controller control){
		_control = control;
		_game = game;
		_Score = 0;
		_record = 0;
		initGUI();
		_game.addObserver(this);
	
		
	}
	
	public void initGUI() {
		
		//Crear los paneles
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout( new BoxLayout(centerPanel, BoxLayout.Y_AXIS));		
		mainPanel.add( centerPanel, BorderLayout.CENTER);
		
		JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.X_AXIS));
		centerPanel.add(upperPanel);
		
		Border b = BorderFactory.createLineBorder(Color.BLACK, 2);

		
		GameInterface _b = new GameInterface(_game);
		centerPanel.add(_b);
		
		JLabel Score = new JLabel("Score");
		upperPanel.add(Score);
		
		ScoreLabel = new JLabel();
		ScoreLabel.setText(" " + _Score);
		upperPanel.add(ScoreLabel);
		
	
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) (358);
		int width = (int) (400);
		setPreferredSize(new Dimension(width, height));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		
		_ballsworker = new BallsWorker();
		_ballsworker.execute();
		
		this.addKeyListener(new KeyListener(){
			@Override
		
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			direction = e.getKeyCode();
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			direction = e.getKeyCode();
		}
		});
	}

	@Override
	public void move(bar Bar, ball Ball) {
		// TODO Auto-generated method stub
		_game.setBar(Bar);
		_game.setBall(Ball);
		repaint();
	}

	@Override
	public void reset(bar Bar, ball Ball) {
		// TODO Auto-generated method stub
	
		
	}
	

	private class BallsWorker extends SwingWorker<Void, Void>{
	@Override
	protected  Void doInBackground() throws Exception{
		while(!isCancelled() && !_game.gameOver()) {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub 
					_game.moveBall();
					if(direction == KeyEvent.VK_LEFT)
						_game.moveBar(-1);
					else if(direction == KeyEvent.VK_RIGHT)
						_game.moveBar(+1);
				}
				
				
			});
			try {
				Thread.sleep(3);
				
			}catch(InterruptedException e) {
				
			}
		
		}
		return null;
	}
	}


	@Override
	public void notifyScore() {
		// TODO Auto-generated method stub
		_Score++;
		ScoreLabel.setText(" " + _Score);
		if(_Score == 10) {
			_game.setSpeed(2);
		}
		else if(_Score == 20) {
			_game.setSpeed(3);		
		}
		else if(_Score == 40) {
			_game.setSpeed(4);
			_game.getBar().setBarWidth(50);
		}
	}
	


	
	
}
