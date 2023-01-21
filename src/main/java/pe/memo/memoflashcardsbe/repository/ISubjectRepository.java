package pe.memo.memoflashcardsbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pe.memo.memoflashcardsbe.repository.entities.Subject;

public interface ISubjectRepository extends JpaRepository<Subject, Integer>, PagingAndSortingRepository<Subject, Integer> {
}
