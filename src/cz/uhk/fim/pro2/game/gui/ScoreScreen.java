package cz.uhk.fim.pro2.game.gui;

import javax.swing.JButton;

public class ScoreScreen extends Screen {

	public ScoreScreen(MainFrame mainFrame) {
		super(mainFrame);
		
		JButton jButtonBack = new JButton("BACK");
		
		add(jButtonBack);
	}

}
