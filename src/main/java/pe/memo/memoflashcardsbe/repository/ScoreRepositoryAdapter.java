package pe.memo.memoflashcardsbe.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pe.memo.memoflashcardsbe.repository.entities.ScoreEntity;
import pe.memo.memoflashcardsbe.scores.business.input.ScoreRepositoryPort;

@Component
public class ScoreRepositoryAdapter implements ScoreRepositoryPort {

    private final IScoresRepository scoresRepository;

    @Autowired
    public ScoreRepositoryAdapter(IScoresRepository scoresRepository) {
        this.scoresRepository = scoresRepository;
    }

    @Override
    public void createScoreForLesson(ScoreEntity score) {
        ScoreEntity currentEntity = this.scoresRepository.findByCardIdAndUserId(score.getCardId(), score.getUserId())
                .orElse(null);
        if (!ObjectUtils.isEmpty(currentEntity)) {
            currentEntity.setScore(score.getScore());
        }
        this.scoresRepository.save(score);
    }

}
