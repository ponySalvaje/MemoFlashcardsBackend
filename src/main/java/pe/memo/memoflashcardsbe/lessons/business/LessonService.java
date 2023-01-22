package pe.memo.memoflashcardsbe.lessons.business;

import pe.memo.memoflashcardsbe.repository.entities.Lesson;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

public interface LessonService {

    PageableResponse<Lesson> findAll(Integer pageSize, Integer pageNumber);

}
