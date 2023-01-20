package pe.memo.memoflashcardsbe.cards.business.input;

import pe.memo.memoflashcardsbe.repository.entities.Card;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

public interface CardService {

    PageableResponse<Card> listCards(Integer pageSize, Integer pageNumber);
}
