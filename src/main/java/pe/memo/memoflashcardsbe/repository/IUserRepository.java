package pe.memo.memoflashcardsbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.memo.memoflashcardsbe.repository.entities.UserData;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserData, Long> {
    UserData findByEmail(String email);

    Optional<UserData> findUserByEmail(String email);

    @Query("select u.id from UserData u order by u.createdAt desc limit 1")
    Long findLastIdForUser();
}
