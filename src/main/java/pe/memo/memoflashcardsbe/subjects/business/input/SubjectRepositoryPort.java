package pe.memo.memoflashcardsbe.subjects.business.input;

import pe.memo.memoflashcardsbe.repository.entities.Subject;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

public interface SubjectRepositoryPort {

    PageableResponse<Subject> findAllSubjectPageable(Integer pageSize, Integer pageNumber);

    PageableResponse<Subject> findAllSubjectsByNamePageable(Integer pageSize, Integer pageNumber, String search);

    PageableResponse<Subject> findAllSubjectsByLessonIdPageable(Long lessonId, Integer pageSize, Integer pageNumber);
}
