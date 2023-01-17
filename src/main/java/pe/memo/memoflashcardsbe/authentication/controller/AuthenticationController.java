package pe.memo.memoflashcardsbe.authentication.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pe.memo.memoflashcardsbe.authentication.business.input.AuthenticationService;
import pe.memo.memoflashcardsbe.authentication.controller.request.SignInRequest;

@RestController(value = "/auth")
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public String signIn(@RequestBody SignInRequest request) {
        log.info("Attempting sign in for user {}", request.username());
        boolean success = this.authenticationService.login(request.username(), request.password());
        return Boolean.toString(success);
    }
}
