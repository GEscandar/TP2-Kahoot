package edu.fiuba.algo3.engine.questions;

import java.util.List;

import edu.fiuba.algo3.constants.QuestionType;
import edu.fiuba.algo3.model.GameOption;
import edu.fiuba.algo3.model.Question;

public class MultipleChoiceQuestion extends Question {

    public MultipleChoiceQuestion(String text, List<GameOption> optionsList) { super(text, optionsList); }

    @Override
    protected int calculatePoints(List<GameOption> selectedOptions) {
		if(selectedOptions.size() == correctOptions.size() && selectedOptions.containsAll(correctOptions)) {
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
		return QuestionType.MULTIPLE_CHOICE;
	}
}
