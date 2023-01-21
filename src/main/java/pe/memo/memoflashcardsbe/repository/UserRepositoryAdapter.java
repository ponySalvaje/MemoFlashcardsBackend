package pe.memo.memoflashcardsbe.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import pe.memo.memoflashcardsbe.repository.IUserRepository;
import pe.memo.memoflashcardsbe.repository.entities.UserData;
import pe.memo.memoflashcardsbe.authentication.business.input.UserRepositoryPort;

import java.util.Optional;

@Repository
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final IUserRepository iUserRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepositoryAdapter(IUserRepository iUserRepository,
                                 PasswordEncoder passwordEncoder) {
        this.iUserRepository = iUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean login(String username, String password) {
        Optional<UserData> possibleUser = iUserRepository.findUserByEmail(username);
        return possibleUser.filter(userData -> passwordEncoder.matches(password, userData.getPassword())).isPresent();
    }
}
