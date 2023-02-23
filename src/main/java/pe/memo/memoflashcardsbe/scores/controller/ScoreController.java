package pe.memo.memoflashcardsbe.scores.controller;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.memo.memoflashcardsbe.config.jwt.TokenManager;
import pe.memo.memoflashcardsbe.scores.business.output.ScoreService;
import pe.memo.memoflashcardsbe.scores.controller.request.SaveScoreRequest;

@RestController
@RequestMapping("/score")
public class ScoreController {

    private final ScoreService scoreService;

    private final ScoreRestParser scoreRestParser;

    private final TokenManager tokenManager;

    @Autowired
    public ScoreController(ScoreService scoreService,
                           ScoreRestParser scoreRestParser,
                           TokenManager tokenManager) {
        this.scoreService = scoreService;
        this.scoreRestParser = scoreRestParser;
        this.tokenManager = tokenManager;
    }

    @PostMapping
    public void saveScoreForCardByUser(@RequestHeader("Authorization") String token, @RequestBody SaveScoreRequest request) {
        Claims claims = tokenManager.getClaimsFromToken(token.split("Bearer ")[1]);
        this.scoreService.saveScore(this.scoreRestParser.convertRequestToDomain(request, claims.get("userId", Long.class)));
    }
}
