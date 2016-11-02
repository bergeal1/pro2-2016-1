package cz.uhk.fim.pro2.game.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class GameScreen extends Screen {

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

		jButtonBack.setBounds(20, 20, 60, 60);
		jButtonBack.setFont(new Font("Arial", Font.PLAIN, 8));
		jButtonBack.setForeground(Color.RED);
		//jButtonPause.setBounds(20, 20, 60, 60);
		
		add(jButtonBack);
		add(jButtonPause);
	}

}
