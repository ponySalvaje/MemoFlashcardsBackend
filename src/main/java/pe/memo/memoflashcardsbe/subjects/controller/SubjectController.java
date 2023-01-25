package pe.memo.memoflashcardsbe.subjects.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.memo.memoflashcardsbe.repository.entities.Subject;
import pe.memo.memoflashcardsbe.subjects.business.output.SubjectService;
import pe.memo.memoflashcardsbe.subjects.controller.response.SubjectGroupResponse;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

import java.util.Random;
import java.util.stream.Collectors;

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

    @GetMapping("/{lessonId}")
    public PageableResponse<SubjectGroupResponse> getSubjectsByLessonId(@PathVariable("lessonId") Long lessonId, Integer pageSize, Integer pageNumber) {
        Random rnd = new Random();
        PageableResponse<Subject> databaseResult = this.subjectService.findAllSubjectsByLessonId(lessonId, pageSize, pageNumber);
        return PageableResponse.<SubjectGroupResponse>builder()
                .metadata(databaseResult.getMetadata())
                .content(databaseResult.getContent().stream().map(
                        subject ->
                                SubjectGroupResponse.builder()
                                        .id(subject.getId())
                                        .title(subject.getName())
                                        .free(rnd.nextInt(100))
                                        .premium(rnd.nextInt(100))
                                        .build()
                ).collect(Collectors.toList()))
                .build();
    }
}
