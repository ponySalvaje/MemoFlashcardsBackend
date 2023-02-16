package pe.memo.memoflashcardsbe.authentication.business.output;

import pe.memo.memoflashcardsbe.repository.entities.UserData;

public interface AuthenticationService {

    void login(String username, String password);

    void registerUser(String name, String email, String password);

    UserData getUserDataByUsername(String username);
}
