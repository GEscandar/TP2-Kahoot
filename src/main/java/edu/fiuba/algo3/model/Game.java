package edu.fiuba.algo3.model;

import edu.fiuba.algo3.constants.AugmenterType;
import edu.fiuba.algo3.engine.score.ScoreCalculator;
import edu.fiuba.algo3.engine.score.augmenters.NoMultiplier;
import edu.fiuba.algo3.engine.score.augmenters.ScoreAugmenter;
import javafx.animation.PauseTransition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game {
	
	private List<Player> players;
	private List<Question> questions;
	private Player currentPlayer;
	private Question currentQuestion;
	private Iterator<Player> playersIterator;
	private Iterator<Question> questionIterator;
	private List<MatchResult> matchResults;
	private boolean isOver;

	public Game(List<Player> players, List<Question> questions) {
		this.players = players;
		this.questions = questions;
	}
	
	public List<Player> getPlayers() {
		return players;
	}

	public List<Question> getQuestions() {
		return questions;
	}
	
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public boolean isOver(){
		return isOver;
	}

	public Question getCurrentQuestion(){
		return currentQuestion;
	}

	public void newRound(){
		playersIterator = players.iterator();
		currentPlayer = playersIterator.next();
		matchResults = new ArrayList<>();
	}

	public void start(){
		newRound();
		questionIterator = questions.iterator();
		currentQuestion = questionIterator.next();
		isOver = false;
	}
	
	public void nextTurn(GameOption selectedOption){
		nextTurn(selectedOption, new NoMultiplier());
	}

	public void nextTurn(GameOption selectedOption, ScoreAugmenter augmenter){
		List<GameOption> selectedOptions = new ArrayList<>();
		selectedOptions.add(selectedOption);
		nextTurn(selectedOptions, augmenter);
	}

	public void nextTurn(List<GameOption> selectedOptions){
		nextTurn(selectedOptions, new NoMultiplier());
	}
  
  
	public void nextTurn(List<GameOption> selectedOptions, ScoreAugmenter augmenter){
		Score matchScore = new Score(currentQuestion.calculatePoints(selectedOptions));

		matchResults.add(new MatchResult(currentPlayer, augmenter, matchScore));
    
		if(playersIterator.hasNext()){
			currentPlayer = playersIterator.next();
		}
		else if(!isOver){
			ScoreCalculator.calculateRoundEndResults(matchResults);

			if(questionIterator.hasNext()){
				currentQuestion = questionIterator.next();
				newRound();
			}else {
				isOver = true;
			}
		}
	}

	public Player getWinner() {
		if (players.get(0).getScore() == players.get(1).getScore()){
			return null;
		}
		if (players.get(0).getScore().biggerThan(players.get(1).getScore())){
			return players.get(0);
		}
		return players.get(1);
	}

	public int getTurnCount(){
		return players.size() * questions.size();
	}
	
	public boolean isAugmenterAvailable(String augmenterString){
		AugmenterType augmenter = AugmenterType.getEnumByName(augmenterString);	
		ScoreAugmenter scoreAugmenter = augmenter.getScoreAugmenter();
		
		return scoreAugmenter.isForPenalty() == currentQuestion.hasPenalty() &&
				currentPlayer.hasAugmenter(augmenter);
	}
}
