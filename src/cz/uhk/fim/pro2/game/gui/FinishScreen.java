package cz.uhk.fim.pro2.game.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import cz.uhk.fim.pro2.game.manager.ScoreManager;
import cz.uhk.fim.pro2.game.model.Bird;


public class FinishScreen extends Screen {

	public FinishScreen(MainFrame mainFrame, Bird bird) {
		super(mainFrame);

		ScoreManager.saveScore(System.currentTimeMillis(), bird.getScore());

		JLabel jLabelTitle = new JLabel("FIM BIRD");
		JLabel jLabelScore = new JLabel("SCORE: " + bird.getScore());

		JButton jButtonPlay = new JButton("PLAY AGAIN");
		JButton jButtonHome = new JButton("HOME");

		jButtonPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setScreen(new GameScreen(mainFrame));
			}
		});

		jButtonHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setScreen(new HomeScreen(mainFrame));
			}
		});

		jLabelTitle.setFont(new Font("Arial", Font.BOLD, 40));
		jLabelScore.setFont(new Font("Arial", Font.BOLD, 40));
		
		// nastavim velikost a pozici
		jLabelTitle.setBounds(10, 10, 460, 100);
		jLabelScore.setBounds(10, 250, 460, 100);
		jButtonPlay.setBounds(100, 400, 280, 50);
		jButtonHome.setBounds(100, 520, 280, 50);

		add(jLabelTitle);
		add(jLabelScore);
		add(jButtonPlay);
		add(jButtonHome);

	}	
}
