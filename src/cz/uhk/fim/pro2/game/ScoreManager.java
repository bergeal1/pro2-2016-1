package cz.uhk.fim.pro2.game;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.SchemaOutputResolver;

public class ScoreManager {

	// DEFINICE TRIDY 	
	private List<Integer> scoreList;
	
	private ScoreManager() {
		this.scoreList = new ArrayList<>();
	}
	
	public void addScore(int score) {
		scoreList.add(score);
	}
	
	public List<Integer> getAll() {
		return scoreList;
	}

		
	
	// SINGLETON
	private static ScoreManager instance;	
	
	public static ScoreManager getInstance() {
		if (instance == null) {
			instance = new ScoreManager();
		}
		return instance;
	}
	
	
	
	// VEREJNE STATICKE METODY
	public static void putScore(int score) {
		getInstance().addScore(score);
	}
	
	public static List<Integer> getList() {
		return getInstance().getAll();
	}

	
	public static int size() {
		return getList().size();
	}

	public static int get(int i) {
		return getList().get(i);
	}
	
	
	
	
}
