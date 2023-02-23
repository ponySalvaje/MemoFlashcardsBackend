package pe.memo.memoflashcardsbe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pe.memo.memoflashcardsbe.repository.entities.Card;

public interface ICardRepository extends JpaRepository<Card, Long>, PagingAndSortingRepository<Card, Long> {

    Page<Card> findAllBySubjectIdAndDeletedAtIsNullAndIsFree(Long subjectId, Boolean isTrue, Pageable pageable);

    Integer countAllBySubjectIdAndIsFree(Long subjectId, Boolean isFree);
}
