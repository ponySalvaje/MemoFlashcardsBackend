package pe.memo.memoflashcardsbe.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pe.memo.memoflashcardsbe.repository.entities.ScoreEntity;
import pe.memo.memoflashcardsbe.repository.entities.Subject;
import pe.memo.memoflashcardsbe.scores.business.input.ScoreRepositoryPort;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ScoreRepositoryAdapter implements ScoreRepositoryPort {

    private final IScoresRepository scoresRepository;

    private final ISubjectRepository subjectRepository;

    @Autowired
    public ScoreRepositoryAdapter(IScoresRepository scoresRepository,
                                  ISubjectRepository subjectRepository) {
        this.scoresRepository = scoresRepository;
        this.subjectRepository = subjectRepository;
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

    @Override
    public Map<Long, List<ScoreEntity>> findScoresByUserIdGroupedBySubjectId(Long userId) {
        List<ScoreEntity> scores = scoresRepository.findByUserId(userId);
        return scores.stream().collect(Collectors.groupingBy(score -> this.subjectRepository.findById(score.getCard().getSubjectId()).map(Subject::getLessonId).orElse(0L)));
    }

}
