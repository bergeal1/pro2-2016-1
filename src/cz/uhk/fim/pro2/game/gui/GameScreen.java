package cz.uhk.fim.pro2.game.gui;

import javax.swing.JButton;

public class GameScreen extends Screen {

	public GameScreen(MainFrame mainFrame) {
		super(mainFrame);

		JButton jButtonBack = new JButton("BACK");
		JButton jButtonPause = new JButton("PAUSE");
		
		
		
		add(jButtonBack);
		add(jButtonPause);
	}

}
