package cz.uhk.fim.pro2.game.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import cz.uhk.fim.pro2.game.model.Bird;
import cz.uhk.fim.pro2.game.model.Heart;
import cz.uhk.fim.pro2.game.model.Tube;
import cz.uhk.fim.pro2.game.model.World;

import javax.imageio.ImageIO;

public class GameCanvas extends Canvas {
	
	public static int UP_BOUND = 100;
	public static int DOWN_BOUND = 200;
	
	private World world;

	private BufferedImage imgBird, imgTop, imgHeart, imgBackground, imgTube, imgBottom;
	
	public GameCanvas(World world) {
		this.world = world;

		try {
			imgBird = ImageIO.read(new File("assets/bird.png"));
			imgHeart = ImageIO.read(new File("assets/heart.png"));
			imgTube = ImageIO.read(new File("assets/tube.png"));
			imgBackground = ImageIO.read(new File("assets/background.png"));
			imgTop = ImageIO.read(new File("assets/top.png"));
			imgBottom = ImageIO.read(new File("assets/bottom.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.drawImage(
				imgBackground,
				0,
				UP_BOUND,
				MainFrame.WIDTH,
				MainFrame.HEIGHT - UP_BOUND - DOWN_BOUND,
				null,
				null
		);
		
		Bird bird = world.getBird();
		
		bird.paint(g, imgBird);
		
		List<Heart> hearts = world.getHearts();
		for(Heart heart : hearts) {
			heart.paint(g, imgHeart);
		}
		
		List<Tube> tubes = world.getTubes();
		for(Tube tube : tubes) {
			tube.paint(g, imgTube);
		}

		g.drawImage(
				imgTop,
				0,
				0,
				MainFrame.WIDTH,
				UP_BOUND,
				null,
				null
		);

		g.drawImage(
				imgBottom,
				0,
				MainFrame.HEIGHT - DOWN_BOUND, MainFrame.WIDTH, DOWN_BOUND, null, null
		);
	}

}
