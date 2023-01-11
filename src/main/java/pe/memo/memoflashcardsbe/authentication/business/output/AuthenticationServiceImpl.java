package pe.memo.memoflashcardsbe.authentication.business.output;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.memo.memoflashcardsbe.authentication.business.input.AuthenticationService;
import pe.memo.memoflashcardsbe.repository.input.UserRepositoryPort;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepositoryPort userRepositoryPort;
    @Autowired
    public AuthenticationServiceImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public boolean login(String username, String password) {
        return this.userRepositoryPort.login(username, password);
    }
}
