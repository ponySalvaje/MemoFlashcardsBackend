package pe.memo.memoflashcardsbe.profile.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubjectProgress {

    Long lessonId;
    String subjectName;
    Double percentageProgress;
    Integer freeCards;
}
