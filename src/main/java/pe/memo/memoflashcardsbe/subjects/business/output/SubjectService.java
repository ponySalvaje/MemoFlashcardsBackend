package pe.memo.memoflashcardsbe.subjects.business.output;

import pe.memo.memoflashcardsbe.repository.entities.Subject;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

import java.util.List;

public interface SubjectService {

    PageableResponse<Subject> findAllSubjectsPageable(Integer pageSize, Integer pageNumber);

    PageableResponse<Subject> findAllSubjectsByNamePageable(Integer pageSize, Integer pageNumber, String search);

    PageableResponse<Subject> findAllSubjectsByLessonId(Long lessonId, Integer pageSize, Integer pageNumber);

    List<Subject> findAllSubjectsByLessonIdNoPaging(Long lessonId);
}
