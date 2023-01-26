package pe.memo.memoflashcardsbe.cards.business.output;

import pe.memo.memoflashcardsbe.repository.entities.Card;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

import java.util.Map;

public interface CardService {

    PageableResponse<Card> listCards(Integer pageSize, Integer pageNumber, String subjectId);

    Map<String,Integer> countCardsBySubjectId(Long subjectId);
}
