package edu.fiuba.algo3.model;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.constants.AugmenterType;

public class MatchResult {

	private Player player;
	private List<GameOption> selectedOptions;
	private AugmenterType selectedAugmenter;
	
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
	
}