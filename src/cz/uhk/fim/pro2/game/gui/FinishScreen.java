package cz.uhk.fim.pro2.game.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import cz.uhk.fim.pro2.game.ScoreManager;
import cz.uhk.fim.pro2.game.model.World;

public class FinishScreen extends Screen {	
	
	public FinishScreen(MainFrame mainFrame, World world) {
		super(mainFrame);
		
		int score = world.getBird().getScore();
		
		ScoreManager.putScore(score);
		
		JLabel scoreLabel = new JLabel(score + "!");
		scoreLabel.setFont(new Font("Arial", Font.BOLD, 40));
		
		JButton jButtonPlay = new JButton("PLAY");
		JButton jButtonScore = new JButton("SCORE");
		
		jButtonPlay.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setScreen(new GameScreen(mainFrame));
			}
		});
		
		jButtonScore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setScreen(new ScoreScreen(mainFrame));
			}
		});

		scoreLabel.setBounds(10, 10, 460, 100);
		jButtonPlay.setBounds(100, 400, 280, 50);
		jButtonScore.setBounds(100, 460, 280, 50);
		
		add(scoreLabel);
		add(jButtonPlay);
		add(jButtonScore);
	}

}
