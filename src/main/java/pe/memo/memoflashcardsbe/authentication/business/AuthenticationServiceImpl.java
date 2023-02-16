package pe.memo.memoflashcardsbe.authentication.business;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import pe.memo.memoflashcardsbe.authentication.business.input.UserRepositoryPort;
import pe.memo.memoflashcardsbe.authentication.business.output.AuthenticationService;
import pe.memo.memoflashcardsbe.repository.entities.UserData;

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
            throw new BadCredentialsException(String.format("Failed to authenticate user %s", username));
        }
    }

    @Override
    public void registerUser(String name, String email, String password) {
        this.userRepositoryPort.registerUser(name, email, password);
    }

    @Override
    public UserData getUserDataByUsername(String username) {
        return this.userRepositoryPort.getUserInfo(username);
    }
}
