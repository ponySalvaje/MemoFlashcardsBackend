package pe.memo.memoflashcardsbe.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import pe.memo.memoflashcardsbe.authentication.business.input.UserRepositoryPort;
import pe.memo.memoflashcardsbe.repository.entities.UserData;

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

    @Override
    public void registerUser(String name, String email, String password) {
        Optional<UserData> existingUser = iUserRepository.findUserByEmail(email);
        if (existingUser.isPresent()) {
            throw new DuplicateKeyException(String.format("Username %s already exists!", email));
        }
        UserData userData = new UserData();
        userData.setEmail(email);
        userData.setName(name);
        userData.setPassword(passwordEncoder.encode(password));
        userData.setId(this.iUserRepository.findLastIdForUser() + 1);
        userData.setType("free student");
        userData.setStudentTypeId((short) 1);
        userData.setUniversityId((short) 1);
        this.iUserRepository.save(userData);
    }

    @Override
    public UserData getUserInfo(String username) {
        return this.iUserRepository.findByEmail(username);
    }
}
