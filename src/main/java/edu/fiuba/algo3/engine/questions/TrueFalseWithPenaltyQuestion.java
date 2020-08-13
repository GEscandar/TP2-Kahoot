package edu.fiuba.algo3.engine.questions;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.constants.QuestionType;
import edu.fiuba.algo3.model.GameOption;
import edu.fiuba.algo3.model.Question;

public class TrueFalseWithPenaltyQuestion extends Question {

    public TrueFalseWithPenaltyQuestion(String text, List<GameOption> optionsList) { super(text, optionsList); }

    @Override
    protected int calculatePoints(List<GameOption> selectedOptions) {
		if(selectedOptions.equals(correctOptions)) {
			return 1;
		}
		return -1;
	}
	
	public void setCorrectOption(GameOption option) {
		this.correctOptions = new ArrayList<>();
		this.correctOptions.add(option);
	}
	
	@Override
	public boolean hasPenalty() {
		return true;
	}
	
	@Override
	public QuestionType getType() {
		return QuestionType.TRUE_FALSE_WITH_PENALTY;
	}
}
