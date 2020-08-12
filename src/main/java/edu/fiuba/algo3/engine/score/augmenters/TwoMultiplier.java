package edu.fiuba.algo3.engine.score.augmenters;

import edu.fiuba.algo3.constants.AugmenterType;
import edu.fiuba.algo3.constants.AugmenterUses;
import edu.fiuba.algo3.model.Score;

public class TwoMultiplier extends ScoreAugmenter {

	@Override
	public void applyScoreAugmenter(Score questionScore, Score opponentQuestionScore) {			
		questionScore.setValue(questionScore.getValue() * 2);
	}

	@Override
	public int getUsesPerPlayer() {
		return AugmenterUses.MULTIPLY_PER_TWO;
	}


	@Override
	public boolean isForPenalty() {
		return true;
	}

	@Override
	public AugmenterType getType() {
		return AugmenterType.MULTIPLY_PER_TWO;
	}

}
