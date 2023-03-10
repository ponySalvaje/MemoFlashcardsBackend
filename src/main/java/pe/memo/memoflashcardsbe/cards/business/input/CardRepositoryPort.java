package pe.memo.memoflashcardsbe.cards.business.input;

import pe.memo.memoflashcardsbe.repository.entities.Card;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

import java.util.Map;

public interface CardRepositoryPort {

    PageableResponse<Card> listCards(Integer pageSize, Integer pageNumber, String subjectId, Boolean isFreeTier);

    Map<String, Integer> countCardsBySubjectAndIsFree(Long subjectId);

    Map<String,Integer> countCardsByLesson(Long lessonId);
}
