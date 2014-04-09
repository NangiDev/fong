package com.me.fong;

import java.util.ArrayList;

public class HighscoreManager {
	private static ArrayList<Score> scoreList = new ArrayList<Score>();
	private Score score;

	public HighscoreManager() {
		score = new Score("Joe", 999999);
		scoreList.add(score);
		score = new Score("Ada", 999999);
		scoreList.add(score);
		score = new Score("Pet", 999999);
		scoreList.add(score);
		score = new Score("Bos", 999999);
		scoreList.add(score);
		score = new Score("Mar", 999999);
		scoreList.add(score);
	}
	
	public String printScore(int index){
		return "" + (index+1) + " " + scoreList.get(index).getName() + " " + scoreList.get(index).getScore();
	}
}
