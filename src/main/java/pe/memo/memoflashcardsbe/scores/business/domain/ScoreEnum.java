package pe.memo.memoflashcardsbe.scores.business.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ScoreEnum {
    SUSPENDED("Suspendido"), EASY("Fácil"), MEDIUM("Dude"), HARD("Difícil");

    final String scoreValue;
}
