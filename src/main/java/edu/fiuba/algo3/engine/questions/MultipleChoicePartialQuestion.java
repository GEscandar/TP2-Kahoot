package edu.fiuba.algo3.engine.questions;

import java.util.List;

import edu.fiuba.algo3.constants.QuestionType;
import edu.fiuba.algo3.model.GameOption;
import edu.fiuba.algo3.model.Question;


public class MultipleChoicePartialQuestion extends Question {

    public MultipleChoicePartialQuestion(String text, List<GameOption> optionList) { super(text, optionList); }

    @Override
    protected int calculatePoints(List<GameOption> selectedOptions) {
		int puntaje = 0;
		for(GameOption option : selectedOptions) {
			if(correctOptions.contains(option)) {
				puntaje ++;
			}else {
				return 0;
			}			
		}
		return puntaje;
	}

	@Override
	public boolean hasPenalty() {
		return false;
	}
	
	@Override
	public QuestionType getType() {
		return QuestionType.MULTIPLE_CHOICE_PARTIAL;
	}
}
