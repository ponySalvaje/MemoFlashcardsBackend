package pe.memo.memoflashcardsbe.scores.business.input;

import pe.memo.memoflashcardsbe.repository.entities.ScoreEntity;

import java.util.List;
import java.util.Map;

public interface ScoreRepositoryPort {

    void createScoreForLesson(ScoreEntity score);

    Map<Long, List<ScoreEntity>> findScoresByUserIdGroupedBySubjectId(Long userId);

}
