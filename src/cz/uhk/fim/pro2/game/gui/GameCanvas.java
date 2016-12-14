package cz.uhk.fim.pro2.game.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import cz.uhk.fim.pro2.game.model.Bird;
import cz.uhk.fim.pro2.game.model.Heart;
import cz.uhk.fim.pro2.game.model.Tube;
import cz.uhk.fim.pro2.game.model.World;

public class GameCanvas extends Canvas {
	
	public static int UP_BOUND = 100;
	public static int DOWN_BOUND = 200;
	
	private World world;
	
	private BufferedImage imageBird, imageBackground, imageBottom, imageHeart, imageTop, imageTube;
	
	public GameCanvas(World world) {
		this.world = world;
		
		try {
			imageBird = ImageIO.read(new File("assets/bird.png"));
			imageBackground = ImageIO.read(new File("assets/background.png"));
			imageBottom = ImageIO.read(new File("assets/bottom.png"));
			imageHeart = ImageIO.read(new File("assets/heart.png"));
			imageTop = ImageIO.read(new File("assets/top.png"));
			imageTube= ImageIO.read(new File("assets/tube.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.setColor(Color.cyan);
		g.fillRect(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
		
		Bird bird = world.getBird();
		
		bird.paint(g, imageBird);
		
		List<Heart> hearts = world.getHearts();
		for(Heart heart : hearts) {
			heart.paint(g, imageHeart);
		}
		
		List<Tube> tubes = world.getTubes();
		for(Tube tube : tubes) {
			tube.paint(g, imageTube);
		}
		
		g.drawImage(imageTop, 0, 0, MainFrame.WIDTH, UP_BOUND, null);
		g.drawImage(imageBottom, 0, MainFrame.HEIGHT - DOWN_BOUND, MainFrame.WIDTH, DOWN_BOUND, null);
	}

}
