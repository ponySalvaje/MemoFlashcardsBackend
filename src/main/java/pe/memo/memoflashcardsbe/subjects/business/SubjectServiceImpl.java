package pe.memo.memoflashcardsbe.subjects.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.memo.memoflashcardsbe.repository.entities.Subject;
import pe.memo.memoflashcardsbe.subjects.business.input.SubjectRepositoryPort;
import pe.memo.memoflashcardsbe.subjects.business.output.SubjectService;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepositoryPort subjectRepositoryPort;

    @Autowired
    public SubjectServiceImpl(SubjectRepositoryPort subjectRepositoryPort) {
        this.subjectRepositoryPort = subjectRepositoryPort;
    }

    @Override
    public PageableResponse<Subject> findAllSubjectsPageable(Integer pageSize, Integer pageNumber) {
        return this.subjectRepositoryPort.findAllSubjectPageable(pageSize, pageNumber);
    }
}