package pe.memo.memoflashcardsbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pe.memo.memoflashcardsbe.repository.entities.ScoreEntity;

import java.util.Optional;

public interface IScoresRepository extends JpaRepository<ScoreEntity, Long>, PagingAndSortingRepository<ScoreEntity, Long> {
    Optional<ScoreEntity> findByCardIdAndUserId(Long cardId, Long userId);
}
