package pe.memo.memoflashcardsbe.lessons.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.memo.memoflashcardsbe.repository.entities.Lesson;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

@Service
public class LessonServiceImpl implements LessonService {
    private final LessonRepositoryPort lessonRepositoryPort;

    @Autowired
    public LessonServiceImpl(LessonRepositoryPort lessonRepositoryPort) {
        this.lessonRepositoryPort = lessonRepositoryPort;
    }

    @Override
    public PageableResponse<Lesson> findAll(Integer pageSize, Integer pageNumber) {
        return this.lessonRepositoryPort.findAll(pageSize, pageNumber);
    }
}
