package pe.memo.memoflashcardsbe.profile.business;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.memo.memoflashcardsbe.cards.business.input.CardRepositoryPort;
import pe.memo.memoflashcardsbe.lessons.business.LessonRepositoryPort;
import pe.memo.memoflashcardsbe.profile.business.domain.SubjectProgress;
import pe.memo.memoflashcardsbe.profile.business.output.UserProfileService;
import pe.memo.memoflashcardsbe.repository.entities.Lesson;
import pe.memo.memoflashcardsbe.repository.entities.ScoreEntity;
import pe.memo.memoflashcardsbe.scores.business.input.ScoreRepositoryPort;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

    private final LessonRepositoryPort lessonRepositoryPort;

    private final ScoreRepositoryPort scoreRepositoryPort;

    private final CardRepositoryPort cardRepositoryPort;


    @Autowired
    public UserProfileServiceImpl(LessonRepositoryPort lessonRepositoryPort,
                                  ScoreRepositoryPort scoreRepositoryPort,
                                  CardRepositoryPort cardRepositoryPort) {
        this.lessonRepositoryPort = lessonRepositoryPort;
        this.scoreRepositoryPort = scoreRepositoryPort;
        this.cardRepositoryPort = cardRepositoryPort;
    }

    @Override
    public List<SubjectProgress> getProgressForSubjectsByUserId(Long userId) {
        Map<Long, List<ScoreEntity>> scoreEntities = this.scoreRepositoryPort.findScoresByUserIdGroupedBySubjectId(userId);
        return scoreEntities.keySet().stream().map(lessonId -> {
            Map<String, Integer> cardCount = this.cardRepositoryPort.countCardsByLesson(lessonId);
            BigDecimal totalCards = BigDecimal.valueOf(cardCount.get("free") + cardCount.get("premium"));
            return SubjectProgress.builder()
                    .subjectName(Optional.ofNullable(this.lessonRepositoryPort.findById(lessonId)).map(Lesson::getName).orElse(null))
                    .freeCards(cardCount.get("free"))
                    .percentageProgress(BigDecimal.valueOf(scoreEntities.get(lessonId).size())
                            .divide(totalCards, 4, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(100)).doubleValue())
                    .build();
        }).toList();
    }
}
