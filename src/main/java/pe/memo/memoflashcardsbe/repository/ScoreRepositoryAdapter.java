package pe.memo.memoflashcardsbe.repository;

import jakarta.persistence.EntityNotFoundException;
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
        this.scoresRepository.save(score);
    }

    @Override
    public void updateScoreForLesson(ScoreEntity score) {
        if (ObjectUtils.isEmpty(score.getId())) {
            throw new EntityNotFoundException("Could not find entity or id was null!");
        }
        ScoreEntity currentEntity = this.scoresRepository.findById(score.getId())
                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + score.getId()));

        this.scoresRepository.save(currentEntity);
    }
}
