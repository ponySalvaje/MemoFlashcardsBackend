package pe.memo.memoflashcardsbe.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.memo.memoflashcardsbe.lessons.business.LessonRepositoryPort;
import pe.memo.memoflashcardsbe.repository.entities.Lesson;
import pe.memo.memoflashcardsbe.utils.PageableResponse;
import pe.memo.memoflashcardsbe.utils.PageableUtils;

import static pe.memo.memoflashcardsbe.utils.PageableUtils.buildPageRequest;
import static pe.memo.memoflashcardsbe.utils.PageableUtils.convertPageToPageableResponse;

@Component
public class LessonRepositoryAdapter implements LessonRepositoryPort {
    private final ILessonRepository lessonRepository;

    @Autowired
    public LessonRepositoryAdapter(ILessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public PageableResponse<Lesson> findAll(Integer pageSize, Integer pageNumber) {
        return convertPageToPageableResponse(this.lessonRepository.findAll(buildPageRequest(pageSize, pageNumber)));
    }
}
