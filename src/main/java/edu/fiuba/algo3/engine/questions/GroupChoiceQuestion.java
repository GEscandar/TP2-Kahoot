package edu.fiuba.algo3.engine.questions;

import java.util.List;

import edu.fiuba.algo3.constants.QuestionType;
import edu.fiuba.algo3.model.GameOption;
import edu.fiuba.algo3.model.OptionGroup;
import edu.fiuba.algo3.model.Question;

public class GroupChoiceQuestion extends Question {	

	private List<OptionGroup> groupList;
	
	public GroupChoiceQuestion(String text, List<GameOption> optionList, List<OptionGroup> groupList) { 
		super(text, optionList);
		this.groupList = groupList;
	}

	@Override
	protected int calculatePoints(List<GameOption> selectedOptions) {
		if(selectedOptions.equals(correctOptions)) {
			return 1;
		}
		return 0;
	}
	
	public List<OptionGroup> getOptionGroupList(){
		return groupList;
	}

	@Override
	public boolean hasPenalty() {
		return false;
	}

	@Override
	public QuestionType getType() {
		return QuestionType.GROUP_CHOICE;
	}
}
