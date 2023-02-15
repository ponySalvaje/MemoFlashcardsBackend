package pe.memo.memoflashcardsbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pe.memo.memoflashcardsbe.repository.entities.ScoreEntity;

public interface IScoresRepository extends JpaRepository<ScoreEntity, Long>, PagingAndSortingRepository<ScoreEntity, Long> {
}
