package com.me.fong;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Comparator;

public class HighscoreManager {
	private static ArrayList<Score> scoreList = new ArrayList<Score>();
	private Score score;
	private FileWriter fileWriter;
	private BufferedReader bufferedReader;
	private String filename = "scoreBoard.txt";
	private String input;

	public HighscoreManager() {
	}

	public String printScore(int index) {
		String name = scoreList.get(index).getName();
		int score = scoreList.get(index).getScore();
		String scoreStr = (("000000" + score).substring(("" + score).length()));

		return "" + (index + 1) + " " + name + " " + scoreStr;
	}

	public void addScore(Score score) {
		this.scoreList.add(score);
		sortList();
		removeLastItem();
	}

	private void removeLastItem() {
		this.scoreList.remove(scoreList.size() - 1);
	}

	private void sortList() {
		scoreList.sort(new Comparator<Score>() {
			@Override
			public int compare(Score fruite1, Score fruite2) {
				return fruite1.compareTo(fruite2);
			}
		});
	}

	/*private void saveScoreToFile() {
		try {
			fileWriter = new FileWriter(new File(filename));
			for (int j = 0; j < scoreList.size(); j++) {
				fileWriter.write(printScore(j) + "\n");
			}
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Couldn't write to scoreboard file");
			e.printStackTrace();
		}
	}
	
	private void readScoreFromFile(){
		try {
			bufferedReader = new BufferedReader(new FileReader(filename));
			while ((input = bufferedReader.readLine()) != null
					&& input.length() != 0) {
				String[] parts = input.split(" ");
				score = new Score(parts[1], Integer.parseInt(parts[2]));
				scoreList.add(score);
			}

		} catch (FileNotFoundException e) {
			System.out.println("Couldn't found file.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Couldn't read file.");
			e.printStackTrace();
		}
	}*/
}
