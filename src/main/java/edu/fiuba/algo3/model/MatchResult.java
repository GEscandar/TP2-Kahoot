package edu.fiuba.algo3.model;

import java.util.List;

import edu.fiuba.algo3.constants.AugmenterType;
import edu.fiuba.algo3.engine.score.augmenters.ScoreAugmenter;

public class MatchResult {

	private Player player;
	private ScoreAugmenter selectedAugmenter;
	private Score matchScore;

	public MatchResult(Player player, ScoreAugmenter augmenter, Score matchScore) {
		this.player = player;
		this.selectedAugmenter = player.getAugmenter(augmenter);
		this.matchScore = matchScore;
	}

	public MatchResult(Player player, Score matchScore) {
		this.player = player;
		this.selectedAugmenter = AugmenterType.NO_MULTIPLIER.getScoreAugmenter();
		this.matchScore = matchScore;
	}
	
	
	public Score getMatchScore() {
		return matchScore;
	}

	public void sumMatchScoreToPlayer() {
		player.sumScore(matchScore);
	}

	public void applyScoreAugmenter(List<MatchResult> resultList) {
		resultList.stream().forEach(result -> {
			if(!this.equals(result)) {	
				selectedAugmenter.applyScoreAugmenter(matchScore, result.getMatchScore());			
			}
		});
	}
	
	
}
