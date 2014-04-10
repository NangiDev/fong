package com.me.fong;

import java.util.ArrayList;

public class HighscoreManager {
	private static ArrayList<Score> scoreList = new ArrayList<Score>();
	private Score score;

	public HighscoreManager() {
		score = new Score("Joel", 999999);
		scoreList.add(score);
		score = new Score("Adam", 999999);
		scoreList.add(score);
		score = new Score("Pett", 999999);
		scoreList.add(score);
		score = new Score("Boss", 999999);
		scoreList.add(score);
		score = new Score("Mari", 999999);
		scoreList.add(score);
	}
	
	public String printScore(int index){
		return "" + (index+1) + " " + scoreList.get(index).getName() + " " + scoreList.get(index).getScore();
	}
}
