package pe.memo.memoflashcardsbe.authentication.business.input;

import pe.memo.memoflashcardsbe.repository.entities.UserData;

public interface UserRepositoryPort {

    boolean login(String username, String password);

    void registerUser(String name, String email, String password);

    UserData getUserInfo(String username);
}
