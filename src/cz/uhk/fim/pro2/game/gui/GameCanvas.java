package cz.uhk.fim.pro2.game.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import cz.uhk.fim.pro2.game.model.Bird;
import cz.uhk.fim.pro2.game.model.Heart;
import cz.uhk.fim.pro2.game.model.Tube;
import cz.uhk.fim.pro2.game.model.World;

public class GameCanvas extends Canvas {
	
	private World world;
	
	public GameCanvas(World world) {
		this.world = world;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.setColor(Color.cyan);
		g.fillRect(0, 0, 480, 800);

		Bird bird = world.getBird();
		bird.paint(g);

		for (Tube tube : world.getTubes()) {
			tube.paint(g);
		}

		for (Heart heart : world.getHearts()) {
			heart.paint(g);
		}

		g.setColor(Color.orange);
		g.fillRect(0, 0, 480, 50);

		g.setColor(new Color(150, 100, 20));
		g.fillRect(0, 650, 480, 150);
	}

}
