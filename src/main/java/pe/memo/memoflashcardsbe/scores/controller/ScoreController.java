package pe.memo.memoflashcardsbe.scores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.memo.memoflashcardsbe.scores.business.output.ScoreService;
import pe.memo.memoflashcardsbe.scores.controller.request.SaveScoreRequest;

@RestController
@RequestMapping("/score/")
public class ScoreController {

    private final ScoreService scoreService;

    private final ScoreRestParser scoreRestParser;

    @Autowired
    public ScoreController(ScoreService scoreService,
                           ScoreRestParser scoreRestParser) {
        this.scoreService = scoreService;
        this.scoreRestParser = scoreRestParser;
    }

    @PostMapping
    public void createScoreForCardByUser(@RequestBody SaveScoreRequest request) {
        this.scoreService.saveScore(this.scoreRestParser.convertRequestToDomain(request));
    }

    @PutMapping
    public void updateScoreForCardByUse(@RequestBody SaveScoreRequest request){
        this.scoreService.updateScore(this.scoreRestParser.convertRequestToDomain(request));
    }
}
