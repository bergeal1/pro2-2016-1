package cz.uhk.fim.pro2.game.model;

import java.util.ArrayList;
import java.util.List;

import cz.uhk.fim.pro2.game.listener.WorldListener;

public class World {

	public static final int SPEED = 100;

	private Bird bird;	
	private List<Tube> tubes;	
	private List<Heart> hearts;

	private WorldListener worldListener;

	public World(Bird bird, WorldListener worldListener) {
		this.bird = bird;
		this.worldListener = worldListener;
		tubes = new ArrayList<>();
		hearts = new ArrayList<>();
	}

	public void update(float deltaTime) {
		bird.update(deltaTime);

		if (bird.isOutOf()) {
			worldListener.outOf();
		}

		for (Tube tube : tubes) {
			tube.update(deltaTime);

			if (bird.collidesWith(tube)) {
				worldListener.crashTube(tube);
			}
		}

		for (Heart heart : hearts) {
			heart.update(deltaTime);

			if (bird.collidesWith(heart)) {
				worldListener.catchHeart(heart);
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
