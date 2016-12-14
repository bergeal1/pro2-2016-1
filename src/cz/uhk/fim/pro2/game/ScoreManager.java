package cz.uhk.fim.pro2.game;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.SchemaOutputResolver;

public class ScoreManager {

	// DEFINICE TRIDY
	
	private ScoreManager() {
	}
	
	public void addScore(int score) {

		List<Integer> scoreList = getAll();
		scoreList.add(score);

		try {
			FileWriter fileWriter = new FileWriter(Game.SCORE_FILE);

			for (int scoreItem : scoreList) {
				fileWriter.append(String.valueOf(scoreItem));
				fileWriter.append(";");
				fileWriter.append("Hey");
				fileWriter.append("\n");
			}

			fileWriter.flush();
			fileWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Integer> getAll() {

		List<Integer> scoreList = new ArrayList<>();

		try {

			FileReader fileReader = new FileReader(Game.SCORE_FILE);

			BufferedReader reader = new BufferedReader(fileReader);

			String line;
			while ((line = reader.readLine()) != null) {
                String[] score = line.split(";");
                scoreList.add(Integer.valueOf(score[0]));
            }

		} catch (Exception e) {

		}

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