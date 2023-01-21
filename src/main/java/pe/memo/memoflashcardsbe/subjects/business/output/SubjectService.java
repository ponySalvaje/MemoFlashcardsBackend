package pe.memo.memoflashcardsbe.subjects.business.output;

import pe.memo.memoflashcardsbe.repository.entities.Subject;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

public interface SubjectService {

    PageableResponse<Subject> findAllSubjectsPageable(Integer pageSize, Integer pageNumber);
}
