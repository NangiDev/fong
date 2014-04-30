package com.me.fong;

public class Score implements Comparable<Score>{
	private int score;
	private String name;
		
	public Score(String name, int score){
		this.name = name;
		this.score = score;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getScore(){
		return this.score;
	}

	@Override
	public int compareTo(Score s) {
		if(this.score > s.score)
			return -1;
		if(this.score < s.score)
			return 1;
		return 0;
	}
}
