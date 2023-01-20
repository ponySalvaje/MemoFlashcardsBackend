package pe.memo.memoflashcardsbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.memo.memoflashcardsbe.repository.entities.UserData;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserData, Long> {

    Optional<UserData> findUserByEmail(String email);
}
