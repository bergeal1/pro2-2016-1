package cz.uhk.fim.pro2.game;

import cz.uhk.fim.pro2.game.gui.GameScreen;
import cz.uhk.fim.pro2.game.gui.HomeScreen;
import cz.uhk.fim.pro2.game.gui.MainFrame;
import cz.uhk.fim.pro2.game.gui.ScoreScreen;

import java.io.File;
import java.io.IOException;

public class Game {

	public static final String SCORE_FILE = "score.csv";

	public static void main(String[] args) {

		MainFrame mainFrame = new MainFrame();

		File file = new File(SCORE_FILE);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		mainFrame.setScreen(new HomeScreen(mainFrame));
		
	}
}
