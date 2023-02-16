package pe.memo.memoflashcardsbe.scores.controller.request;

import pe.memo.memoflashcardsbe.scores.business.domain.ScoreEnum;

public record SaveScoreRequest(Long id, Long cardId, ScoreEnum score) {
}
