package pe.memo.memoflashcardsbe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pe.memo.memoflashcardsbe.repository.entities.Subject;

import java.util.List;

public interface ISubjectRepository extends JpaRepository<Subject, Long>, PagingAndSortingRepository<Subject, Long> {

    Page<Subject> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Subject> findAllByLessonIdAndDeletedAtIsNull(Long lessonId, Pageable pageable);

    List<Subject> findAllByLessonId(Long lessonId);
}
