package cz.uhk.fim.pro2.game.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class HomeScreen extends Screen {

	public HomeScreen(MainFrame mainFrame) {
		super(mainFrame);

		JButton jButtonPlay = new JButton("PLAY");
		JButton jButtonScore = new JButton("SCORE");
		JButton jButtonSound = new JButton("SOUND");
		
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
		
		add(jButtonPlay);
		add(jButtonScore);
		add(jButtonSound);
	}	
}
