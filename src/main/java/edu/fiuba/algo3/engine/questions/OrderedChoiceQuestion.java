package edu.fiuba.algo3.engine.questions;

import java.util.List;

import edu.fiuba.algo3.constants.QuestionType;
import edu.fiuba.algo3.model.GameOption;
import edu.fiuba.algo3.model.Question;

public class OrderedChoiceQuestion extends Question {

    public OrderedChoiceQuestion(String text, List<GameOption> optionList) { super(text, optionList); }

    @Override
    protected int calculatePoints(List<GameOption> selectedOptions) {
		if(selectedOptions.equals(correctOptions)) {
			return 1;
		}
		return 0;
	}

	@Override
	public boolean hasPenalty() {
		return false;
	}
	
	@Override
	public QuestionType getType() {
		return QuestionType.ORDERED_QUESTION;
	}
}
