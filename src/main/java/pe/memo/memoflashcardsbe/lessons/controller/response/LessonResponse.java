package pe.memo.memoflashcardsbe.lessons.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LessonResponse {

    Long id;
    String title;
    String photo;
    Integer premium;
    Integer free;
}
