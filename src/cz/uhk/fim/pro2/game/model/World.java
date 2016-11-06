package cz.uhk.fim.pro2.game.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cz.uhk.fim.pro2.game.listener.WorldListener;

public class World {

	public static final int SPACE_BETWEEN_TUBES = 400;
	public static final int SPACE_BETWEEN_HEARTS = 800;

	public static final int SPEED = 150;

	private Bird bird;	
	private List<Tube> tubes;	
	private List<Heart> hearts;

	private WorldListener worldListener;

	private boolean randomWorld = false;

	public World(Bird bird, WorldListener worldListener) {
		this.bird = bird;
		this.worldListener = worldListener;
		tubes = new ArrayList<>();
		hearts = new ArrayList<>();
	}

	public void generateRandom() {

		randomWorld = true;

		for (int i = 1; i <= 3; i++) {
			tubes.add(new Tube(SPACE_BETWEEN_TUBES * i + SPACE_BETWEEN_TUBES / 2, Tube.getRandomHeight(), Color.green));
		}

		hearts.add(new Heart(SPACE_BETWEEN_HEARTS, Heart.getRandomY()));
	}

	public void update(float deltaTime) {

		if (randomWorld) {
			regenerate();
		}

		bird.update(deltaTime);

		if (bird.isOutOf()) {
			worldListener.outOf();
		}

		for (Tube tube : tubes) {
			tube.update(deltaTime);

			if (bird.collidesWith(tube)) {
				tube.setCounted(true);
				worldListener.crashTube(tube);
			}

			if (!tube.wasCounted()) {
				if (bird.getRectangle().getMinX() > tube.getTopRectangle().getMaxX()) {
					tube.setCounted(true);
					bird.addScore();
				}
			}
		}

		for (Heart heart : hearts) {
			heart.update(deltaTime);

			if (bird.collidesWith(heart)) {
				worldListener.catchHeart(heart);
			}
		}
	}

	private void regenerate() {

		for (Tube tube : tubes) {
			if (tube.getPositionX() < -100) {
				tube.setCounted(false);
				tube.setPositionX(tube.getPositionX() + 3 * SPACE_BETWEEN_TUBES);
				tube.setHeight(Tube.getRandomHeight());
			}
		}

		for (Heart heart : hearts) {
			if (heart.getPositionX() < -100) {
				heart.setPositionX(heart.getPositionX() + SPACE_BETWEEN_HEARTS);
				heart.setPositionY(Heart.getRandomY());
			}
		}

	}

	public void addTube(Tube tube) {
		tubes.add(tube);
	}
	
	public void addHeart(Heart heart) {
		hearts.add(heart);
	}
	
	public Bird getBird() {
		return bird;
	}

	public List<Tube> getTubes() {
		return tubes;
	}

	public List<Heart> getHearts() {
		return hearts;
	}

	@Override
	public String toString() {
		return "Bird: " + bird.getName() 
		+ " [" + bird.getPositionX() 
		+ ";" + bird.getPositionY() + "]\n"
				
		+ "Tubes: " + tubes.size() + "\n"				
		+ "Hearts: " + hearts.size();
	}
	
}
