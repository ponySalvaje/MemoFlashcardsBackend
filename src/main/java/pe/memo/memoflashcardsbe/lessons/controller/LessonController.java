package pe.memo.memoflashcardsbe.lessons.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.memo.memoflashcardsbe.lessons.business.LessonService;
import pe.memo.memoflashcardsbe.lessons.controller.response.LessonResponse;
import pe.memo.memoflashcardsbe.repository.entities.Lesson;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    public PageableResponse<LessonResponse> getAll(Integer pageSize, Integer pageNumber) {
        Random rnd = new Random();
        PageableResponse<Lesson> pageResponse = this.lessonService.findAll(pageSize, pageNumber);
        return PageableResponse.<LessonResponse>builder()
                .metadata(pageResponse.getMetadata())
                .content(pageResponse.getContent().stream().map(lesson -> LessonResponse.builder()
                        .id(lesson.getId())
                        .title(lesson.getName())
                        .free(rnd.nextInt(100))
                        .premium(rnd.nextInt(100))
                        .photo(String.format("https://memoflashcards.s3.us-east-2.amazonaws.com/%s", lesson.getIconPath()))
                        .build()).collect(Collectors.toList()))
                .build();
    }
}
