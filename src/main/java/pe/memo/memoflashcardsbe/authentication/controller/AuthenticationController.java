package pe.memo.memoflashcardsbe.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pe.memo.memoflashcardsbe.authentication.business.input.AuthenticationService;
import pe.memo.memoflashcardsbe.authentication.controller.request.SignInRequest;

@Controller("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public String signIn(@RequestBody SignInRequest request) {
        boolean success = this.authenticationService.login(request.username(), request.password());
        return Boolean.toString(success);
    }
}
