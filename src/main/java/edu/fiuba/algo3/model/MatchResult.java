package edu.fiuba.algo3.model;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.constants.AugmenterType;

public class MatchResult {

	private Player player;
	private List<GameOption> selectedOptions;
	private AugmenterType selectedAugmenter;
	private Score matchScore;
	
	
	public MatchResult(Player player, List<GameOption> selectedOptions) {
		this.player = player;
		this.selectedOptions = selectedOptions;
	}
	
	public MatchResult(Player player, GameOption selectedOption) {
		this.player = player;
		this.selectedOptions = new ArrayList<>();
		selectedOptions.add(selectedOption);
	}
	
	public MatchResult(Player player, List<GameOption> selectedOptions, AugmenterType selectedAugmenter) {
		this.player = player;
		this.selectedOptions = selectedOptions;
		this.selectedAugmenter = selectedAugmenter;
	}
	
	public MatchResult(Player player, GameOption selectedOption, AugmenterType selectedAugmenter) {
		this.player = player;
		this.selectedOptions = new ArrayList<>();
		selectedOptions.add(selectedOption);
		this.selectedAugmenter = selectedAugmenter;
	}

	public MatchResult(Player player, List<GameOption> selectedOptions) {
		this.player = player;
		this.selectedOptions = selectedOptions;
		this.selectedAugmenter = null;
	}

	public MatchResult(Player player, GameOption selectedOption) {
		this.player = player;
		this.selectedOptions = new ArrayList<>();
		selectedOptions.add(selectedOption);
		this.selectedAugmenter = null;
	}

	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public List<GameOption> getSelectedOptions() {
		return selectedOptions;
	}
	public void setSelectedOptions(List<GameOption> selectedOptions) {
		this.selectedOptions = selectedOptions;
	}

	public AugmenterType getSelectedAugmenter() {
		return selectedAugmenter;
	}

	public void setSelectedAugmenter(AugmenterType selectedAugmenter) {
		this.selectedAugmenter = selectedAugmenter;
	}

	public Score getMatchScore() {
		return matchScore;
	}

	public void setMatchScore(Score matchScore) {
		this.matchScore = matchScore;
	}
	
	public void sumMatchScoreToPlayer() {
		player.getScore().sumScore(matchScore);
	}
	
}
