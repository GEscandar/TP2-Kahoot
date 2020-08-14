package edu.fiuba.algo3.model;

import java.util.Arrays;
import java.util.List;

import edu.fiuba.algo3.constants.QuestionType;
import edu.fiuba.algo3.engine.score.augmenters.NoMultiplier;
import edu.fiuba.algo3.engine.score.augmenters.ScoreAugmenter;

public abstract class Question {

	protected String text;
	protected QuestionType type;
	protected List<GameOption> options;
	protected List<GameOption> correctOptions;

	public Question(String text, List<GameOption> optionsList) {
		this.text = text;
		this.options = optionsList;
	}

	public List<GameOption> getOptions() {
		return options;
	}

	public List<GameOption> getCorrectOptions() {
		return correctOptions;
	}

	public void setCorrectOptions(List<GameOption> correctOptions) {
		this.correctOptions = correctOptions;
	}	
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public Score calculatePoints(List<GameOption> selectedOptions, ScoreAugmenter augmenter) {
		if(augmenter.isForPenalty() != hasPenalty()) {
			augmenter = new NoMultiplier(); 
		}
		return new Score(calculatePoints(selectedOptions), augmenter);
	}
	
	
	public Score calculatePoints(GameOption selectedOption, ScoreAugmenter augmenter) {
		return calculatePoints(Arrays.asList(selectedOption), augmenter);
	}
	
	public Score calculatePoints(GameOption selectedOption) {
		return calculatePoints(Arrays.asList(selectedOption), new NoMultiplier());
	}
	
	/***
	 * Returns the earned or lost points depending on the answer to the question
	 * @return
	 */
	protected abstract int calculatePoints(List<GameOption> selectedOptions);
	public  abstract QuestionType getType();
	public abstract boolean hasPenalty();
}
