package cz.uhk.fim.pro2.game.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import cz.uhk.fim.pro2.game.gui.MainFrame;

public class Tube {
	
	private float positionX;
	private float height;
	private Color color;
	
	private static final int GAP = 200;
	private static final int WIDTH = 50;
	
	private boolean counted = false;
	
	public Tube(float positionX, float height, Color color) {
		super();
		this.positionX = positionX;
		this.height = height;
		this.color = color;
	}
	
	public void paint(Graphics g, BufferedImage image){
		g.setColor(Color.GREEN);
		
		Rectangle topRectangle = getTopRectangle();
		Rectangle bottomRectangle = getBottomRectangle();
		
		g.drawImage(
				image,
			(int) topRectangle.getX(),
			(int) topRectangle.getY(), 
			(int) topRectangle.getWidth(),
			(int) topRectangle.getHeight(),
			null
		);
		
		g.drawImage(
				image,
			(int) bottomRectangle.getX(),
			(int) bottomRectangle.getY(), 
			(int) bottomRectangle.getWidth(),
			(int) bottomRectangle.getHeight(),
			null
		);
		
	}
	
	public Rectangle getTopRectangle(){
		return new Rectangle(
				(int) (getPositionX()) - (WIDTH / 2),
				(int) height, 
				WIDTH,
				(int) (MainFrame.HEIGHT - height)
			);
	}
	
	public Rectangle getBottomRectangle(){
		return new Rectangle(
				(int)(getPositionX()) - (WIDTH / 2),
				0, 
				WIDTH,
				(int) (height - GAP)
			);
	}
	
	public static float getRandomHeight(){
		return (new Random().nextFloat() * 350) + 250;
	} 
	
	public int getCenterY(){
		return (int) (height - GAP/2.0);
	}
	
	public int getMinX() {
		return (int) positionX - (WIDTH / 2);
	}
	
	public int getMaxX() {
		return (int) positionX + (WIDTH / 2);
	}

	public float getPositionX() {
		return positionX;
	}

	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}

	public float getHeight() {
		return height;
	}

	public Color getColor() {
		return color;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}

	public void update(float deltaTime){
		positionX -= World.SPEED * deltaTime;
	}

	public boolean isCounted() {
		return counted;
	}
	
	public void setCounted(boolean counted) {
		this.counted = counted;
	}
}
