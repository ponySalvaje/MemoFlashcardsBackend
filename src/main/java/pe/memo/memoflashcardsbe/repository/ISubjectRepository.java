package pe.memo.memoflashcardsbe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pe.memo.memoflashcardsbe.repository.entities.Subject;

public interface ISubjectRepository extends JpaRepository<Subject, Integer>, PagingAndSortingRepository<Subject, Integer> {

    Page<Subject> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
