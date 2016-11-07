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

import cz.uhk.fim.pro2.game.listener.WorldListener;
import cz.uhk.fim.pro2.game.model.Bird;
import cz.uhk.fim.pro2.game.model.Heart;
import cz.uhk.fim.pro2.game.model.Tube;
import cz.uhk.fim.pro2.game.model.World;

public class GameScreen extends Screen implements WorldListener {

	private Bird bird;

	private World world;

	private Timer timer;

	private long lastTimeMillis;

	private JLabel jLabelScore, jLabelLives;

	public GameScreen(MainFrame mainFrame) {
		super(mainFrame);

		JButton jButtonBack = new JButton("BACK");
		JButton jButtonPause = new JButton("PAUSE");
		
		jButtonBack.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setScreen(new HomeScreen(mainFrame));
			}
		});

		jButtonPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (timer.isRunning()) {
					timer.stop();
				} else {
					lastTimeMillis = System.currentTimeMillis();
					timer.start();
				}
			}
		});

		jButtonBack.setBounds(20, 20, 60, 60);
		jButtonPause.setBounds(400, 20, 60, 60);
		
		add(jButtonBack);
		add(jButtonPause);

		jLabelScore = new JLabel("Score: " + Bird.DEFAULT_SCORE);
		jLabelLives = new JLabel("Lives: " + Bird.DEFAULT_LIVES);

		jLabelScore.setBounds(100, 20, 120, 60);
		jLabelLives.setBounds(260, 20, 120, 60);

		add(jLabelScore);
		add(jLabelLives);

		// WORLD 
		bird = new Bird("Ales", 240, 400);
		world = new World(bird, this);
		world.generateRandom();
/*
		world.addTube(new Tube(400, 400, Color.GREEN));
		world.addTube(new Tube(600, 300, Color.GREEN));
		world.addTube(new Tube(800, 500, Color.GREEN));

		world.addHeart(new Heart(500, 450));
		world.addHeart(new Heart(700, 600));
*/

		GameCanvas gameCanvas = new GameCanvas(world);
		gameCanvas.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
		gameCanvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bird.goUp();
			}
		});
		add(gameCanvas);

		// TIMER
		timer = new Timer(15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				long currentTimeMillis = System.currentTimeMillis();

				float delta = (currentTimeMillis - lastTimeMillis) / 1000f;

				world.update(delta);
				gameCanvas.repaint();

				lastTimeMillis = currentTimeMillis;

				jLabelScore.setText("Score: " + bird.getScore());
				jLabelLives.setText("Lives: " + bird.getLives());
			}
		});
		lastTimeMillis = System.currentTimeMillis();
		timer.start();
	}

	@Override
	public void catchHeart(Heart heart) {
		System.out.println("Chytil jsem srdicko");

		heart.setPositionY(-100);

		bird.addLive();
	}

	@Override
	public void crashTube(Tube tube) {
		System.out.println("Narval jsem to do truby");

		bird.setPositionY(tube.getCenterY());
		bird.setSpeed(Bird.JUMP / 2);

		if (!bird.removeLive()) {
			timer.stop();
			mainFrame.setScreen(new FinishScreen(mainFrame, bird));
		}
	}

	@Override
	public void outOf() {
		System.out.println("A ted jsem uplne mimo");

		bird.setPositionY(MainFrame.HEIGHT / 2);
		bird.setSpeed(Bird.JUMP / 2);

		if (!bird.removeLive()) {
			timer.stop();
			mainFrame.setScreen(new FinishScreen(mainFrame, bird));
		}
	}
}
