package pe.memo.memoflashcardsbe.scores.business.output;

import pe.memo.memoflashcardsbe.scores.business.domain.ScoreDomain;

public interface ScoreService {
    void saveScore(ScoreDomain scoreDomain);

    void updateScore(ScoreDomain scoreDomain);
}
