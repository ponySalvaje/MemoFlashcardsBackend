package pe.memo.memoflashcardsbe.lessons.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.memo.memoflashcardsbe.cards.business.output.CardService;
import pe.memo.memoflashcardsbe.lessons.business.LessonService;
import pe.memo.memoflashcardsbe.lessons.controller.response.LessonResponse;
import pe.memo.memoflashcardsbe.repository.entities.Lesson;
import pe.memo.memoflashcardsbe.repository.entities.Subject;
import pe.memo.memoflashcardsbe.subjects.business.output.SubjectService;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    private final SubjectService subjectService;

    private final CardService cardService;

    @Autowired
    public LessonController(LessonService lessonService,
                            SubjectService subjectService,
                            CardService cardService) {
        this.lessonService = lessonService;
        this.subjectService = subjectService;
        this.cardService = cardService;
    }

    @GetMapping
    public PageableResponse<LessonResponse> getAll(Integer pageSize, Integer pageNumber) {
        PageableResponse<Lesson> pageResponse = this.lessonService.findAll(pageSize, pageNumber);
        Map<String, Integer> defaultMap = new HashMap<>();
        defaultMap.put("free", 0);
        defaultMap.put("premium", 0);
        return PageableResponse.<LessonResponse>builder()
                .metadata(pageResponse.getMetadata())
                .content(pageResponse.getContent().stream().map(lesson -> {
                    List<Subject> subjects = this.subjectService.findAllSubjectsByLessonIdNoPaging(lesson.getId());
                    Map<String, Integer> countMapSubjects = subjects.stream()
                            .map(subject ->
                                    this.cardService.countCardsBySubjectId(subject.getId()))
                            .reduce((m1, m2) -> {
                                Map<String, Integer> m3 = new HashMap<>(m1);
                                m2.forEach((key, value) -> m3.merge(key, value, Integer::sum));
                                return m3;
                            }).orElse(defaultMap);
                    return LessonResponse.builder()
                            .id(lesson.getId())
                            .title(lesson.getName())
                            .free(countMapSubjects.get("free"))
                            .premium(countMapSubjects.get(("premium")))
                            .photo(String.format("https://memoflashcards.s3.us-east-2.amazonaws.com/%s", lesson.getIconPath()))
                            .build();
                }).toList())
                .build();
    }
}
