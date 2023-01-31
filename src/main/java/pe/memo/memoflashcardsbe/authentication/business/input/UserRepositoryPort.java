package pe.memo.memoflashcardsbe.authentication.business.input;

public interface UserRepositoryPort {

    boolean login(String username, String password);

    void registerUser(String name, String email, String password);
}
