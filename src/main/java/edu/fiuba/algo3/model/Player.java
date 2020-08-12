package edu.fiuba.algo3.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import edu.fiuba.algo3.constants.AugmenterType;
import edu.fiuba.algo3.engine.score.augmenters.NoMultiplier;
import edu.fiuba.algo3.engine.score.augmenters.ScoreAugmenter;

public class Player {

    private String name;
    private Score score;
    private List<ScoreAugmenter> augmenters = new ArrayList<>();

    public Player(String name) {
        this.name = name;
        score = new Score(0);
        loadAugmenters();
    }

    public String getName() {
        return name;
    }

    public Score getScore() {
        return score;
    }

    public void sumScore(Score matchScore) {
        score.sum(matchScore);
    }

    public ScoreAugmenter getAugmenter(ScoreAugmenter augmenter) {
        int index = augmenters.indexOf(augmenter);
        if (index > -1) {
            return augmenters.remove(index);
        }
        return new NoMultiplier();
    }

    public boolean hasAugmenter(AugmenterType augmenterType) {
        return augmenters.contains(augmenterType.getScoreAugmenter());
    }

    private void loadAugmenters() {
        for (AugmenterType augmenterType : AugmenterType.values()) {
            ScoreAugmenter augmenter = augmenterType.getScoreAugmenter();
            IntStream.range(0, augmenter.getUsesPerPlayer()).forEach(
                    element -> augmenters.add(augmenter)
            );
        }
    }
}
