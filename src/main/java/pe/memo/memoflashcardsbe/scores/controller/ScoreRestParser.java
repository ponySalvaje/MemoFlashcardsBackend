package pe.memo.memoflashcardsbe.scores.controller;

import org.springframework.stereotype.Component;
import pe.memo.memoflashcardsbe.scores.business.domain.ScoreDomain;
import pe.memo.memoflashcardsbe.scores.controller.request.SaveScoreRequest;

import java.util.Optional;

@Component
public class ScoreRestParser {

    public ScoreDomain convertRequestToDomain(SaveScoreRequest request) {
        return Optional.ofNullable(request)
                .map(score -> ScoreDomain.builder()
                        .id(request.id())
                        .userId(request.userId())
                        .cardId(request.cardId())
                        .score(request.score())
                        .build())
                .orElse(null);
    }
}
