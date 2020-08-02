package edu.fiuba.algo3.engine.score.augmenters;

import edu.fiuba.algo3.constants.AugmenterType;
import edu.fiuba.algo3.model.Score;

public class ExclusivityMultiplier extends ScoreAugmenter {

	@Override
	public void applyScoreAugmenter(Score questionScore, Score opponentQuestionScore) {		
		if(questionScore.getValue() == opponentQuestionScore.getValue()){
			questionScore.setValue(0);
			opponentQuestionScore.setValue(0);
		}else if(questionScore.getValue() > 0) {
			questionScore.setValue(questionScore.getValue() * 2);			
		}else {
			opponentQuestionScore.setValue(opponentQuestionScore.getValue() * 2);
		}
	}

	@Override
	public AugmenterType getAugmenterType() {
		return AugmenterType.EXCLUSIVITY;
	}

	@Override
	public boolean isNil() {
		return false;
	}

}
