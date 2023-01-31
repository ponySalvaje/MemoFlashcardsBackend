package pe.memo.memoflashcardsbe.authentication.business.output;

public interface AuthenticationService {

    void login(String username, String password);

    void registerUser(String name, String email, String password);
}
