package pe.memo.memoflashcardsbe.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pe.memo.memoflashcardsbe.repository.entities.ScoreEntity;
import pe.memo.memoflashcardsbe.scores.business.input.ScoreRepositoryPort;

import java.util.Optional;

@Component
public class ScoreRepositoryAdapter implements ScoreRepositoryPort {

    private final IScoresRepository scoresRepository;

    @Autowired
    public ScoreRepositoryAdapter(IScoresRepository scoresRepository) {
        this.scoresRepository = scoresRepository;
    }

    @Override
    public void createScoreForLesson(ScoreEntity score) {
        ScoreEntity currentEntity = this.scoresRepository.findByCardIdAndUserId(score.getCard().getId(), score.getUser().getId())
                .orElse(null);
        if (!ObjectUtils.isEmpty(currentEntity)) {
            currentEntity.setScore(score.getScore());
            currentEntity.setLastEaseFactor(Optional.ofNullable(score.getLastEaseFactor()).orElse(currentEntity.getLastEaseFactor()));
            this.scoresRepository.updateScoreByUserAndCard(score.getScore(), score.getUser(), score.getCard());
        } else {
            score.setLastEaseFactor(Optional.ofNullable(score.getLastEaseFactor()).orElse(2.5));
            this.scoresRepository.save(score);
        }
    }

}
