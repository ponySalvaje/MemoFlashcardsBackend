package pe.memo.memoflashcardsbe.subjects.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.memo.memoflashcardsbe.repository.entities.Subject;
import pe.memo.memoflashcardsbe.subjects.business.output.SubjectService;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public PageableResponse<Subject> getSubjectsPageable(Integer pageSize, Integer pageNumber) {
        return this.subjectService.findAllSubjectsPageable(pageSize, pageNumber);
    }

    @GetMapping("/search")
    public PageableResponse<Subject> getSubjectsByNamePageable(Integer pageSize, Integer pageNumber, String term) {
        return this.subjectService.findAllSubjectsByNamePageable(pageSize, pageNumber, term);
    }
}
