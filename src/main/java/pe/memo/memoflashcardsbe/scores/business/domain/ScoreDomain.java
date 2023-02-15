package pe.memo.memoflashcardsbe.scores.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScoreDomain {

    Long id;
    Long userId;
    Long cardId;
    ScoreEnum score;
    Long repetitions;
    Long lastInterval;
    Instant repetitionDate;
    Double lastEaseFactor;
}
