package edu.fiuba.algo3.engine.score.augmenters;

import edu.fiuba.algo3.constants.AugmenterType;
import edu.fiuba.algo3.model.Score;

public class NoMultiplier extends ScoreAugmenter {

	@Override
	public void applyScoreAugmenter(Score questionScore, Score opponentQuestionScore) {}

	@Override
	public int getUsesPerPlayer() {
		return 0;
	}

	@Override
	public boolean isForPenalty() {

		return false;
	}

	@Override
	public AugmenterType getType() {
		return AugmenterType.NO_MULTIPLIER;
	}

}
