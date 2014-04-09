package com.me.fong;

public class Score {
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
}
