package cz.uhk.fim.pro2.game.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import cz.uhk.fim.pro2.game.gui.MainFrame;

public class Tube {

	public static final int SPACE_BETWEEN = 200;

	private float positionX;
	private float height;
	private Color color;
	
	public Tube(float positionX, float height, Color color) {
		super();
		this.positionX = positionX;
		this.height = height;
		this.color = color;
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

	public void paint(Graphics g) {

		Rectangle topRectangle = getTopRectangle();
		Rectangle bottomRectangle = getBottomRectangle();

		g.setColor(Color.green);
		g.fillRect(topRectangle.x, topRectangle.y, topRectangle.width, topRectangle.height);
		g.fillRect(bottomRectangle.x, bottomRectangle.y, bottomRectangle.width, bottomRectangle.height);
	}

	public void update(float deltaTime) {
		positionX -= World.SPEED * deltaTime;
	}

	public Rectangle getTopRectangle() {
		return new Rectangle(
						(int) (getPositionX() - 25),
						(int) (MainFrame.HEIGHT - height),
						50,
						(int) height
		);
	}

	public Rectangle getBottomRectangle() {
		return new Rectangle(
						(int) (getPositionX() - 25),
						0,
						50,
						(int) (MainFrame.HEIGHT - height - SPACE_BETWEEN)
		);
	}
	
}
