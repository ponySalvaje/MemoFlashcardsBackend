package pe.memo.memoflashcardsbe.profile.controller;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.memo.memoflashcardsbe.config.jwt.TokenManager;
import pe.memo.memoflashcardsbe.profile.business.domain.SubjectProgress;
import pe.memo.memoflashcardsbe.profile.business.output.UserProfileService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/profile/")
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final TokenManager tokenManager;

    @Autowired
    public UserProfileController(UserProfileService userProfileService, TokenManager tokenManager) {
        this.userProfileService = userProfileService;
        this.tokenManager = tokenManager;
    }

    @GetMapping("/progress")
    public List<SubjectProgress> getSubjectProgressByUserId(@RequestHeader("Authorization") String token) {
        Claims claims = tokenManager.getClaimsFromToken(token.split("Bearer ")[1]);
        return this.userProfileService.getProgressForSubjectsByUserId(claims.get("userId", Long.class));
    }
}
