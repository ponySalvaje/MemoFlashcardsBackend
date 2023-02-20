package pe.memo.memoflashcardsbe.subjects.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.memo.memoflashcardsbe.cards.business.output.CardService;
import pe.memo.memoflashcardsbe.repository.entities.Subject;
import pe.memo.memoflashcardsbe.subjects.business.output.SubjectService;
import pe.memo.memoflashcardsbe.subjects.controller.response.SubjectGroupResponse;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

import java.util.Map;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    private final CardService cardService;

    @Autowired
    public SubjectController(SubjectService subjectService,
                             CardService cardService) {
        this.subjectService = subjectService;
        this.cardService = cardService;
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
        PageableResponse<Subject> databaseResult = this.subjectService.findAllSubjectsByLessonId(lessonId, pageSize, pageNumber);
        return PageableResponse.<SubjectGroupResponse>builder()
                .metadata(databaseResult.getMetadata())
                .content(databaseResult.getContent().stream().map(
                        subject -> {
                            Map<String, Integer> countMap = this.cardService.countCardsBySubjectId(subject.getId());
                            return SubjectGroupResponse.builder()
                                    .id(subject.getId())
                                    .title(subject.getName())
                                    .free(countMap.get("free"))
                                    .premium(countMap.get("premium"))
                                    .build();
                        }
                ).toList())
                .build();
    }
}
