package pe.memo.memoflashcardsbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pe.memo.memoflashcardsbe.repository.entities.Card;
import pe.memo.memoflashcardsbe.repository.entities.ScoreEntity;
import pe.memo.memoflashcardsbe.repository.entities.UserData;

import java.util.Optional;

public interface IScoresRepository extends JpaRepository<ScoreEntity, Long>, PagingAndSortingRepository<ScoreEntity, Long> {
    @Transactional
    @Modifying
    @Query("update ScoreEntity s set s.score = ?1 where s.user = ?2 and s.card = ?3")
    void updateScoreByUserAndCard(String score, UserData user, Card card);
    Optional<ScoreEntity> findByCardIdAndUserId(Long cardId, Long userId);
}
