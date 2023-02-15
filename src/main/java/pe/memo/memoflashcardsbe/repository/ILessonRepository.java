package pe.memo.memoflashcardsbe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pe.memo.memoflashcardsbe.repository.entities.Lesson;

public interface ILessonRepository extends JpaRepository<Lesson, Integer>, PagingAndSortingRepository<Lesson, Integer> {
    Page<Lesson> findAllByDeletedAtIsNull(Pageable pageable);
}