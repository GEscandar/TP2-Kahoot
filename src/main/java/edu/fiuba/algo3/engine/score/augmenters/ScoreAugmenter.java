package edu.fiuba.algo3.engine.score.augmenters;

import edu.fiuba.algo3.constants.AugmenterType;
import edu.fiuba.algo3.model.Score;

public abstract class ScoreAugmenter {
	
	public abstract void applyScoreAugmenter(Score questionScore, Score opponentQuestionScore);
	public abstract int getUsesPerPlayer();
	public abstract boolean isForPenalty();

    public abstract AugmenterType getType();

	@Override
	public boolean equals(Object obj) {
		ScoreAugmenter other = (ScoreAugmenter)obj;

		if ( other == null) {
			return false;
		}

		return other.getType().equals(this.getType());
	}
}
