package cz.uhk.fim.pro2.game.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import cz.uhk.fim.pro2.game.gui.MainFrame;

public class Bird {

	public static final int JUMP = 1000;
	public static final int GRAVITY = 500;

	public static final int DEFAULT_LIVES = 3;
	public static final int DEFAULT_SCORE = 0;

	private String name;
	private float positionX, positionY;
	private float speed;
	private int lives;
	private int score;
	
	public Bird(String name, float positionX, float positionY) {
		super();
		this.name = name;
		this.positionX = positionX;
		this.positionY = positionY;
		speed = JUMP / 2;
		score = DEFAULT_SCORE;
		lives = DEFAULT_LIVES;
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

		if (rectangle.getMinX() < 0 || rectangle.getMinY() < 50) {
			return true;
		}

		if (rectangle.getMaxX() > MainFrame.WIDTH || rectangle.getMaxY() > 650) {
			return true;
		}

		return false;
	}

	public void update(float deltaTime) {
		positionY += speed * deltaTime;
		positionY -= GRAVITY * deltaTime;

		speed -= speed * deltaTime;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addScore() {
		score++;
	}

	public void addLive() {
		lives++;
	}

	public boolean removeLive() {
		lives--;
		return lives > 0;
	}
}