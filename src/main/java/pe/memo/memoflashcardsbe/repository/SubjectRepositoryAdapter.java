package pe.memo.memoflashcardsbe.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.memo.memoflashcardsbe.repository.entities.Subject;
import pe.memo.memoflashcardsbe.subjects.business.input.SubjectRepositoryPort;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

import static pe.memo.memoflashcardsbe.utils.PageableUtils.buildPageRequest;
import static pe.memo.memoflashcardsbe.utils.PageableUtils.convertPageToPageableResponse;

@Component
public class SubjectRepositoryAdapter implements SubjectRepositoryPort {

    private final ISubjectRepository subjectRepository;

    @Autowired
    public SubjectRepositoryAdapter(ISubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public PageableResponse<Subject> findAllSubjectPageable(Integer pageSize, Integer pageNumber) {
        return convertPageToPageableResponse(subjectRepository.findAll(buildPageRequest(pageSize, pageNumber)));
    }

    @Override
    public PageableResponse<Subject> findAllSubjectsByNamePageable(Integer pageSize, Integer pageNumber, String search) {
        return convertPageToPageableResponse(subjectRepository.findAllByNameContainingIgnoreCase(search, buildPageRequest(pageSize, pageNumber)));
    }
}
