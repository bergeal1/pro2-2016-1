package cz.uhk.fim.pro2.game.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import cz.uhk.fim.pro2.game.gui.MainFrame;

public class Tube {

	public static final int SPACE_BETWEEN = 250;

	private float positionX;
	private float height;
	private Color color;

	private boolean counted;

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

	public void setHeight(float height) {
		this.height = height;
	}

	public Color getColor() {
		return color;
	}

	public void paint(Graphics g) {

		Rectangle topRectangle = getTopRectangle();
		Rectangle bottomRectangle = getBottomRectangle();

		g.setColor(color);
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

	public int getCenterY() {
		return (int) (height + SPACE_BETWEEN / 2);
	}

	public static final int getRandomHeight() {
		return new Random().nextInt(300) + 200;
	}

	public boolean wasCounted() {
		return counted;
	}

	public void setCounted(boolean counted) {
		this.counted = counted;
	}
}
