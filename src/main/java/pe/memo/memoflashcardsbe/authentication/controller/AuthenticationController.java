package pe.memo.memoflashcardsbe.authentication.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.memo.memoflashcardsbe.authentication.business.input.AuthenticationService;
import pe.memo.memoflashcardsbe.authentication.controller.request.SignInRequest;
import pe.memo.memoflashcardsbe.authentication.controller.response.JWTResponse;
import pe.memo.memoflashcardsbe.authentication.business.output.JwtUserDetailsService;
import pe.memo.memoflashcardsbe.config.jwt.TokenManager;

@RestController
@RequestMapping(value = "/auth")
@Slf4j
public class AuthenticationController {

    private final JwtUserDetailsService jwtUserDetailsService;
    private final TokenManager tokenManager;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService,
                                    JwtUserDetailsService jwtUserDetailsService,
                                    TokenManager tokenManager) {
        this.authenticationService = authenticationService;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.tokenManager = tokenManager;
    }

    @PostMapping("/login")
    public JWTResponse signIn(@RequestBody SignInRequest request) throws Exception {
        log.info("Attempting sign in for user {}", request.username());
        this.authenticationService.login(request.username(), request.password());
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(request.username());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return JWTResponse.builder().token(jwtToken).build();
    }
}
