package edu.fiuba.algo3.engine.questions;

import java.util.List;

import edu.fiuba.algo3.constants.QuestionType;
import edu.fiuba.algo3.model.GameOption;
import edu.fiuba.algo3.model.Question;

public class MultipleChoiceWithPenaltyQuestion extends Question {

    public MultipleChoiceWithPenaltyQuestion(String text, List<GameOption> optionsList) { super(text, optionsList); }

    @Override
    protected int calculatePoints(List<GameOption> selectedOptions) {
		int puntaje = 0;
		for(GameOption option : selectedOptions) {
			if(correctOptions.contains(option)) {
				puntaje ++;
			}else {
				puntaje --;
			}			
		}
		return puntaje;
	}

	@Override
	public boolean hasPenalty() {
		return true;
	}
	
	@Override
	public QuestionType getType() {
		return QuestionType.MULTIPLE_CHOICE_WITH_PENALTY;
	}
}
