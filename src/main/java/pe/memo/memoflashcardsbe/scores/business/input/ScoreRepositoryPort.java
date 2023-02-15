package pe.memo.memoflashcardsbe.scores.business.input;

import pe.memo.memoflashcardsbe.repository.entities.ScoreEntity;

public interface ScoreRepositoryPort {

    void createScoreForLesson(ScoreEntity score);

}
