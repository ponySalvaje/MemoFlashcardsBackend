package pe.memo.memoflashcardsbe.authentication.business;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.memo.memoflashcardsbe.authentication.business.input.UserRepositoryPort;
import pe.memo.memoflashcardsbe.authentication.business.output.AuthenticationService;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepositoryPort userRepositoryPort;

    @Autowired
    public AuthenticationServiceImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public void login(String username, String password) {
        boolean success = this.userRepositoryPort.login(username, password);
        if (!success) {
            log.warn("Authentication attempt failed for user {}", username);
        }
    }
}
