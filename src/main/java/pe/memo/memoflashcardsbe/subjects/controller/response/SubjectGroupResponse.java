package pe.memo.memoflashcardsbe.subjects.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubjectGroupResponse {
    Long id;
    String title;
    Integer free;
    Integer premium;
}
