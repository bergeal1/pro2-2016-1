package cz.uhk.fim.pro2.game.model;

import java.awt.Color;
import java.awt.Graphics;

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

		g.setColor(Color.green);
		g.fillRect(
						(int) (getPositionX() - 25),
						(int) (MainFrame.HEIGHT - height),
						50,
						(int) height);

		g.fillRect(
						(int) (getPositionX() - 25),
						0,
						50,
						(int) (MainFrame.HEIGHT - height - SPACE_BETWEEN));
	}

	public void update(float deltaTime) {
	}


	// TODO konstruktor, gettery, settery, toString()
	
	
	
}
