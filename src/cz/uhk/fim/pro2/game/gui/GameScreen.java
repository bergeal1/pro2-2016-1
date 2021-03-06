package cz.uhk.fim.pro2.game.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;

import cz.uhk.fim.pro2.game.interfaces.WorldListener;
import cz.uhk.fim.pro2.game.model.Bird;
import cz.uhk.fim.pro2.game.model.Heart;
import cz.uhk.fim.pro2.game.model.Tube;
import cz.uhk.fim.pro2.game.model.World;

public class GameScreen extends Screen implements WorldListener {
	
	private long lastTimeMillis;
	
	private Timer timer;
	
	private Bird bird;
	
	private JLabel jLabelScore, jLabelLives;

	public GameScreen(MainFrame mainFrame) {
		super(mainFrame);

		JButton jButtonBack = new JButton("BACK");
		JButton jButtonPause = new JButton("PAUSE");
		
		jButtonBack.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				mainFrame.setScreen(new HomeScreen(mainFrame));
			}
		});
		
		jButtonPause.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(timer.isRunning()){
					timer.stop();
				}else{
					lastTimeMillis = System.currentTimeMillis();
					timer.start();
				}
			}
		});

		jButtonBack.setBounds(20, 20, 60, 60);
		jButtonPause.setBounds(400, 20, 60, 60);
		
		add(jButtonBack);
		add(jButtonPause);
		
		jLabelLives = new JLabel("Lives: " + Bird.DEFAULT_LIVES);
		jLabelScore = new JLabel("Score: " + Bird.DEFAULT_SCORE);

		jLabelLives.setFont(new Font("Arial", Font.BOLD, 30));
		jLabelLives.setForeground(Color.WHITE);
		
		jLabelScore.setFont(new Font("Arial", Font.BOLD, 30));
		jLabelScore.setForeground(Color.WHITE);
		
		jLabelLives.setBounds(260, 20, 120, 60);
		jLabelScore.setBounds(100, 20, 120, 60);

		add(jLabelLives);
		add(jLabelScore);	
		
		// WORLD 
		bird = new Bird("Ales", 240, 400);
		World world = new World(bird, this);
		
		world.generateRandom();
		
//		world.addTube(new Tube(400, 400, Color.GREEN));
//		world.addTube(new Tube(700, 300, Color.GREEN));
//		world.addTube(new Tube(1000, 500, Color.GREEN));
//
//		world.addHeart(new Heart(600, 250));
//		world.addHeart(new Heart(850, 300));
		
		GameCanvas gameCanvas = new GameCanvas(world);
		gameCanvas.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
		
		gameCanvas.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				bird.goUp();
			}
		});
		
		add(gameCanvas);
		
		timer = new Timer(15, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				long currentTimeMillis = System.currentTimeMillis();
				
				float delta = (currentTimeMillis - lastTimeMillis) / 1000f;
				world.update(delta);
				
				jLabelLives.setText("Lives: " + bird.getLives());
				jLabelScore.setText("Score: " + bird.getScore());
				
				if(!bird.isAlive()){
					timer.stop();
					FinishScreen finisScreen = new FinishScreen(mainFrame,world);
					mainFrame.setScreen(finisScreen);
				}

				repaint();
				
				lastTimeMillis = currentTimeMillis;
			}
		});
		
		lastTimeMillis = System.currentTimeMillis();
		timer.start();
	}

	@Override
	public void crashTube(Tube tube) {
		bird.removeLive();
		bird.setPositionY(tube.getCenterY());
	}

	@Override
	public void catchHeart(Heart heart) {
		heart.setPositionY(-100);
		bird.catchHeart();
	}

	@Override
	public void outOf() {
		bird.setPositionY(MainFrame.HEIGHT / 2);
		bird.setSpeed(Bird.JUMP / 2);
		
		bird.removeLive();
	}

}
