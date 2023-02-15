package pe.memo.memoflashcardsbe.scores.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.memo.memoflashcardsbe.repository.entities.ScoreEntity;
import pe.memo.memoflashcardsbe.scores.business.domain.ScoreDomain;
import pe.memo.memoflashcardsbe.scores.business.input.ScoreRepositoryPort;
import pe.memo.memoflashcardsbe.scores.business.output.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepositoryPort scoreRepositoryPort;

    @Autowired
    public ScoreServiceImpl(ScoreRepositoryPort scoreRepositoryPort) {
        this.scoreRepositoryPort = scoreRepositoryPort;
    }

    @Override
    public void saveScore(ScoreDomain scoreDomain) {
        //TODO: Fitness logic for card repetition
        this.scoreRepositoryPort.createScoreForLesson(ScoreEntity.builder()
                .score(scoreDomain.getScore().getScoreValue())
                .userId(scoreDomain.getUserId())
                .cardId(scoreDomain.getCardId())
                .build());
    }

}
