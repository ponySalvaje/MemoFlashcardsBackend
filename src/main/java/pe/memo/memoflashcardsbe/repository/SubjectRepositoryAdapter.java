package pe.memo.memoflashcardsbe.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import pe.memo.memoflashcardsbe.repository.entities.Subject;
import pe.memo.memoflashcardsbe.subjects.business.input.SubjectRepositoryPort;
import pe.memo.memoflashcardsbe.utils.Metadata;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

import java.util.Optional;

@Component
public class SubjectRepositoryAdapter implements SubjectRepositoryPort {

    private final ISubjectRepository subjectRepository;

    @Autowired
    public SubjectRepositoryAdapter(ISubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }


    @Override
    public PageableResponse<Subject> findAllSubjectPageable(Integer pageSize, Integer pageNumber) {
        Page<Subject> resultPage = subjectRepository.findAll(PageRequest.of(
                Optional.ofNullable(pageNumber).map(integer -> integer - 1).orElse(0),
                Optional.ofNullable(pageSize).orElse(10)));
        return PageableResponse.<Subject>builder()
                .metadata(Metadata.builder()
                        .contentLength(resultPage.getContent().size())
                        .pageNumber(resultPage.getNumber() + 1)
                        .totalItems(resultPage.getTotalElements())
                        .pageSize(resultPage.getSize())
                        .totalPages(resultPage.getTotalPages())
                        .build())
                .content(resultPage.getContent())
                .build();
    }
}
