package edu.fiuba.algo3.model;

import edu.fiuba.algo3.engine.score.augmenters.ScoreAugmenter;

public class Score {

	private int value;
	private ScoreAugmenter augmenter;

	public Score(int value){
		this.value = value;
	}
	
	public Score(int value, ScoreAugmenter augmenter){
		this.value = value;
		this.augmenter = augmenter;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public void sum(Score score) {
		value = score.getValue() + value;
	}
	
	public boolean biggerThan(Score score) {
		return value > score.getValue();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Score other = (Score) obj;
		if (this.value != other.getValue())
			return false;
		return true;
	}

	public ScoreAugmenter getAugmenter() {
		return augmenter;
	}

	
	
	
}
