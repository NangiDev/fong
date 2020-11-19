package se.nangidev.fong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class HighscoreManager {
	private ArrayList<Score> scoreList = new ArrayList<Score>();
	private Preferences prefs;

	public HighscoreManager() {
	}

	public void addScore(Score score) {
		this.scoreList.add(score);
		sortList();
		removeLastItem();
		writeHighscoreToSavefile();
	}
	
	public String printScore(int index) {
		String name = scoreList.get(index).getName();
		name = name + (("     ").substring(name.length()));
		int score = scoreList.get(index).getScore();
		String scoreStr = (("000000" + score).substring(("" + score).length()));
		return "" + (index + 1) + " " + name + " " + scoreStr;
	}


	private void removeLastItem() {
		if(scoreList.size()>5)
			this.scoreList.remove(scoreList.size() - 1);
	}

	private void sortList() {
		Collections.sort(scoreList, new Comparator<Score>() {
			@Override
			public int compare(Score score1, Score score2) {
				return score1.compareTo(score2);
			}
		});
	}
	
	private void writeHighscoreToSavefile(){
		prefs = Gdx.app.getPreferences("FongSaveFile");
		for (Score score : scoreList) {
			int index = scoreList.indexOf(score);
			prefs.putString("" + (index + 1), score.getName()+ " " + score.getScore());
		}
		prefs.flush();
	}
}
