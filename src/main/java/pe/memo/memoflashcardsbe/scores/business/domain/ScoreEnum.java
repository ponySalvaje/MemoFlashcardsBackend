package pe.memo.memoflashcardsbe.scores.business.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ScoreEnum {
    SUSPENDED("Suspendido", null), EASY("Fácil", 2.5), MEDIUM("Dude", 2.6), HARD("Difícil", 2.7);

    final String scoreValue;
    final Double initialEaseFactor;
}
