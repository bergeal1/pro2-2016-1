package cz.uhk.fim.pro2.game.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import cz.uhk.fim.pro2.game.gui.MainFrame;

public class Bird {

	public static final int JUMP = 1000;
	public static final int GRAVITY = 500;

	private String name;
	private float positionX, positionY;
	private float speed;
	private int lives;
	
	public Bird(String name, float positionX, float positionY) {
		super();
		this.name = name;
		this.positionX = positionX;
		this.positionY = positionY;
		speed = 0;
		lives = 3;
	}
	
	public String getName(){
		return name;
	}
	
	public float getPositionX() {
		return positionX;
	}
	
	public void setPositionX(float x) {
		this.positionX = x;
	}
	
	public float getPositionY() {
		return positionY;
	}

	public void setPositionY(float positionY) {
		this.positionY = positionY;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public void paint(Graphics g) {
		Rectangle rectangle = getRectangle();

		g.setColor(Color.blue);
		g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}

	public void goUp() {
		speed = JUMP;
	}

	public Rectangle getRectangle() {
		return new Rectangle(
						(int) (getPositionX() - 25),
						(int) (MainFrame.HEIGHT - getPositionY() - 25),
						50,
						50
		);
	}

	public boolean collidesWith(Tube tube) {
		Rectangle rectangle = getRectangle();

		return rectangle.intersects(tube.getTopRectangle()) || rectangle.intersects(tube.getBottomRectangle());
	}

	public boolean collidesWith(Heart heart) {
		Rectangle rectangle = getRectangle();

		return rectangle.intersects(heart.getRectangle());
	}

	public boolean isOutOf() {
		Rectangle rectangle = getRectangle();

		if (rectangle.getMinX() < 0 || rectangle.getMinY() < 0) {
			return true;
		}

		if (rectangle.getMaxX() > MainFrame.WIDTH || rectangle.getMaxY() > MainFrame.HEIGHT) {
			return true;
		}

		return false;
	}

	public void update(float deltaTime) {
		positionY += speed * deltaTime;
		positionY -= GRAVITY * deltaTime;

		speed -= speed * deltaTime;
	}
}